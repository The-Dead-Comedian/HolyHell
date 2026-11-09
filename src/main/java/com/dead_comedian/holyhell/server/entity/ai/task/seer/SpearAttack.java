package com.dead_comedian.holyhell.server.entity.ai.task.seer;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class SpearAttack extends Behavior<AllSeerEntity> {
    public SpearAttack() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ATTACK_INDEX.get(), MemoryStatus.VALUE_PRESENT,
                HolyHellMemoryModules.ENERGY.get(), MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, AllSeerEntity owner) {

        return owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                owner.getBrain().getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 2;
    }

    @Override
    protected boolean canStillUse(ServerLevel level, AllSeerEntity entity, long gameTime) {
        return a;
    }

    boolean a = true;

    @Override
    protected void tick(ServerLevel level, AllSeerEntity owner, long gameTime) {
        super.tick(level, owner, gameTime);


        for (int i = 0; i < 39; i++) {


            for (int j = 0; j < 39; j++) {
                if ((i % 4 == 2 && j % 4 == 2) || (i % 4 == 3 && j % 4 == 3)) {
                    BlockPos pos = owner.blockPosition().south(i - 20).east(j - 20);
//                Player player = owner.level().getNearestPlayer(owner.getX(), owner.getY(), owner.getZ(), 100, true);
                    System.out.println(pos);

                    JudgementSpearEntity leftProjectile =
                            new JudgementSpearEntity(HolyHellEntities.JUDGEMENT_SPEAR.get(), level, owner);
                    leftProjectile.moveTo(
                            pos.getX(),
                            pos.below(11).getY(),
                            pos.getZ(),
                            0,
                            0
                    );

                    if (leftProjectile.getTargetId() == null && owner.level().getNearestPlayer(leftProjectile.getX(), leftProjectile.getY(), leftProjectile.getZ(), 100, false) != null) {
                        leftProjectile.setTargetId(owner.level().getNearestPlayer(leftProjectile.getX(), leftProjectile.getY(), leftProjectile.getZ(), 100, false).getUUID());
                    }

                    level.addFreshEntity(leftProjectile);
                }
            }
            if (i == 38) {
                a = false;
                Brain<AllSeerEntity> brain = owner.getBrain();
                brain.eraseMemory(HolyHellMemoryModules.ATTACK_INDEX.get());
            }
        }

        if (!a) {
            stop(level, owner, gameTime);
        }

    }

    @Override
    protected void stop(ServerLevel level, AllSeerEntity entity, long gameTime) {
        super.stop(level, entity, gameTime);

        Brain<AllSeerEntity> brain = entity.getBrain();

        if (brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).isPresent() &&
                brain.getMemory(HolyHellMemoryModules.ATTACK_INDEX.get()).get() == 2 &&
                brain.getMemory(HolyHellMemoryModules.ENERGY.get()).isPresent()) {
//            brain.eraseMemory(HolyHellMemoryModules.ATTACK_INDEX.get());
            int i = brain.getMemory(HolyHellMemoryModules.ENERGY.get()).get() - 1;
            brain.setMemory(HolyHellMemoryModules.ENERGY.get(), i);
            brain.setMemory(HolyHellMemoryModules.ATTACK_INDEX.get(), 100);
        }
    }
}
