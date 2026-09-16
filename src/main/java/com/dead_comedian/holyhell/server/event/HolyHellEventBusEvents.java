package com.dead_comedian.holyhell.server.event;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.event.EndTextOverlay;
import com.dead_comedian.holyhell.networking.ServerboundTpToAngelPacket;
import com.dead_comedian.holyhell.server.data.StatueData;
import com.dead_comedian.holyhell.server.entity.*;
import com.dead_comedian.holyhell.server.entity.non_living.GlobularDomeEntity;
import com.dead_comedian.holyhell.server.registries.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@EventBusSubscriber(modid = HolyHell.MOD_ID)
public class HolyHellEventBusEvents {

    private static int paranoiaTimer;
    private static int paranoiaAmp;
    private static int secTillText = 40;
    private static int cooldown = 0;
    private static boolean textSequenceTriggered = false;

    public static void setCooldown(int cooldown) {
        HolyHellEventBusEvents.cooldown = cooldown;
    }

    @SubscribeEvent
    public static void block(LivingShieldBlockEvent event) {
        Entity entity = event.getDamageSource().getDirectEntity();
        if (entity instanceof RevenantEntity && event.getBlocked()) {
            if (((RevenantEntity) entity).getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent()) {
                Entity targetEntity = ((RevenantEntity) entity).getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();
                double d0 = targetEntity.getX() - entity.getX();
                double d1 = targetEntity.getZ() - entity.getZ();
                double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
                targetEntity.push(d0 / d2 * 4.0, 0.2, d1 / d2 * 4.0);
            }
        }
    }

