package com.dead_comedian.holyhell.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import com.dead_comedian.holyhell.block.ModBlocks;
import com.dead_comedian.holyhell.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //BlockStateModelGenerator.BlockTexturePool rubyPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK);
        //blockStateModelGenerator.registe;
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EVANGELIST_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EVANGELIST_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EVANGELIST_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.EVANGELIST_BOOTS));

    }
}
