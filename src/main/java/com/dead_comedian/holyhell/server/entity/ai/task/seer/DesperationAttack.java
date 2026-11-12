package com.dead_comedian.holyhell.server.entity.ai.task.seer;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.non_living.LightArrowEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules.ARROWS_SUMMONED;

public class DesperationAttack extends Behavior<AllSeerEntity> {
    public DesperationAttack() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ATTACK_INDEX.get(), MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ENERGY.get(), MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, AllSeerEntity owner) {

        return owner.getShield() <= 0;
    }

    @Override
    protected void start(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.start(level, owner, gameTime);
    }

    boolean a = true;

    @Override
    protected boolean canStillUse(ServerLevel level, AllSeerEntity owner, long gameTime) {
        return true;
    }

    @Override
    protected void tick(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.tick(level, owner, gameTime);

        List<Player> players = owner.getBrain().getMemory(MemoryModuleType.NEAREST_PLAYERS).get();
        if (!players.isEmpty()) {


            for (Player player : players) {
                int phase = owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).orElse(1);
                int beamCount = 4 * phase;
                double radius = 2.0;

                double waveOffset = (Math.PI / beamCount);

                if (a) {
                    for (int j = 0; j < beamCount; j++) {
                        double angle = (Math.PI * 2 / beamCount) * j + waveOffset;

                        double x = player.getX() + Math.cos(angle) * radius;
                        double z = player.getZ() + Math.sin(angle) * radius;

                        owner.getBrain().setMemory(ARROWS_SUMMONED.get(), beamCount);

                        LightArrowEntity beam = new LightArrowEntity(HolyHellEntities.LIGHT_ARROW.get(), level, player, angle, j);
                        beam.moveTo(x, player.getEyeY(), z, 0, 0);
                        level.addFreshEntity(beam);

                        if (j == beamCount - 1) {
                            a = false;
                        }

                    }
                }
            }
        }
    }

    @Override
    protected void stop(ServerLevel level, AllSeerEntity entity, long gameTime) {
        super.stop(level, entity, gameTime);

        Brain<AllSeerEntity> brain = entity.getBrain();

        if (brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 1 &&
                brain.getMemory(HolyHellMemoryModules.ENERGY.get()).isPresent()) {

            a = true;
//            brain.eraseMemory(HolyHellMemoryModules.ATTACK_INDEX.get());
            int i = brain.getMemory(HolyHellMemoryModules.ENERGY.get()).get() - 1;
            brain.setMemory(HolyHellMemoryModules.ENERGY.get(), i);
            brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 100);
        }
    }
}
