package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.HolyhellModClient;
import com.dead_comedian.holyhell.client.model.entity.*;
import com.dead_comedian.holyhell.client.model.entity.non_living.*;
import com.dead_comedian.holyhell.client.renderer.overlay.EndTextOverlay;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.render_layer.UpperRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.overlay.EyeTransitionOverlay;
import com.dead_comedian.holyhell.helper.PlayerHelpers;
import com.dead_comedian.holyhell.networking.packet.ServerboundAngelShaderAbilityPacket;
import com.dead_comedian.holyhell.particle.*;
import com.dead_comedian.holyhell.registries.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.AttackSweepParticle;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;


@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellClientEventBus {

    public static int staticTimer = 0;

    public static int windCooldown = 100;
    public static int windTimer1 = 1200;
    public static int windTimer2 = 300;
    public static int windTimer3 = 537;
    public static int windType = 4;
    public static int horrorCooldown;


    public static boolean shouldRenderParticle;


    @SubscribeEvent
    public static void renderRing(RenderPlayerEvent.Post event) {
        event.getRenderer().addLayer(new LowerRingRenderLayer<>(event.getRenderer(), Minecraft.getInstance().getEntityModels()));
        event.getRenderer().addLayer(new UpperRingRenderLayer<>(event.getRenderer(), Minecraft.getInstance().getEntityModels()));

    }

    @SubscribeEvent
    public static void playAmbientEffects(ClientTickEvent.Post event) {
        staticTimer++;
        Level level = Minecraft.getInstance().level;
        Player player = Minecraft.getInstance().player;

        if (player != null && level != null) {
            if (player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {
                //Static Sound
                if (staticTimer % 202 == 0) {
                    level.playLocalSound(player, HolyHellSounds.STATIC_AMBIENT.get(), SoundSource.AMBIENT, 0.6F, 1);
                }


                //Wind Sound

                if (windCooldown > 0) {
                    windTimer1 = 1200;
                    windTimer2 = 300;
                    windTimer3 = 537;
                    windType = level.getRandom().nextInt(0, 3);
                    windCooldown--;
                } else {
                    Minecraft.getInstance().getMusicManager().stopPlaying();
                    switch (windType) {
                        case 0:
                            if (windTimer1 == 1200) {
                                level.playLocalSound(player, HolyHellSounds.WIND_AMBIENT_1.get(), SoundSource.AMBIENT, 0.2F, 1);
                            }
                            windTimer1--;
                            if (windTimer1 <= 0) {
                                windType = 4;
                                windCooldown = level.getRandom().nextInt(100, 300);
                            }
                        case 1:
                            if (windTimer2 == 300) {
                                level.playLocalSound(player, HolyHellSounds.WIND_AMBIENT_2.get(), SoundSource.AMBIENT, 0.2F, 1);
                            }
                            windTimer2--;
                            if (windTimer2 <= 0) {
                                windType = 4;
                                windCooldown = level.getRandom().nextInt(100, 300);
                            }
                        case 2:
                            if (windTimer3 == 537) {
                                level.playLocalSound(player, HolyHellSounds.WIND_AMBIENT_3.get(), SoundSource.AMBIENT, 0.2F, 1);
                            }
                            windTimer3--;
                            if (windTimer3 <= 0) {
                                windType = 4;
                                windCooldown = level.getRandom().nextInt(100, 300);
                            }
                        case 4:
                            windType = level.getRandom().nextInt(0, 3);
                    }
                }

            } else {

                //STOP SOUNDS
            }
        }
    }


    @SubscribeEvent
    public static void locatorParticles(ClientTickEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {
                Level level = player.level();

                AABB userHitbox = new AABB(player.blockPosition()).inflate(100);

                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, userHitbox);
                for (LivingEntity i : list) {

                    if (i instanceof Monster && !i.getType().is(HolyhellTags.Entities.MINIBOSS) && !i.getType().is(HolyhellTags.Entities.BOSS)) {
                        level.addParticle(HolyhellParticles.HOSTILE_LOCATOR.get(), i.getX(), i.getY() + i.getHitbox().getYsize() / 2, i.getZ(), 0, 0, 0);
                    } else if (i.getType().is(HolyhellTags.Entities.BOSS) || i.getType().is(HolyhellTags.Entities.MINIBOSS)) {
                        level.addParticle(HolyhellParticles.BOSS_LOCATOR.get(), i.getX(), i.getY() + i.getHitbox().getYsize() / 2, i.getZ(), 0, 0, 0);
                    } else if (!(i instanceof Monster) && !(i instanceof Player)) {
                        level.addParticle(HolyhellParticles.PEACEFUL_LOCATOR.get(), i.getX(), i.getY() + i.getHitbox().getYsize() / 2, i.getZ(), 0, 0, 0);
                    } else if (i instanceof Player && i != player) {
                        level.addParticle(HolyhellParticles.PLAYER_LOCATOR.get(), i.getX(), i.getY() + i.getHitbox().getYsize() / 2, i.getZ(), 0, 0, 0);
                    }
                }

                list.removeAll(list);

            }
        }
    }


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (HolyHellKeyBinds.VISION_ABILITY_KEY.consumeClick()) {
            EyeTransitionOverlay.eyeTransitionCounter = 0;
            PacketDistributor.sendToServer(new ServerboundAngelShaderAbilityPacket());
        }
    }

    @SubscribeEvent
    public static void increaseCounter(ClientTickEvent.Post event) {
        if (EyeTransitionOverlay.eyeTransitionCounter >= 0) {
            if (EyeTransitionOverlay.eyeTransitionCounter < EyeTransitionOverlay.FRAME_TEXTURES.length) {
                EyeTransitionOverlay.eyeTransitionCounter++;
            }
        }
        if (EndTextOverlay.textCounter >= 0) {
            if (EndTextOverlay.textCounter < EndTextOverlay.FRAME_TEXTURES.length * 60) {
                EndTextOverlay.textCounter++;
            }
        }
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(HolyHellKeyBinds.VISION_ABILITY_KEY);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        HolyHellItemProperties.addCustomItemProperties();

        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGSV, UpperRingRenderLayer::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, LowerRingRenderLayer::getTexturedModelData);


        event.registerLayerDefinition(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::createBodyLayer);