    @SubscribeEvent
    public static void changePumpkinFace(PlayerInteractEvent.RightClickBlock event) {
        Block pumpkin = event.getLevel().getBlockState(event.getPos()).getBlock();
        if (event.getItemStack().is(Tags.Items.TOOLS_SHEAR)) {

            if (pumpkin == Blocks.CARVED_PUMPKIN) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.CARVED_PUMPKIN_EYE.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.CARVED_PUMPKIN_EYE.get()) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.CARVED_PUMPKIN_CROSS.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.CARVED_PUMPKIN_CROSS.get()) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), Blocks.CARVED_PUMPKIN.defaultBlockState(), 11);
            }

            if (pumpkin == Blocks.JACK_O_LANTERN) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.JACK_O_LANTERN_EYE.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.JACK_O_LANTERN_EYE.get()) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.JACK_O_LANTERN_CROSS.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.JACK_O_LANTERN_CROSS.get()) {
                event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                event.getLevel().setBlock(event.getPos(), Blocks.JACK_O_LANTERN.defaultBlockState(), 11);
            }
        }
    }

    @SubscribeEvent
    public static void criticalHitEvent(CriticalHitEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);

            if (player.getItemInHand(InteractionHand.MAIN_HAND).is(HolyHellItems.HOLY_GRAIL.get())) {
                if (itemStack.get(HolyhellDataComps.GRAIL_LEVEL) != null) {
                    itemStack.set(HolyhellDataComps.GRAIL_LEVEL, itemStack.get(HolyhellDataComps.GRAIL_LEVEL) + 1);
                } else {
                    itemStack.set(HolyhellDataComps.GRAIL_LEVEL, 0);
                }
            }

        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();


        //Globular Dome
        List<Entity> entityBelow = level.getEntities(player, player.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (player.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                player.setSpeed(0);
            }
        }

        //Religious Rings
        if (player.hasEffect(HolyHellEffects.DIVINE_PROTECTION)) {
            MobEffectInstance effect = player.getEffect(HolyHellEffects.DIVINE_PROTECTION);
            if (effect.getDuration() > 3.5 * 20) {
                if (player.tickCount % 70 == 1) {
                    player.level().playSound((Player) null, player.blockPosition(), HolyHellSounds.RINGS_HOLD.get(), SoundSource.PLAYERS, 0.2f, 1);
                }
            }
        }


        // TO-DO:  change music system

        player.setData(HolyHellAttachments.RENDER_RINGS, player.hasEffect(HolyHellEffects.DIVINE_PROTECTION));

        if (player.hasEffect(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN)){
            cooldown++;
            player.setData(HolyHellAttachments.RENDER_RINGS_BREAK, cooldown <= 20);

        } else {
            player.setData(HolyHellAttachments.RENDER_RINGS_BREAK, false);
            setCooldown(0);
        }

        if (level.dimension() == HolyHellDimensions.ANGEL) {

            player.getAbilities().mayBuild = false;
            player.setData(HolyHellAttachments.VISION_SHADER, false);
        } else {
            player.getAbilities().mayBuild = true;
        }

        if(level.isClientSide){
            System.out.println("c: "+player.getData(HolyHellAttachments.TP_TO_ANGEL));
        }
        else {
            System.out.println("s: "+player.getData(HolyHellAttachments.TP_TO_ANGEL));
        }


        // Teleport player
        if (player.level().dimension() == Level.END && player.blockPosition().getY() < -50) {
            if (level instanceof ServerLevel serverLevel) {
                if (player.getData(HolyHellAttachments.TP_TO_ANGEL)) {
                    ServerLevel targetLevel = serverLevel.getServer().getLevel(HolyHellDimensions.ANGEL);
                    if (targetLevel != null) {
                        player.changeDimension(new DimensionTransition(
                                targetLevel,
                                new Vec3(-10.5, 126, -11.5),
                                Vec3.ZERO,
                                player.getYRot(),
                                player.getXRot(),
                                DimensionTransition.PLAY_PORTAL_SOUND
                                        .then(DimensionTransition.PLACE_PORTAL_TICKET)
                        ));
                        player.setData(HolyHellAttachments.TP_TO_ANGEL, player.level().dimension() != HolyHellDimensions.ANGEL);
                    }
                }
            }
        }


        if (player.getData(HolyHellAttachments.VISION_SHADER)) {

            //paranoia timer

            if (player.hasEffect(HolyHellEffects.PARANOIA)) {
                paranoiaTimer = player.getEffect(HolyHellEffects.PARANOIA).getDuration();
                paranoiaAmp = player.getEffect(HolyHellEffects.PARANOIA).getAmplifier();
            } else {
                paranoiaTimer++;
            }


            if (paranoiaTimer == 300 && !player.hasEffect(HolyHellEffects.PARANOIA)) {
                player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 100, 0));
            } else if (paranoiaTimer == 0 && paranoiaAmp == 0) {
                player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 100, 1));
            } else if (paranoiaTimer == 0 && paranoiaAmp == 1) {
                player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 100, 2));
            } else if (paranoiaTimer == 0 && paranoiaAmp == 2) {
                player.addEffect(new MobEffectInstance(HolyHellEffects.PARANOIA, 400, 3));
            }

            if (paranoiaAmp == 3 && level.dimension() == Level.END && isLookingIntoVoidInEnd(player)) {
                if (secTillText > 0) {
                    secTillText--;
                } else {
                    if (!textSequenceTriggered) {
                        if (level.isClientSide()) {
                            PacketDistributor.sendToServer(new ServerboundTpToAngelPacket());
                        }
                        player.setData(HolyHellAttachments.TP_TO_ANGEL, player.level().dimension() != HolyHellDimensions.ANGEL);
                        EndTextOverlay.textCounter = 1;
                        EndTextOverlay.extraGraceTime = 60;
                        player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, true);
                        textSequenceTriggered = true; // consumed — won't fire again until re-armed below
                    }
                }
            } else {
                player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, false);
                secTillText = 40;
                textSequenceTriggered = false;
            }


        } else {

            if (player.hasEffect(HolyHellEffects.PARANOIA)) {
                player.removeEffect(HolyHellEffects.PARANOIA);
            }
        }
    }


    public static boolean isLookingIntoVoidInEnd(Player player) {
        if (player.level().dimension() != Level.END) {
            return false;
        }
        float pitch = player.getXRot();
        if (pitch < 0.0f) {
            return false;
        }

        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getLookAngle();
        Vec3 target = eyePos.add(lookVec.scale(256));

        ClipContext ctx = new ClipContext(eyePos, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);

        BlockHitResult hit = player.level().clip(ctx);

        boolean miss = (hit.getType() == HitResult.Type.MISS);
        boolean targetBelowVoid = (target.y < 0);

        return miss && targetBelowVoid;
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();

        //Fall Damage
        if (entity.level().dimension() == HolyHellDimensions.ANGEL) {
            if (event.getSource().is(DamageTypes.FALL)) {
                event.setNewDamage(0);
            }
        }
    }

    /// ////////////////////////

    private static boolean isAprilFools() {
        LocalDate localdate = LocalDate.now();

        Month j = localdate.getMonth();
        return j.equals(Month.APRIL);
    }

    @SubscribeEvent
    public static void spawnHolyCow(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Cow cow && !(event.getEntity() instanceof HolyCowEntity)) {
            if (isAprilFools()) {
                BlockPos blockPos = cow.blockPosition();
                HolyCowEntity holyCowEntity = new HolyCowEntity(HolyHellEntities.HOLY_COW.get(), event.getLevel());
                event.getLevel().addFreshEntity(holyCowEntity);
                holyCowEntity.moveTo(blockPos, holyCowEntity.getYRot(), holyCowEntity.getXRot());
                cow.discard();
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {

        if (event.getPlayer().level().dimension().equals(HolyHellDimensions.ANGEL) && !event.getLevel().isClientSide()) {
            event.setCanceled(true);
        }

        if (event.getLevel().getBlockState(event.getPos()).is(HolyHellTags.Blocks.REVENANT_PROTECTS)) {
            Player player = event.getPlayer();
            List<RevenantEntity> nearbyRevenant = event.getLevel().getEntitiesOfClass(RevenantEntity.class, new AABB(player.getX() + 20, player.getY() + 4, player.getZ() + 20, player.getX() - 20, player.getY() - 4, player.getZ() - 20));

            for (RevenantEntity entity : nearbyRevenant) {
                if (!player.isCreative() && !player.isSpectator()) {
                    entity.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, player);
                }
            }

        }
    }

    /// /////////////////////
    @SubscribeEvent
    public static void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(HolyHellCodecs.STATUES, StatueData.FullStatueCodec.CODEC, StatueData.FullStatueCodec.CODEC);

    }


    @SubscribeEvent
    public static void triggerFallingCrossAchievement(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            DamageSource damageSource = event.getSource();


            if (damageSource.is(DamageTypes.FALLING_BLOCK)) {
                Entity directEntity = damageSource.getDirectEntity();

                if (directEntity instanceof FallingBlockEntity fallingBlock) {

                    if (player instanceof ServerPlayer) {


                    }
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
        event.put(HolyHellEntities.REVENANT.get(), RevenantEntity.createAttributes().build());

    }


}