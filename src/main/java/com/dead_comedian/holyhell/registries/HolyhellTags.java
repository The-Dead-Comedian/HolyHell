package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;

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

    public static class DamageTypes{

        public static final TagKey<DamageType> MAGIC_DAMAGE =
                createTag("magic_damage");

        private static TagKey<DamageType> createTag(String name) {
            return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Holyhell.MOD_ID, name));
        }
    }

}
