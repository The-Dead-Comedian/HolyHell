package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

public class HolyHellModelLayers {

    public static final Material SHIELD_BASE_NO_PATTERN = new Material(Sheets.SHIELD_SHEET,  new ResourceLocation(Holyhell.MOD_ID , "entity/holy_shield"));



    public static final ModelLayerLocation LIGHT_BEAM =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam"), "main");

    public static final ModelLayerLocation LIGHT_BEAM1 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam1"), "main");
    public static final ModelLayerLocation LIGHT_BEAM2 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam2"), "main");
    public static final ModelLayerLocation LIGHT_BEAM3 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam3"), "main");
    public static final ModelLayerLocation LIGHT_BEAM4 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam4"), "main");
    public static final ModelLayerLocation LIGHT_BEAM5 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "light_beam5"), "main");

    public static final ModelLayerLocation ANGEL =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "angel"), "main");
    public static final ModelLayerLocation PALLADIN =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "palladin"), "main");
    public static final ModelLayerLocation HAILING_HERETIC =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "hailing_heretic"), "main");
    public static final ModelLayerLocation HOLY_SPIRIT =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "holy_spirit"), "main");
    public static final ModelLayerLocation KAMIKAZE_ANGEL =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "kamikaze_angel"), "main");
    public static final ModelLayerLocation BAB =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "bab"), "main");
    public static final ModelLayerLocation BAB1 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "bab1"), "main");
    public static final ModelLayerLocation BAB2 =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "bab2"), "main");

    public static final ModelLayerLocation FIREBALL =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "fireball"), "main");
    public static final ModelLayerLocation FALLING_SWORD =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "falling_sword"), "main");
    public static final ModelLayerLocation SWORD_CROSS =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "sword_cross"), "main");
    public static final ModelLayerLocation GLOBULAR_DOME =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "globular_dome"), "main");
    public static final ModelLayerLocation BLINDING_BOMB =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "blinding_bomb"), "main");



    public static final ModelLayerLocation RELIGIOUS_RINGS =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "religious_rings"), "main");
    public static final ModelLayerLocation RELIGIOUS_RINGSV =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "religious_ringsv"), "main");
    public static final ModelLayerLocation ATHEIST_AMAZEMENT =
            new ModelLayerLocation(new ResourceLocation(Holyhell.MOD_ID, "atheist_amazement"), "main");

}
