package com.dead_comedian.holyhell.server.entity.ai;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.AngelEntity;
import com.dead_comedian.holyhell.server.entity.ai.task.RangedAttack;
import com.dead_comedian.holyhell.server.entity.ai.task.seer.DetermineAttackPattern;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.dead_comedian.holyhell.server.registries.HolyHellSensorTypes;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.RandomLookAround;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;

import java.util.List;
import java.util.Set;

public class AllSeerAi {


    public static final List<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
            HolyHellMemoryModules.PHASE.get(),
            HolyHellMemoryModules.ATTACK_INDEX.get(),
            HolyHellMemoryModules.ENERGY.get(),
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.NEAREST_PLAYERS


    );

    public static final ImmutableList<SensorType<? extends Sensor<? super AllSeerEntity>>> SENSORS = ImmutableList.of(
            HolyHellSensorTypes.PHASE.get(),
            SensorType.NEAREST_PLAYERS,
            SensorType.HURT_BY
    );

    public static Brain<?> makeBrain(Brain<AllSeerEntity> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);

        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    public static void updateActivity(AllSeerEntity revenant) {
        revenant.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                Activity.IDLE
        ));
    }

    private static void initCoreActivity(Brain<AllSeerEntity> brain) {
        brain.addActivity(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new DetermineAttackPattern()
                )
        );
    }


    private static void initIdleActivity(Brain<AllSeerEntity> brain) {
        brain.addActivity(
                Activity.IDLE,
                0,
                ImmutableList.of(
                )
        );

    }


}
