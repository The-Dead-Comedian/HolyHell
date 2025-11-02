package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellCriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


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
    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(DeathHandler.class);
    }

}
