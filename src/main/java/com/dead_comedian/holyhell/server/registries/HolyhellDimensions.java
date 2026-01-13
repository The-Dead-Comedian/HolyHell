package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HolyhellDimensions {
    public static final DeferredRegister<Level> LEVEL =
            DeferredRegister.create(Registries.DIMENSION, Holyhell.MOD_ID);


    public static final ResourceKey<Level> ANGEL = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID,"angel"));


    public static void register(IEventBus eventBus) {
        LEVEL.register(eventBus);
    }

}
