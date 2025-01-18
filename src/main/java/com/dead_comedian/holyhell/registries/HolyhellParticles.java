package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HolyhellParticles {
    public static final DefaultParticleType LIGHT_RING =
            registerParticle("light_ring", FabricParticleTypes.simple());


    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Holyhell.MOD_ID, name), particleType);
    }



    public static void registerParticles() {
        Holyhell.LOGGER.info("Registering Particles for " + Holyhell.MOD_ID);
    }
}
