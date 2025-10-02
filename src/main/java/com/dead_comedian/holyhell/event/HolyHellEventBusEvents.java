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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellEventBusEvents {

    public static int paranoiaTimer;
    public static int paranoiaAmp;
    public static int secTillText = 40;


    @SubscribeEvent
    public static void flyInAngel(LevelTickEvent.Post event) {

        Player player = Minecraft.getInstance().player;
        if (Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().player != null) {
                if (player.level().dimension() == HolyhellDimensions.ANGEL) {
                    player.setData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA, false);

                    player.getAbilities().flying = true;
                }
            }
        }
    }


    @SubscribeEvent
    public static void teleportPlayer(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        List<ServerPlayer> players = new ArrayList<>(serverLevel.players());

        for (ServerPlayer player : players) {
            if (player.level().dimension() == Level.END && player.getY() <= -50 && Minecraft.getInstance().player != null) {
                ServerLevel targetLevel = event.getLevel().getServer().getLevel(HolyhellDimensions.ANGEL);
                if (Minecraft.getInstance().player.getData(HolyHellAttachments.CAN_TP_TO_ANGEL)) {
                    if (targetLevel != null) {
                        player.changeDimension(new DimensionTransition(
                                targetLevel,
                                player.position(),
                                player.getDeltaMovement(),
                                Direction.WEST.toYRot(),
                                player.getXRot(),
                                DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)
                        ));
                        

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
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 20, 0));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 0) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 50, 1));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 1) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 50, 2));
                } else if (paranoiaTimer == 0 && paranoiaAmp == 2) {
                    player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 500, 3));
                }

                if (paranoiaAmp == 3 && !player.getData(HolyHellAttachments.CAN_TP_TO_ANGEL)) {
                    if (secTillText > 0) {
                        secTillText--;
                    } else {
                        if (EndTextOverlay.textCounter == 185) {
                            EndTextOverlay.textCounter = 0;
                            player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, true);
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
    public static void stopSounds(LevelTickEvent.Post event) {

        if (event.getLevel() instanceof ServerLevel serverLevel) {
            for (ServerPlayer player : serverLevel.players()) {
                if (!player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {

                    ClientboundStopSoundPacket stopSoundS2CPacket = new ClientboundStopSoundPacket(HolyHellSounds.STATIC_AMBIENT.get().getLocation(), SoundSource.RECORDS);
                    player.connection.send(stopSoundS2CPacket);
                }
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
    public static void onLivingHealEvent(EntityJoinLevelEvent event) {
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

    private static boolean isAprilFools() {
        LocalDate localdate = LocalDate.now();
        int i = localdate.get(ChronoField.DAY_OF_MONTH);
        int j = localdate.get(ChronoField.MONTH_OF_YEAR);
        return j == 4 && i <= 2;
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
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
    public static void livingEntityDie(LivingDeathEvent event) {


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