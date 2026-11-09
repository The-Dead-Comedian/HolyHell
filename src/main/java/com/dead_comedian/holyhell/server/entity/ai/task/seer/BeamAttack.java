package com.dead_comedian.holyhell.server.entity.ai.task.seer;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.entity.non_living.LightBeamEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class BeamAttack extends Behavior<AllSeerEntity> {
    public BeamAttack() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ATTACK_INDEX.get(), MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ENERGY.get(), MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, AllSeerEntity owner) {

        return owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 1;
    }

    private int wave;
    private int counter;
    private int totalWaves;

    private int cooldownFor(int phase) {
        float divisor = (4 - phase) / 2f;
        return divisor > 0 ? Math.max(1, (int) (20 / divisor)) : 1;
    }

    @Override
    protected void start(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.start(level, owner, gameTime);
        int phase = owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).orElse(1);
        wave = 0;
        totalWaves = phase + 2;
        counter = 0;
    }

    @Override
    protected boolean canStillUse(ServerLevel level, AllSeerEntity owner, long gameTime) {
        return wave < totalWaves;
    }

    @Override
    protected void tick(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.tick(level, owner, gameTime);

        if (counter > 0) {
            counter--;
            return;
        }

        int phase = owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).orElse(1);
        int beamCount = 8 * phase;
        double radius = 4.0;

        double waveOffset = wave * (Math.PI / beamCount);

        for (int j = 0; j < beamCount; j++) {
            double angle = (Math.PI * 2 / beamCount) * j + waveOffset;

            double x = owner.getX() + Math.cos(angle) * radius;
            double z = owner.getZ() + Math.sin(angle) * radius;

            LightBeamEntity beam = new LightBeamEntity(HolyHellEntities.LIGHTBEAM.get(), level, owner);
            beam.moveTo(x, owner.getY() - 11, z, 0, 0);

            beam.setXRot((float) angle);

            double speed = 0.5 * owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).get();
            beam.moveTo(owner.getX() + Math.cos(angle) * radius, owner.getY() - 11,
                    owner.getZ() + Math.sin(angle) * radius, 0, 0);
            beam.setDeltaMovement(Math.cos(angle) * speed, 0, Math.sin(angle) * speed);
            level.addFreshEntity(beam);

        }

        wave++;
        counter = cooldownFor(phase);
    }

    @Override
    protected void stop(ServerLevel level, AllSeerEntity entity, long gameTime) {
        super.stop(level, entity, gameTime);

        Brain<AllSeerEntity> brain = entity.getBrain();

        if (brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 1 &&
                brain.getMemory(HolyHellMemoryModules.ENERGY.get()).isPresent()) {
//            brain.eraseMemory(HolyHellMemoryModules.ATTACK_INDEX.get());
            int i = brain.getMemory(HolyHellMemoryModules.ENERGY.get()).get() - 1;
            brain.setMemory(HolyHellMemoryModules.ENERGY.get(), i);
            brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 100);
        }
    }
}
