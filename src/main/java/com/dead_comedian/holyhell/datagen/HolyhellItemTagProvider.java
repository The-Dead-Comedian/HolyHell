package com.dead_comedian.holyhell.datagen;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.registries.HolyHellTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class HolyhellItemTagProvider extends ItemTagsProvider {
    public HolyhellItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, HolyHell.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(HolyHellTags.Items.GLASS)
                .add(Blocks.GLASS.asItem())
                .add(Blocks.BLACK_STAINED_GLASS.asItem())
                .add(Blocks.GRAY_STAINED_GLASS.asItem())
                .add(Blocks.LIGHT_GRAY_STAINED_GLASS.asItem())
                .add(Blocks.WHITE_STAINED_GLASS.asItem())
                .add(Blocks.RED_STAINED_GLASS.asItem())
                .add(Blocks.ORANGE_STAINED_GLASS.asItem())
                .add(Blocks.YELLOW_STAINED_GLASS.asItem())
                .add(Blocks.LIME_STAINED_GLASS.asItem())
                .add(Blocks.GREEN_STAINED_GLASS.asItem())
                .add(Blocks.CYAN_STAINED_GLASS.asItem())
                .add(Blocks.LIGHT_BLUE_STAINED_GLASS.asItem())
                .add(Blocks.BLUE_STAINED_GLASS.asItem())
                .add(Blocks.PURPLE_STAINED_GLASS.asItem())
                .add(Blocks.MAGENTA_STAINED_GLASS.asItem())
                .add(Blocks.PINK_STAINED_GLASS.asItem())
                .add(Blocks.BROWN_STAINED_GLASS.asItem());

    }
}
