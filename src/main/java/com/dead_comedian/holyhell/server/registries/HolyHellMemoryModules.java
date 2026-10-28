package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Supplier;

public class HolyHellMemoryModules {
    public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(Registries.MEMORY_MODULE_TYPE, HolyHell.MOD_ID);

    public static final Supplier<MemoryModuleType<BlockPos>> WEAPON_POS = register("weapon_pos", BlockPos.CODEC);
    public static final Supplier<MemoryModuleType<Boolean>> IS_AWAKE = register("is_awake", Codec.BOOL);
    public static final Supplier<MemoryModuleType<Boolean>> SHOULD_DAMAGE = register("should_damage", Codec.BOOL);
    public static final Supplier<MemoryModuleType<Integer>> ENERGY = register("energy", Codec.INT);
    public static final Supplier<MemoryModuleType<Integer>> ATTACK_INDEX = register("attack_index", Codec.INT);
    public static final Supplier<MemoryModuleType<Integer>> PHASE = register("phase", Codec.INT);



    private static <T> Supplier<MemoryModuleType<T>> register(String name, Codec<T> codec) {
        return register(name, Optional.of(codec));
    }

    private static <T> Supplier<MemoryModuleType<T>> register(String name, Optional<Codec<T>> codec) {
        return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType<>(codec));
    }

    public static void register(IEventBus modEventBus) {
        MEMORY_MODULE_TYPES.register(modEventBus);
    }
}
