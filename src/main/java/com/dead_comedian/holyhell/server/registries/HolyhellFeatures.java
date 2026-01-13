package com.dead_comedian.holyhell.server.registries;


import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HolyhellFeatures {

    public static class ConfiguredFeatures {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
                DeferredRegister.create(Registries.CONFIGURED_FEATURE, Holyhell.MOD_ID);

        public static final ResourceKey<ConfiguredFeature<?, ?>> MARBLE_PATCH = createKey("marble_patch");

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, name));
        }

        public static void register(IEventBus eventBus) {
            CONFIGURED_FEATURE.register(eventBus);
        }

    }

    public static class PlacedFeatures {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
                DeferredRegister.create(Registries.PLACED_FEATURE, Holyhell.MOD_ID);

        public static final ResourceKey<PlacedFeature> MARBLE_PATCH = createKey("marble_patch");

        public static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, name));
        }

        public static void register(IEventBus eventBus) {
            PLACED_FEATURE.register(eventBus);
        }
    }
}