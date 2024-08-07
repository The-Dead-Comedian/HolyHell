package com.dead_comedian.holyhell.datagen;


import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(HolyHellItems.EVANGELIST_HELMET, HolyHellItems.EVANGELIST_CHESTPLATE, HolyHellItems.EVANGELIST_LEGGINGS, HolyHellItems.EVANGELIST_BOOTS);
    }
}
