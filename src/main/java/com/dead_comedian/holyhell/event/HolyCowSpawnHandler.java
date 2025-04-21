package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.entity.BabTwoEntity;
import com.dead_comedian.holyhell.entity.HolyCowEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class HolyCowSpawnHandler {
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
    public static void register() {
        MinecraftForge.EVENT_BUS.register(HolyCowSpawnHandler.class);
    }
}
