
    package com.dead_comedian.holyhell.item;

import com.dead_comedian.holyhell.registries.HolyHellItems;
import java.util.function.Supplier;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

    public enum HolyhellToolMaterial implements Tier {
        GRAIL(5, 1500, 11.0f, 5.0f, 25, () -> Ingredient.of(HolyHellItems.BAPTIZED_PLATE));

        private final int miningLevel;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;
        private final LazyLoadedValue<Ingredient> repairIngredient;

        private HolyhellToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage,
                                int enchantability, Supplier<Ingredient> repairIngredient) {
            this.miningLevel = miningLevel;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairIngredient = new LazyLoadedValue<Ingredient>(repairIngredient);
        }

        @Override
        public int getUses() {
            return this.itemDurability;
        }

        @Override
        public float getSpeed() {
            return this.miningSpeed;
        }

        @Override
        public float getAttackDamageBonus() {
            return this.attackDamage;
        }

        @Override
        public int getLevel() {
            return this.miningLevel;
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

