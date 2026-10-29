package com.dead_comedian.holyhell.server.entity.ai.task.seer;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class DetermineAttackPattern extends Behavior<AllSeerEntity> {

    public DetermineAttackPattern() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ATTACK_INDEX.get(), MemoryStatus.VALUE_ABSENT));
    }

    /*  ATTACK INDEX LEGEND

        1 - Beam attack
        2 - Spear attack
        3 - Desperation attack
        4 -

        5 - Desperate attack
        6 - Spawn Mobs

     */

    @Override
    protected boolean canStillUse(ServerLevel level, AllSeerEntity entity, long gameTime) {
        return true;
    }

    @Override
    protected void start(ServerLevel level, AllSeerEntity entity, long gameTime) {
        super.start(level, entity, gameTime);
        if (entity.getBrain().getMemory(HolyHellMemoryModules.ENERGY.get()).isEmpty() && entity.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).isPresent()) {
            entity.getBrain().setMemory(HolyHellMemoryModules.ENERGY.get(), 2 + 2 * entity.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).get());
        }
    }

    @Override
    protected void tick(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.tick(level, owner, gameTime);
        Brain<AllSeerEntity> brain = owner.getBrain();

        if (brain.getMemory(HolyHellMemoryModules.ENERGY.get()).isPresent()) {

            if (brain.getMemory(HolyHellMemoryModules.ENERGY.get()).get() > 0) {
                brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), level.random.nextInt(1, 4));
            } else {
                if (owner.getShield() > 0) {
                    brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 6);
                } else {
                    brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 5);
                }
            }

        }

    }
}
