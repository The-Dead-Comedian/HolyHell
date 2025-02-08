package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class HolyHellPaintings {
    public static final PaintingVariant SPIRIT = registerPainting("spirit", new PaintingVariant(16, 16));


    private static PaintingVariant registerPainting(String name, PaintingVariant paintingMotive) {
        return Registry.register(BuiltInRegistries.PAINTING_VARIANT, new ResourceLocation(Holyhell.MOD_ID, name), paintingMotive);
    }

    public static void registerPaintings() {
        Holyhell.LOGGER.info("Registering Paintings for " + Holyhell.MOD_ID);
    }
}