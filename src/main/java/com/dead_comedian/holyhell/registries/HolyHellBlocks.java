package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.*;

import com.dead_comedian.holyhell.item.custom.StoneCrossBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;




public class HolyHellBlocks {

    public static final Block DIVINING_TABLE = registerBlock("divining_table",
            new DiviningTableBlock(FabricBlockSettings
                    .copyOf(Blocks.STONE)
                        .requiresTool()
                    .strength(3F)
                    .noOcclusion()) {
                        @Override
                        public BlockState rotate(BlockState state, Rotation rotation) {
                            return super.rotate(state, rotation);
                        }
            });

    public static final Block FALLING_CROSS = registerBlock("falling_cross",
            new FallingCrossBlock(  FabricBlockSettings
                    .copyOf(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3F)
                    .noOcclusion()) {
                @Override
                public BlockState rotate(BlockState state, Rotation rotation) {
                    return super.rotate(state, rotation);
                }
            });

    public static final Block CANDELABRA = registerBlock("candelabra",
            new CandelabraBlock(FabricBlockSettings
                    .copyOf(Blocks.LANTERN)
                    .noOcclusion()
                    .noCollission()
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
                    .sound(SoundType.LANTERN)
                    .lightLevel((state) -> {
                         if(state.getValue(CandelabraBlock.LIT)){
                            return (state.getValue(CandelabraBlock.CANDLE)+1) *3;
                         }
                         return 0;
            }) , ParticleTypes.FLAME) {

            });
    public static final Block CANDLEHOLDER = registerBlock("candleholder",
            new CandleholderBlock(FabricBlockSettings
                    .copyOf(Blocks.LANTERN)
                    .noOcclusion()
                    .noCollission()
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
                    .sound(SoundType.LANTERN)
                    .lightLevel((state) -> {
                        if(state.getValue(CandleholderBlock.LIT)){
                           return 14;
                        }
                         return 0;
            }) , ParticleTypes.FLAME) {

            });

    public static final Block STONE_CROSS = registerStoneCrossDoor("stone_cross",
            new StoneCrossBlock(FabricBlockSettings
                    .copyOf(Blocks.STONE)
                    .noOcclusion()
                    .noCollission()
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
            ));

    public static final Block GLOBE = registerBlock("globe",
            new GlobeBlock(FabricBlockSettings
                    .copyOf(Blocks.ANVIL)
                    .strength(2.3F)
                    .noOcclusion()) {
                        @Override
                        public BlockState rotate(BlockState state, Rotation rotation) {
                            return super.rotate(state, rotation);
                        }
            });





    private static Block registerStoneCrossDoor(String name, Block block) {
        registerStoneCrossDoorItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Holyhell.MOD_ID, name), block);
    }

    private static Item registerStoneCrossDoorItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Holyhell.MOD_ID, name),
                new StoneCrossBlockItem(block, new FabricItemSettings()));
    }



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Holyhell.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Holyhell.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Holyhell.LOGGER.info("Registering ModBlocks for " + Holyhell.MOD_ID);
    }
}
