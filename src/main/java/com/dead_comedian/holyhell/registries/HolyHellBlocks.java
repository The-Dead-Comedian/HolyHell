package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.*;

import com.dead_comedian.holyhell.item.CandleHolderItem;
import com.dead_comedian.holyhell.item.StoneCrossItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;


public class HolyHellBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Holyhell.MOD_ID);


    public static final Supplier<Block> BISMUTH_BLOCK = register("bismuth_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final Supplier<Block> DIVINING_TABLE = register("divining_table",
            () -> new DiviningTableBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));

    public static final Supplier<Block> STONE_CROSS = registerCrossBlock("stone_cross",
            () -> new StoneCrossBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));


    public static final Supplier<Block> CANDELABRA = register("candelabra",
            () -> new CandelabraBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .noCollission()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(HolyHellSound.CANDELABRA_SOUNDS)
                    .lightLevel(CandelabraBlock.LIGHT_EMISSION), ParticleTypes.FLAME));

    public static final Supplier<Block> CANDLE_HOLDER = registerCandleHolder("candleholder",
            () -> new CandleholderBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .noCollission()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(HolyHellSound.CANDELABRA_SOUNDS)
                    .lightLevel(CandleholderBlock.LIGHT_EMISSION), ParticleTypes.FLAME));

    public static final Supplier<Block> FALLING_CROSS = register("falling_cross",
            () -> new FallingCrossBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));


    public static final Supplier<Block> COBBLED_MARBLE = register("cobbled_marble",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)) {
            });

    public static final Supplier<Block> MARBLE = register("marble",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)) {
            });

    public static final Supplier<Block> MARBLE_BRICKS = register("marble_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)) {
            });

    public static final Supplier<Block> CRACKED_MARBLE_BRICKS = register("cracked_marble_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)) {
            });

    public static final Supplier<Block> MARBLE_COLLUMN = register("marble_collumn",
            () -> new MarbleCollumnBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)) {
            });


    private static <T extends Block> DeferredHolder<Item,Item> registerCrossBlockItem(String name, Supplier<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new StoneCrossItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredHolder<Item,Item> registerCandleHolderItem(String name, Supplier<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new CandleHolderItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> Supplier<T> registerCrossBlock(String name, Supplier<T> block) {
        Supplier<T> toReturn = BLOCKS.register(name, block);
        registerCrossBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> Supplier<T> registerCandleHolder(String name, Supplier<T> block) {
        Supplier<T> toReturn = BLOCKS.register(name, block);
        registerCandleHolderItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
        Supplier<T> toReturn = BLOCKS.register(name, block);
        registerItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerItem(String name, Supplier<T> block) {
        HolyHellItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
