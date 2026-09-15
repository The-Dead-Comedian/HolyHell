package com.dead_comedian.holyhell.server.entity.ai.task.revenant;

import com.dead_comedian.holyhell.server.entity.RevenantEntity;
import com.dead_comedian.holyhell.server.entity.ai.RevenantStates;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class SitUp extends Behavior<RevenantEntity> {

    public static final int DURATION = 21;

    public SitUp() {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), DURATION);
    }


    @Override
    protected void start(ServerLevel level, RevenantEntity entity, long gameTime) {
        super.start(level, entity, gameTime);
        entity.setState(RevenantStates.SIT_UP);
        entity.playSound(HolyHellSounds.REVENANT_RISE.get());
    }

    @Override
    protected void stop(ServerLevel level, RevenantEntity entity, long gameTime) {
        super.stop(level, entity, gameTime);
        entity.setState(RevenantStates.UNARMED);
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, RevenantEntity owner) {
        return owner.getState().getId() == 0 && owner.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent();
    }

    @Override
    protected boolean canStillUse(ServerLevel level, RevenantEntity entity, long gameTime) {
        return true;
    }
}
