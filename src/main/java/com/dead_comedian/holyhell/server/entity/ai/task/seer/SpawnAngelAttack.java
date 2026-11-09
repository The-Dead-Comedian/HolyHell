package com.dead_comedian.holyhell.server.entity.ai.task.seer;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.helper.WaveSpawner;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpawnAngelAttack extends Behavior<AllSeerEntity> {
    public SpawnAngelAttack() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ATTACK_INDEX.get(), MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ENERGY.get(), MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, AllSeerEntity owner) {

        return owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 5;
    }

    @Override
    protected boolean canStillUse(ServerLevel level, AllSeerEntity entity, long gameTime) {
        return a;
    }

    boolean a = true;
    boolean b = true;
    boolean c = false;

    @Override
    protected void tick(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.tick(level, owner, gameTime);

        if (owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).isPresent()) {
            List<Entity> mobs = new ArrayList<>();

            if (b) {
                mobs = WaveSpawner.spawnWave(level, owner.blockPosition().below(9), (int) (1.5 * owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).get()));
                b = false;
                c = true;
            }


            if (!mobs.isEmpty()) {
                mobs.removeIf(entity -> !entity.isAlive());
            } else {
                if (c) {
                    a = false;
                }
            }
        }


    }

    @Override
    protected void stop(ServerLevel level, AllSeerEntity entity, long gameTime) {
        super.stop(level, entity, gameTime);

        Brain<AllSeerEntity> brain = entity.getBrain();

        if (brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 5 &&
                brain.getMemory(HolyHellMemoryModules.ENERGY.get()).isPresent()) {
//            brain.eraseMemory(HolyHellMemoryModules.ATTACK_INDEX.get());
            int i = brain.getMemory(HolyHellMemoryModules.ENERGY.get()).get() - 1;
            brain.setMemory(HolyHellMemoryModules.ENERGY.get(), i);
            brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 100);
        }
    }
}
