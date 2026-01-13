package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.event.EyeTransitionOverlay;
import com.dead_comedian.holyhell.client.renderer.*;
import com.dead_comedian.holyhell.client.renderer.non_living.FireBallRenderer;
import com.dead_comedian.holyhell.client.renderer.non_living.GlobularDomeRenderer;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.render_layer.UpperRingRenderLayer;
import com.dead_comedian.holyhell.networking.ServerboundAngelShaderAbilityPacket;
import com.dead_comedian.holyhell.networking.ServerboundExplosionShaderAbilityPacket;
import com.dead_comedian.holyhell.server.registries.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

@Mod(value = Holyhell.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Holyhell.MOD_ID, value = Dist.CLIENT)
public class HolyhellModClient {

    public static final ResourceLocation ANGEL_VISION_SHADER = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "shaders/post/angel_vision.json");
    public static int loadShaderAttempt = 0;
    public static int staticTimer = 0;


    public HolyhellModClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        HolyHellItemProperties.addCustomItemProperties();


        EntityRenderers.register(HolyHellEntities.ANGEL.get(), AngelRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_ONE.get(), BabOneRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_TWO.get(), BabTwoRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_THREE.get(), BabThreeRenderer::new);
        EntityRenderers.register(HolyHellEntities.CHERUB.get(), CherubRenderer::new);
        EntityRenderers.register(HolyHellEntities.HERETIC.get(), HereticRenderer::new);
        EntityRenderers.register(HolyHellEntities.HOLY_COW.get(), HolyCowRenderer::new);
        EntityRenderers.register(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritRenderer::new);
        EntityRenderers.register(HolyHellEntities.KAMIKAZE.get(), KamikazeRenderer::new);

        EntityRenderers.register(HolyHellEntities.ALL_SEER.get(), AllSeerRenderer::new);

        EntityRenderers.register(HolyHellEntities.BLINDING_BOMB.get(), ThrownItemRenderer::new);
        EntityRenderers.register(HolyHellEntities.FIREBALL.get(), FireBallRenderer::new);
        EntityRenderers.register(HolyHellEntities.GLOBULAR_DOME.get(), GlobularDomeRenderer::new);
    }


    @SubscribeEvent
    public static void renderShader(RenderLevelStageEvent event) {
        Player player = Minecraft.getInstance().player;

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            GameRenderer renderer = Minecraft.getInstance().gameRenderer;
            if (player.getData(HolyHellAttachments.VISION_SHADER)) {
                HolyhellModClient.attemptLoadShader(HolyhellModClient.ANGEL_VISION_SHADER);
            } else if (renderer.currentEffect() != null && HolyhellModClient.ANGEL_VISION_SHADER.toString().equals(renderer.currentEffect().getName())) {
                renderer.checkEntityPostEffect(null);
            }

        }
    }
    public static void attemptLoadShader(ResourceLocation effect) {
        GameRenderer renderer = Minecraft.getInstance().gameRenderer;
        if (loadShaderAttempt <= 0) {
            renderer.loadEffect(effect);
            if (!ANGEL_VISION_SHADER.toString().equals((renderer.currentEffect().getName()))) {
                loadShaderAttempt = 12000;
                Holyhell.LOGGER.warn("Failed to load shader {}", effect);
            }
        }
    }





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
            if (player.getData(HolyHellAttachments.VISION_SHADER)) {
                if (staticTimer % 202 == 0) {
                    level.playLocalSound(player, HolyHellSounds.STATIC_AMBIENT.get(), SoundSource.AMBIENT, 1F, 1);

                }
            }
        }
    }

    @SubscribeEvent
    public static void obfuscationRenderer(ClientTickEvent.Pre event){
        Player player = Minecraft.getInstance().player;
        if(player!=null){
            if(!player.getData(HolyHellAttachments.VISION_SHADER) && player.hasEffect(HolyHellEffects.CONFUSION)){
                Level level = player.level();

                AABB userHitbox = new AABB(player.blockPosition()).inflate(100);

                List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, userHitbox);
                for (LivingEntity i : list) {
                    if (i instanceof Monster && !i.getType().is(HolyhellTags.Entities.MINIBOSS) && !i.getType().is(HolyhellTags.Entities.BOSS)) {
                        level.addParticle(HolyhellParticles.OBFUSCATION.get(), i.getX(), i.getY() + i.getHitbox().getYsize() / 2, i.getZ(), 0, 0, 0);
                    }
                }

                list.removeAll(list);

            }
        }
    }

    @SubscribeEvent
    public static void locatorParticles(ClientTickEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getData(HolyHellAttachments.VISION_SHADER)) {
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
        if (HolyHellKeyBinds.RING_ABILITY_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new ServerboundExplosionShaderAbilityPacket());        }
    }
}
