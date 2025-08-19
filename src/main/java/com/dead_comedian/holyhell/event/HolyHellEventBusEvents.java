package com.dead_comedian.holyhell.event;



import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.*;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellCriteriaTriggers;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellEventBusEvents {

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
            if(isAprilFools()){
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
                            HolyHellCriteriaTriggers.KILLED_BY_CROSS.trigger(((ServerPlayer) (Object) player));
                        }
                    }
                }
            }
        }
        System.out.println("Work");
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HolyHellEntities.ANGEL.get(), AngelEntity.createAngelAttributes().build());
        event.put(HolyHellEntities.KAMIKAZE.get(), KamikazeEntity.createKamikazeAttributes().build());
        event.put(HolyHellEntities.HERETIC.get(), HereticEntity.createHereticAttributes().build());
        event.put(HolyHellEntities.BAB_ONE.get(), BabOneEntity.createBabOneAttributes().build());
        event.put(HolyHellEntities.BAB_TWO.get(), BabTwoEntity.createBabTwoAttributes().build());
        event.put(HolyHellEntities.BAB_THREE.get(), BabThreeEntity.createBabThreeAttributes().build());
        event.put(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritEntity.createHolySpiritAttributes().build());
        event.put(HolyHellEntities.CHERUB.get(), CherubEntity.createCherubAttributes().build());
        event.put(HolyHellEntities.HOLY_COW.get(), HolyCowEntity.createHolyCowAttributes().build());
//        event.put(HolyHellEntities.DEVOUT.get(), DevoutEntity.createDevoutAttributes().build());
//        event.put(HolyHellEntities.PALLADIN.get(), PalladinEntity.createPalladinAttributes().build());

    }
}