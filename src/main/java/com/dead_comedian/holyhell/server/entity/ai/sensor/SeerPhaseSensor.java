package com.dead_comedian.holyhell.server.entity.ai.sensor;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.RevenantEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;

import java.util.Set;

public class SeerPhaseSensor extends Sensor<AllSeerEntity> {
    @Override
    protected void doTick(ServerLevel level, AllSeerEntity entity) {
        if (entity.getHealth() > 200) {
            entity.getBrain().setMemory(HolyHellMemoryModules.PHASE.get(), 1);
        } else if (entity.getHealth() > 100) {
            entity.getBrain().setMemory(HolyHellMemoryModules.PHASE.get(), 2);
        } else if (entity.getHealth() > 0) {
            entity.getBrain().setMemory(HolyHellMemoryModules.PHASE.get(), 3);
        }
    }

    @Override
    public Set<MemoryModuleType<?>> requires() {
        return Set.of(HolyHellMemoryModules.PHASE.get());
    }
}
