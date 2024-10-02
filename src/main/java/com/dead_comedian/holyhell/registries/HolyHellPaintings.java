package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HolyHellPaintings {
    public static final PaintingVariant SPIRIT = registerPainting("spirit", new PaintingVariant(16, 16));


    private static PaintingVariant registerPainting(String name, PaintingVariant paintingMotive) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(Holyhell.MOD_ID, name), paintingMotive);
    }

    public static void registerPaintings() {
        Holyhell.LOGGER.info("Registering Paintings for " + Holyhell.MOD_ID);
    }
}