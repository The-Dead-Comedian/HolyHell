package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class HolyHellModelLayers {

    public static final EntityModelLayer LIGHT_BEAM =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "light_beam"), "main");

    public static final EntityModelLayer ANGEL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "angel"), "main");
    public static final EntityModelLayer PALLADIN =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "palladin"), "main");
    public static final EntityModelLayer HAILING_HERETIC =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "hailing_heretic"), "main");
    public static final EntityModelLayer KAMIKAZE_ANGEL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "kamikaze_angel"), "main");

    public static final EntityModelLayer FIREBALL =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "fireball"), "main");
    public static final EntityModelLayer FALLING_SWORD =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "falling_sword"), "main");

    public static final EntityModelLayer GLOBULAR_DOME =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "globular_dome"), "main");
    public static final EntityModelLayer RELIGIOUS_RINGS =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "religious_rings"), "main");
    public static final EntityModelLayer RELIGIOUS_RINGSV =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "religious_ringsv"), "main");
    public static final EntityModelLayer ATHEIST_AMAZEMENT =
            new EntityModelLayer(new Identifier(Holyhell.MOD_ID, "atheist_amazement"), "main");

}
