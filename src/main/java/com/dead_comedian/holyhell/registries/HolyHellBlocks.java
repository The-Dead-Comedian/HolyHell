package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.custom.DiviningTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;




public class HolyHellBlocks {

    public static final Block DIVINING_TABLE = registerBlock("divining_table",
            new DiviningTableBlock(FabricBlockSettings.copyOf(Blocks.ANVIL).nonOpaque()) {
                @Override
                public BlockState rotate(BlockState state, BlockRotation rotation) {
                    return super.rotate(state, rotation);
                }
            });



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Holyhell.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Holyhell.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Holyhell.LOGGER.info("Registering ModBlocks for " + Holyhell.MOD_ID);
    }
}
