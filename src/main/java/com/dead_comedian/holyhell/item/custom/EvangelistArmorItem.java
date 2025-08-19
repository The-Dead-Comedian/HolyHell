package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EvangelistArmorItem extends ArmorItem {

    HolyhellArmorMaterials armorMaterial;

    public EvangelistArmorItem(HolyhellArmorMaterials material, Type type, Properties settings) {
        super((Holder<ArmorMaterial>) material, type, settings);
        armorMaterial = material;

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (entity instanceof Player) {
            if ((hasCorrectHelmOn(HolyhellArmorMaterials.EVANGELIST, (Player) entity) ||
                    hasCorrectChestOn(HolyhellArmorMaterials.EVANGELIST, (Player) entity) ||
                    hasCorrectLegOn(HolyhellArmorMaterials.EVANGELIST, (Player) entity) ||
                    hasCorrectBootsOn(HolyhellArmorMaterials.EVANGELIST, (Player) entity))
                    && (! ((Player) entity).hasEffect(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN)
                    &&  ((Player) entity).getHealth() >=  ((Player) entity).getMaxHealth())) {

                ((Player) entity).addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION, 100));


            } else {
                ((Player) entity).removeEffect(HolyHellEffects.DIVINE_PROTECTION);
            }
        }
    }

    private boolean hasCorrectHelmOn(Holder<ArmorMaterial> material, Player player) {

        if (!(player.getInventory().getArmor(3).getItem() instanceof ArmorItem)) {
            return false;
        }
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material;
    }

    private boolean hasCorrectChestOn(Holder<ArmorMaterial> material, Player player) {

        if (!(player.getInventory().getArmor(2).getItem() instanceof ArmorItem)) {
            return false;
        }
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());

        return breastplate.getMaterial() == material;
    }

    private boolean hasCorrectLegOn(Holder<ArmorMaterial> material, Player player) {
        if (!(player.getInventory().getArmor(1).getItem() instanceof ArmorItem)) {
            return false;
        }

        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());

        return leggings.getMaterial() == material;
    }

    private boolean hasCorrectBootsOn(Holder<ArmorMaterial> material, Player player) {
        if (!(player.getInventory().getArmor(0).getItem() instanceof ArmorItem)) {
            return false;
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());


        return boots.getMaterial() == material;
    }
}