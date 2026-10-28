package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.entity.ai.sensor.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HolyHellSensorTypes {

    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES =
            DeferredRegister.create(Registries.SENSOR_TYPE, HolyHell.MOD_ID);

    public static final Supplier<SensorType<SeerPhaseSensor>> PHASE =
            SENSOR_TYPES.register("phase",
                    () -> new SensorType<>(SeerPhaseSensor::new));
    public static final Supplier<SensorType<RevenantAttackableEntitySensor>> REVENANT_ATTACKABLE_ENTITY =
            SENSOR_TYPES.register("revenant_attackable_entity",
                    () -> new SensorType<>(RevenantAttackableEntitySensor::new));

    public static final Supplier<SensorType<AttackablePlayerSensor>> ATTACKABLE_PLAYER =
            SENSOR_TYPES.register("attackable_player",
                    () -> new SensorType<>(AttackablePlayerSensor::new));

    public static final Supplier<SensorType<NearbyWeaponSensor>> NEARBY_WEAPON =
            SENSOR_TYPES.register("nearby_weapon",
                    () -> new SensorType<>(NearbyWeaponSensor::new));

    public static final Supplier<SensorType<IsAwakeSensor>> IS_AWAKE_SENSOR =
            SENSOR_TYPES.register("is_awake_sensor",
                    () -> new SensorType<>(IsAwakeSensor::new));


    public static void register(IEventBus eventBus) {
        SENSOR_TYPES.register(eventBus);
    }


}





