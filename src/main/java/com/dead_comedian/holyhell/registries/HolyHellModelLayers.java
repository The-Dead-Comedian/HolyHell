package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class HolyHellModelLayers {

    public static final EntityModelLayer ANGEL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "angel"), "main");



    public static final EntityModelLayer LASTPRAYER =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "last_prayer"), "main");
    public static final EntityModelLayer CHRISTIANCROSS =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "christian_cross"), "main");
    public static final EntityModelLayer RELIGIOUS_RINGS =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "religious_rings"), "main");
    public static final EntityModelLayer AREA_SPELL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "area_spell"), "main");

}
