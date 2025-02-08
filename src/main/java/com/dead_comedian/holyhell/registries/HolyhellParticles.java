package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class HolyhellParticles {
    public static final SimpleParticleType LIGHT_RING =
            registerParticle("light_ring", FabricParticleTypes.simple());


    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(Holyhell.MOD_ID, name), particleType);
    }



    public static void registerParticles() {
        Holyhell.LOGGER.info("Registering Particles for " + Holyhell.MOD_ID);
    }
}
