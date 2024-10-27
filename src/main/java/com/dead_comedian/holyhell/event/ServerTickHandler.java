package com.dead_comedian.holyhell.event;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.dead_comedian.holyhell.block.DiviningTableBlock;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.entity.custom.other.FallingSwordEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffects;
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
        List<ServerPlayerEntity> list = world.getPlayers();
        for (PlayerEntity i : list) {
            if (i.hasStatusEffect(HolyHellEffects.ENLIGHTENED)) {
                tick++;
                if (tick >= 300) {

                    if (i.hasStatusEffect(StatusEffects.BAD_OMEN) && Objects.requireNonNull(i.getStatusEffect(StatusEffects.BAD_OMEN)).getAmplifier() == 1) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        i.getWorld().spawnEntity(swordEntity);
                        swordEntity.setLevel(1);
                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }
                    else if (i.hasStatusEffect(StatusEffects.BAD_OMEN) && Objects.requireNonNull(i.getStatusEffect(StatusEffects.BAD_OMEN)).getAmplifier() == 2) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        i.getWorld().spawnEntity(swordEntity);
                        swordEntity.setLevel(2);
                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }
                    else if (i.hasStatusEffect(StatusEffects.BAD_OMEN) && Objects.requireNonNull(i.getStatusEffect(StatusEffects.BAD_OMEN)).getAmplifier() == 3) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        i.getWorld().spawnEntity(swordEntity);
                        swordEntity.setLevel(3);
                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }
                    else if (i.hasStatusEffect(StatusEffects.BAD_OMEN) && Objects.requireNonNull(i.getStatusEffect(StatusEffects.BAD_OMEN)).getAmplifier() == 4) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        i.getWorld().spawnEntity(swordEntity);
                        swordEntity.setLevel(4);
                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }
                    else if (i.hasStatusEffect(StatusEffects.BAD_OMEN) && Objects.requireNonNull(i.getStatusEffect(StatusEffects.BAD_OMEN)).getAmplifier() == 5) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        swordEntity.setLevel(5);
                        i.getWorld().spawnEntity(swordEntity);

                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }
                    else if (!i.hasStatusEffect(StatusEffects.BAD_OMEN)){
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.getWorld());
                        i.getWorld().spawnEntity(swordEntity);
                        swordEntity.setLevel(0);
                        swordEntity.refreshPositionAndAngles(d, e, f, i.getYaw(), i.getPitch());
                        tick = 0;
                    }

                }
            }

        }
    }
}


