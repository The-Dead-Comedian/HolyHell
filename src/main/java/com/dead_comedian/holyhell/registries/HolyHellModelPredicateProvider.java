package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
public class HolyHellModelPredicateProvider {
    public static void registerModModels() {
        ItemProperties.register(HolyHellItems.HOLY_SHIELD, new ResourceLocation("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0.0f);
    }


}