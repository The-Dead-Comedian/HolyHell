package com.dead_comedian.holyhell.server.registries;



import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class HolyHellTags {
    public static class Entities{

        public static final TagKey<EntityType<?>> MINIBOSS =
                createTag("miniboss");

        public static final TagKey<EntityType<?>> REVENANT_TRANSCENDS =
                createTag("revenant_transcends");

        public static final TagKey<EntityType<?>> BOSS =
                createTag("boss");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> GLASS =
                createTag("glass");
        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, name));
        }
    }

    public static class Blocks{

        public static final TagKey<Block> DOME_CLEARS_OUT =
                createTag("dome_clears_out");

        public static final TagKey<Block> MARBLE =
                createTag("marble");

        public static final TagKey<Block> STATUE =
                createTag("statue");

        public static final TagKey<Block> LIGHTING_BLOCKS =
                createTag("lighting_blocks");

        public static final TagKey<Block> REVENANT_PROTECTS =
                createTag("revenant_protects");



        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, name));
        }
    }

    public static class DamageTypes{


        public static final TagKey<DamageType> DIVINE_PROTECTION_IGNORE =
                createTag("divine_protection_ignore");


        private static TagKey<DamageType> createTag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, name));
        }
    }

}
