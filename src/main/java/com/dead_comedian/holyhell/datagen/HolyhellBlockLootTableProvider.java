package com.dead_comedian.holyhell.datagen;

import com.dead_comedian.holyhell.server.registries.HolyHellBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class HolyhellBlockLootTableProvider extends BlockLootSubProvider {
    protected HolyhellBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(HolyHellBlocks.SKULL_PILE.get());
        dropSelf(HolyHellBlocks.BONE_PILE.get());
        dropSelf(HolyHellBlocks.BONE_CHANDELIER.get());

        dropSelf(HolyHellBlocks.CHANDELIER.get());

        dropSelf(HolyHellBlocks.FALLING_CROSS.get());

        dropSelf(HolyHellBlocks.MARBLE.get());
        dropSelf(HolyHellBlocks.MARBLE_SLAB.get());
        dropSelf(HolyHellBlocks.MARBLE_STAIRS.get());
        dropSelf(HolyHellBlocks.MARBLE_WALL.get());

        dropSelf(HolyHellBlocks.MARBLE_BRICKS.get());
        dropSelf(HolyHellBlocks.MARBLE_BRICK_SLAB.get());
        dropSelf(HolyHellBlocks.MARBLE_BRICK_STAIRS.get());
        dropSelf(HolyHellBlocks.MARBLE_BRICK_WALL.get());

        dropSelf(HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());
        dropSelf(HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get());
        dropSelf(HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get());
        dropSelf(HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get());

        dropSelf(HolyHellBlocks.MARBLE_COLUMN.get());


        dropSelf(HolyHellBlocks.CARVED_PUMPKIN_CROSS.get());
        dropSelf(HolyHellBlocks.JACK_O_LANTERN_CROSS.get());

        dropSelf(HolyHellBlocks.CARVED_PUMPKIN_EYE.get());
        dropSelf(HolyHellBlocks.JACK_O_LANTERN_EYE.get());
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {

        return HolyHellBlocks.BLOCKS.getEntries().stream().map(Holder::value)
                .filter(block -> block != HolyHellBlocks.ATLAS_STATUE.get())
                .filter(block -> block != HolyHellBlocks.BAPHOMET_STATUE.get())
                .filter(block -> block != HolyHellBlocks.DOOMSLAYER_STATUE.get())
                .filter(block -> block != HolyHellBlocks.ICARUS_STATUE.get())
                .filter(block -> block != HolyHellBlocks.KRATOS_STATUE.get())
                .filter(block -> block != HolyHellBlocks.V1_STATUE.get())

                .filter(block -> block != HolyHellBlocks.COFFIN.get())
                .filter(block -> block != HolyHellBlocks.STONE_CROSS.get())

                .filter(block -> block != HolyHellBlocks.DIVINING_TABLE.get())


                .filter(block -> block != HolyHellBlocks.CANDLEHOLDER.get())
                .filter(block -> block != HolyHellBlocks.TALL_CANDLEHOLDER.get())
                .filter(block -> block != HolyHellBlocks.CANDELABRA.get())
                .filter(block -> block != HolyHellBlocks.TALL_CANDELABRA.get())


                ::iterator;
    }
}