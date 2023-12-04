package net.deadcomedian.holyhell.datagen.loot;


import net.deadcomedian.holyhell.block.ModBlocks;


import net.deadcomedian.holyhell.entity.ModEntities;
import net.minecraft.data.loot.BlockLootSubProvider;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;

import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.DIVINING_TABLE.get());


        this.dropSelf(ModBlocks.LEVANTIA_LOG.get());
        this.dropSelf(ModBlocks.CARVED_LEVANTIA_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_LEVANTIA_LOG.get());
        this.dropSelf(ModBlocks.LEVANTIA_PLANK.get());
        this.dropSelf(ModBlocks.LEVANTIA_SAPLING.get());

        this.add(ModBlocks.LEVANTIA_LEAVES.get(), block ->

                createLeavesDrops(block, ModBlocks.LEVANTIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));



    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}