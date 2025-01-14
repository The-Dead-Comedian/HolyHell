package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class HolyhellTags {
    public static class Entities{

        public static final TagKey<EntityType<?>> MAGIC_DEALING_MOBS =
                createTag("magic_dealing_mobs");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Holyhell.MOD_ID, name));
        }
    }

    public static class Blocks{


        public static final TagKey<Block> POWERS_TABLE =
                createTag("powers_table");

        public static final TagKey<Block> DOME_CLEARS_OUT =
                createTag("dome_clears_out");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Holyhell.MOD_ID, name));
        }
    }

    public static class DamageTypes{

        public static final TagKey<DamageType> MAGIC_DAMAGE =
                createTag("magic_damage");

        private static TagKey<DamageType> createTag(String name) {
            return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Holyhell.MOD_ID, name));
        }
    }

}
