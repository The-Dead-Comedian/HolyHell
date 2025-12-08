package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HolyhellParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Holyhell.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIGHT_RING  =
            PARTICLE_TYPES.register("light_ring", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HOSTILE_LOCATOR  =
            PARTICLE_TYPES.register("hostile_locator", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BOSS_LOCATOR  =
            PARTICLE_TYPES.register("boss_locator", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PEACEFUL_LOCATOR  =
            PARTICLE_TYPES.register("peaceful_locator", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PLAYER_LOCATOR  =
            PARTICLE_TYPES.register("player_locator", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> OBFUSCATION  =
            PARTICLE_TYPES.register("obfuscation", () -> new SimpleParticleType(true));



    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> KAMIKAZE_EXPLOSION  =
            PARTICLE_TYPES.register("kamikaze_explosion", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SWEEP_ATTACK  =
            PARTICLE_TYPES.register("sweep_attack", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SOUND_RING  =
            PARTICLE_TYPES.register("sound_ring", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STUN  =
            PARTICLE_TYPES.register("stun", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STUN2  =
            PARTICLE_TYPES.register("stun2", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EYE0  =
            PARTICLE_TYPES.register("eye_0", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EYE1  =
            PARTICLE_TYPES.register("eye_1", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EYE2  =
            PARTICLE_TYPES.register("eye_2", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EYE3 =
            PARTICLE_TYPES.register("eye_3", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}