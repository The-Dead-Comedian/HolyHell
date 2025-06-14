package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.entity.HolyCowEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellCriteriaTriggers;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DeathHandler {
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            DamageSource damageSource = event.getSource();

            if (damageSource.is(DamageTypes.FALLING_BLOCK)) {
                Entity directEntity = damageSource.getDirectEntity();

                if (directEntity instanceof FallingBlockEntity fallingBlock) {
                    BlockState blockState = fallingBlock.getBlockState();

                    if (blockState.getBlock() == HolyHellBlocks.FALLING_CROSS.get()) {
                        if (player   instanceof ServerPlayer) {
                            HolyHellCriteriaTriggers.KILLED_BY_CROSS.trigger(((ServerPlayer) (Object) player));
                        }
                    }
                }
            }
        }
        System.out.println("Work");
    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(DeathHandler.class);
    }

}
