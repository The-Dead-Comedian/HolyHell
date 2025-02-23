package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyhellParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HolyHell.MOD_ID);

    public static final RegistryObject<SimpleParticleType> LIGHT_RING  =
            PARTICLE_TYPES.register("light_ring", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> SOUND_RING  =
            PARTICLE_TYPES.register("sound_ring", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> STUN  =
            PARTICLE_TYPES.register("stun", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> STUN2  =
            PARTICLE_TYPES.register("stun2", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}