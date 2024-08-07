package com.dead_comedian.holyhell.client;


import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


        itemModelGenerator.registerArmor(((ArmorItem) HolyHellItems.EVANGELIST_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) HolyHellItems.EVANGELIST_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) HolyHellItems.EVANGELIST_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) HolyHellItems.EVANGELIST_BOOTS));

    }
}
