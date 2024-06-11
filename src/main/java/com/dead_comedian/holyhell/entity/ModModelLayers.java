package com.dead_comedian.holyhell.entity;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer ANGEL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "angel"), "main");
    public static final EntityModelLayer LASTPRAYER =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "last_prayer"), "main");
}
