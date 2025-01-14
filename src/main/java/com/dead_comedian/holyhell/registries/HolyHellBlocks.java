package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.*;
import com.dead_comedian.holyhell.item.custom.StoneCrossBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;




public class HolyHellBlocks {

    public static final Block DIVINING_TABLE = registerBlock("divining_table",
            new DiviningTableBlock(FabricBlockSettings
                    .copyOf(Blocks.STONE)
                    .requiresTool()
                    .strength(3F)
                    .nonOpaque()) {
                        @Override
                        public BlockState rotate(BlockState state, BlockRotation rotation) {
                            return super.rotate(state, rotation);
                        }
            });

    public static final Block CANDELABRA = registerBlock("candelabra",
            new CandelabraBlock(FabricBlockSettings
                    .copyOf(Blocks.LANTERN)
                    .nonOpaque()
                    .noCollision()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance((state) -> {
                         if(state.get(CandelabraBlock.LIT)){
                            return (state.get(CandelabraBlock.CANDLE)+1) *3;
                         }
                         return 0;
            }) , ParticleTypes.FLAME) {

            });
    public static final Block CANDLEHOLDER = registerBlock("candleholder",
            new CandleholderBlock(FabricBlockSettings
                    .copyOf(Blocks.LANTERN)
                    .nonOpaque()
                    .noCollision()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance((state) -> {
                        if(state.get(CandleholderBlock.LIT)){
                           return 14;
                        }
                         return 0;
            }) , ParticleTypes.FLAME) {

            });

    public static final Block STONE_CROSS = registerStoneCrossDoor("stone_cross",
            new StoneCrossBlock(FabricBlockSettings
                    .copyOf(Blocks.STONE)
                    .nonOpaque()
                    .noCollision()
                    .requiresTool()
                    .strength(3.5F)
            ));

    public static final Block GLOBE = registerBlock("globe",
            new GlobeBlock(FabricBlockSettings
                    .copyOf(Blocks.ANVIL)
                    .strength(2.3F)
                    .nonOpaque()) {
                        @Override
                        public BlockState rotate(BlockState state, BlockRotation rotation) {
                            return super.rotate(state, rotation);
                        }
            });





    private static Block registerStoneCrossDoor(String name, Block block) {
        registerStoneCrossDoorItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Holyhell.MOD_ID, name), block);
    }

    private static Item registerStoneCrossDoorItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Holyhell.MOD_ID, name),
                new StoneCrossBlockItem(block, new FabricItemSettings()));
    }



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
