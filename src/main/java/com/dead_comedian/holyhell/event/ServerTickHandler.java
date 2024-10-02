package com.dead_comedian.holyhell.event;

import java.util.List;
import java.util.Random;

import com.dead_comedian.holyhell.block.DiviningTableBlock;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;


public class ServerTickHandler implements ServerTickEvents.EndWorldTick {
    int tick = 0;

    @Override
    public void onEndTick(ServerWorld world) {


        Random rand = new Random();
        World world1;

        List<ServerPlayerEntity> list = world.getPlayers();
        for (PlayerEntity i : list) {
            if (i.hasStatusEffect(HolyHellEffects.ENLIGHTENED)) {
                tick++;

                System.out.println(tick);
                if (tick >= 300) {

                    double d = i.getX() + (rand.nextInt(5) - 0.5) *     10.0;
                    double e = i.getY();
                    double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;

                    HolyHellEntities.LIGHT_BEAM.spawn(world, new BlockPos((int) d, (int) e, (int) f), SpawnReason.EVENT);
                    BlockPos blockPos = i.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, i.getWorld());
                    i.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.add((int) d, (int) e, (int) f), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());

                    tick = 0;
                }
            }
        }

    }
}



