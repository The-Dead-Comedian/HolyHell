package com.dead_comedian.holyhell.event;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.renderer.overlay.EndTextOverlay;
import com.dead_comedian.holyhell.entity.*;
import com.dead_comedian.holyhell.networking.HolyHellMessages;
import com.dead_comedian.holyhell.registries.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static com.dead_comedian.holyhell.Holyhell.LOGGER;


@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellEventBusEvents {

    public static int paranoiaTimer;
    public static int paranoiaAmp;
    public static int secTillText = 40;
    public static int musicDuration = 1660;
    public static int musicCooldown;

    public static List<? extends AllSeerEntity> getEyes(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel) {
            return ((ServerLevel) (Object) (event.getLevel())).<AllSeerEntity>getEntities(HolyHellEntities.ALL_SEER.get(), LivingEntity::isAlive);
        }
        return null;
    }

    private static boolean isAprilFools() {
        LocalDate localdate = LocalDate.now();
        int i = localdate.get(ChronoField.DAY_OF_MONTH);
        int j = localdate.get(ChronoField.MONTH_OF_YEAR);
        return j == 4 && i <= 2;
    }

    @SubscribeEvent
    public static void inAngel(LevelTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if (Minecraft.getInstance().level != null) {
            if (player != null) {
                if (player.level().dimension() == HolyhellDimensions.ANGEL) {

                    if (musicDuration == 1660) {
                        player.level().playLocalSound(player, HolyHellSounds.HEAVENS_VOICE.get(), SoundSource.MUSIC, 1, 1);

                    }
                    if (musicDuration <= 1660) {
                        musicDuration--;
                        Minecraft.getInstance().getMusicManager().stopPlaying();
                    }
                    if (musicDuration <= 0) {
                        musicCooldown = player.level().getRandom().nextInt(200, 9000);
                        musicDuration = 1;
                    }
                    if (musicCooldown <= 0 && musicDuration == 1) {
                        musicDuration = 1660;
                    }

                    player.setData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA, false);

                    player.getAbilities().flying = true;
                }
            }
        }
    }

    @SubscribeEvent
    public static void spawnAllSeer(LevelTickEvent.Post event) {

        if (event.getLevel().dimension() == HolyhellDimensions.ANGEL) {
            List<? extends AllSeerEntity> list = getEyes(event);

            if (list != null) {
                if (list.isEmpty()) {
                    LOGGER.debug("Haven't seen the All Seer , respawning it");
                    event.getLevel().getChunkAt(new BlockPos(0, 0, 0));
                    AllSeerEntity allSeerEntity = HolyHellEntities.ALL_SEER.get().create(event.getLevel());
                    if (allSeerEntity != null) {
                        event.getLevel().addFreshEntity(allSeerEntity);
                    }
                } else if (list.size() >= 2) {

                    for (AllSeerEntity allSeerEntity : list) {
                        if (allSeerEntity != list.get(0)) {
                            allSeerEntity.discard();
                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void teleportPlayer(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        Player playerooni = Minecraft.getInstance().player;
        List<ServerPlayer> players = new ArrayList<>(serverLevel.players());

        for (ServerPlayer player : players) {
            if (player.level().dimension() == Level.END && player.getY() <= -50 && playerooni != null) {
                ServerLevel targetLevel = event.getLevel().getServer().getLevel(HolyhellDimensions.ANGEL);
                if (playerooni.getData(HolyHellAttachments.CAN_TP_TO_ANGEL)) {
                    if (targetLevel != null) {
                        playerooni.removeEffect(HolyHellEffects.ANGELIC_VISION);
                        player.removeEffect(HolyHellEffects.ANGELIC_VISION);
                        player.changeDimension(new DimensionTransition(targetLevel, new Vec3(20, 12, 0), player.getDeltaMovement(), Direction.EAST.toYRot(), player.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)));
                        playerooni.setData(HolyHellAttachments.CAN_TP_TO_ANGEL, false);

                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void paranoiaTimer(LevelTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {
                if (player.hasEffect(HolyHellEffects.PARANOIA)) {
                    paranoiaTimer = player.getEffect(HolyHellEffects.PARANOIA).getDuration();
                    paranoiaAmp = player.getEffect(HolyHellEffects.PARANOIA).getAmplifier();
                } else {
                    paranoiaTimer++;
                }


                if (paranoiaTimer == 300 && !player.hasEffect(HolyHellEffects.PARANOIA)) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 200, 0));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 0) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 300, 1));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 1) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 400, 2));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 2) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 500, 3));
                }

                if (paranoiaAmp == 3 && player.level().dimension() == Level.END) {
                    if (!player.getData(HolyHellAttachments.CAN_TP_TO_ANGEL)) {
                        if (secTillText > 0) {
                            secTillText--;
                        } else {
                            if (EndTextOverlay.textCounter == 185) {
                                EndTextOverlay.textCounter = 0;
                                player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, true);
                            }
                        }
                    }
                }


            } else {
                if (player.hasEffect(HolyHellEffects.PARANOIA)) {
                    player.removeEffect(HolyHellEffects.PARANOIA);
                }
                paranoiaTimer = 0;
            }
        }
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        HolyHellMessages.register(event);
    }

    @SubscribeEvent
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(HolyHellEffects.BLOODLUST)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void spawnHolyCow(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Cow cow) {
            if (isAprilFools()) {
                cow.discard();
                BlockPos blockPos = cow.blockPosition();
                HolyCowEntity holyCowEntity = new HolyCowEntity(HolyHellEntities.HOLY_COW.get(), cow.level());
                cow.level().addFreshEntity(holyCowEntity);
                holyCowEntity.moveTo(blockPos, holyCowEntity.getYRot(), holyCowEntity.getXRot());
            }
        }
    }

    @SubscribeEvent
    public static void triggerFallingCrossAchievement(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            DamageSource damageSource = event.getSource();

            if (damageSource.is(DamageTypes.FALLING_BLOCK)) {
                Entity directEntity = damageSource.getDirectEntity();

                if (directEntity instanceof FallingBlockEntity fallingBlock) {
                    BlockState blockState = fallingBlock.getBlockState();

                    if (blockState.getBlock() == HolyHellBlocks.FALLING_CROSS.get()) {
                        if (player instanceof ServerPlayer) {
                            HolyHellCriteriaTriggers.KILLED_BY_CROSS.get().trigger(((ServerPlayer) (Object) player));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void giveBuffsFromKatar(LivingDeathEvent event) {

        LivingEntity entity = event.getEntity();
        LivingEntity entity1 = (LivingEntity) event.getSource().getEntity();
        if (entity == Minecraft.getInstance().player && entity.hasEffect(HolyHellEffects.BLOODLUST)) {
            entity.setHealth(20);
        }


        if (entity instanceof LivingEntity && entity1 != null && entity != entity1) {
            if (entity1.hasEffect(HolyHellEffects.BLOODLUST)) {
                entity1.setHealth((float) (entity1.getHealth() + entity.getAttribute(Attributes.MAX_HEALTH).getBaseValue() * 0.3F));


                if (entity instanceof RangedAttackMob) {
                    entity1.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));

                }
                if (entity instanceof FlyingAnimal) {
                    entity1.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 1));

                }
                if (entity instanceof Monster) {
                    entity1.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));

                }
                if (entity instanceof Animal) {
                    entity1.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 1));

                }
            }
        }

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HolyHellEntities.ANGEL.get(), AngelEntity.createAttributes().build());
        event.put(HolyHellEntities.ALL_SEER.get(), AllSeerEntity.createAttributes().build());
        event.put(HolyHellEntities.BAB_ONE.get(), BabOneEntity.createAttributes().build());
        event.put(HolyHellEntities.BAB_TWO.get(), BabTwoEntity.createAttributes().build());
        event.put(HolyHellEntities.BAB_THREE.get(), BabThreeEntity.createAttributes().build());
        event.put(HolyHellEntities.CHERUB.get(), CherubEntity.createAttributes().build());
        event.put(HolyHellEntities.HERETIC.get(), HereticEntity.createAttributes().build());
        event.put(HolyHellEntities.HOLY_COW.get(), HolyCowEntity.createAttributes().build());
        event.put(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritEntity.createAttributes().build());
        event.put(HolyHellEntities.KAMIKAZE.get(), KamikazeEntity.createAttributes().build());
    }
}