//        event.registerLayerDefinition(HolyHellModelLayers.FALLING_SWORD, FallingSwordModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.FIREBALL, FireBallModel::createBodyLayer);
//        event.registerLayerDefinition(HolyHellModelLayers.SWORD_CROSS, SwordCrossModel::getTexturedModelData);


        event.registerLayerDefinition(HolyHellModelLayers.HERETIC, HereticModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.ANGEL, AngelModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.KAMIKAZE_ANGEL, KamikazeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.BAB, BabOneModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB1, BabTwoModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB2, BabThreeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_SPIRIT, HolySpiritModel::getTexturedModelData);
        //      event.registerLayerDefinition(HolyHellModelLayers.PALLADIN, PalladinModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.CHERUB, CherubModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_COW, HolyCowModel::createBodyLayer);
        //      event.registerLayerDefinition(HolyHellModelLayers.DEVOUT, DevoutModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void renderShader(RenderLevelStageEvent event) {
        Player player = Minecraft.getInstance().player;

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            GameRenderer renderer = Minecraft.getInstance().gameRenderer;
            if (player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {
                HolyhellModClient.attemptLoadShader(HolyhellModClient.ANGEL_VISION_SHADER);
            } else if (renderer.currentEffect() != null && HolyhellModClient.ANGEL_VISION_SHADER.toString().equals(renderer.currentEffect().getName())) {
                renderer.checkEntityPostEffect(null);
            }

        }
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {

        event.registerSpriteSet(HolyhellParticles.HOSTILE_LOCATOR.get(), HostileLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.BOSS_LOCATOR.get(), BossLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.NEUTRAL_LOCATOR.get(), NeutralLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.PEACEFUL_LOCATOR.get(), PeacefulLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.PLAYER_LOCATOR.get(), PlayerLocatorParticle.Provider::new);


        event.registerSpriteSet(HolyhellParticles.LIGHT_RING.get(), LightRingParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.SOUND_RING.get(), SoundRingParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.STUN.get(), StunParticle1.Provider::new);
        event.registerSpriteSet(HolyhellParticles.STUN2.get(), StunParticle2.Provider::new);

        event.registerSpriteSet(HolyhellParticles.SWEEP_ATTACK.get(), AttackSweepParticle.Provider::new);

        event.registerSpriteSet(HolyhellParticles.EYE0.get(), EyeParticle0.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE1.get(), EyeParticle1.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE2.get(), EyeParticle2.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE3.get(), EyeParticle3.Provider::new);

        event.registerSpriteSet(HolyhellParticles.KAMIKAZE_EXPLOSION.get(), KamikazeExplosionParticle.Provider::new);
    }


}
