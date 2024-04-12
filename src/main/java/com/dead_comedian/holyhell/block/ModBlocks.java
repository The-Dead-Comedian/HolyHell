package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DIVINING_TABLE = registerBlock("divining_table",
            new Block(FabricBlockSettings.copyOf(Blocks.ANVIL)));
    private static Block registerBlock(String name, Block block ){
        registerBlockitem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Holyhell.MOD_ID, name), block);
    }
    private static void registerBlockitem(String name, Block block){
        Registry.register(Registries.ITEM, new Identifier(Holyhell.MOD_ID), new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks(){
        Holyhell.LOGGER.info("registering modBlocks for " + Holyhell.MOD_ID);
    }
}
