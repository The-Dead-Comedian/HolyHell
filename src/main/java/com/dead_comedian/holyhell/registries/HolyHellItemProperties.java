package com.dead_comedian.holyhell.registries;


import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;


public class HolyHellItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(HolyHellItems.HOLY_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (stack, level, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1.0F : 0.0F;
        });

        ItemProperties.register(HolyHellItems.BAB.get(), ResourceLocation.withDefaultNamespace("level"), (stack, level, livingEntity, i) -> {
            if (stack.get(DataComponents.CUSTOM_DATA) != null) {

                return stack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("level");
            } return 0;
        });
    }

}