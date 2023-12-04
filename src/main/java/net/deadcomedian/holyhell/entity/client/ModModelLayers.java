package net.deadcomedian.holyhell.entity.client;

import net.deadcomedian.holyhell.HolyHell;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;



    public class ModModelLayers {
        public static final ModelLayerLocation ALL_SEER_LAYER = new ModelLayerLocation(
                new ResourceLocation(HolyHell.MOD_ID, "all_seer_layer"), "main");

        public static final ModelLayerLocation ANGEL_LAYER = new ModelLayerLocation(
                new ResourceLocation(HolyHell.MOD_ID, "angel_layer"), "main");


            public static final ModelLayerLocation EVANGELIST_ARMOR = create("evangelist_armor");



            private static ModelLayerLocation create(String name) {
                return new ModelLayerLocation(new ResourceLocation(HolyHell.MOD_ID, name), "main");
            }}

