package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class HolyhellTags {
    public static class Entities{

        public static final TagKey<EntityType<?>> MAGIC_DEALING_MOBS =
                createTag("magic_dealing_mobs");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(HolyHell.MOD_ID, name));
        }
    }

    public static class Blocks{

        public static final TagKey<Block> DOME_CLEARS_OUT =
                createTag("dome_clears_out");
        public static final TagKey<Block> REVENANT_PROTECTS =
                createTag("revenant_protects");
        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(HolyHell.MOD_ID, name));
        }
    }

    public static class DamageTypes{

        public static final TagKey<DamageType> MAGIC_DAMAGE =
                createTag("magic_damage");

        public static final TagKey<DamageType> DIVINE_PROTECTION_IGNORE =
                createTag("divine_protection_ignore");


        private static TagKey<DamageType> createTag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HolyHell.MOD_ID, name));
        }
    }

}
