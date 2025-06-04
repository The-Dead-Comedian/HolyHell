package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EvangelistArmorItem extends ArmorItem {

    HolyhellArmorMaterials armorMaterial;

    public EvangelistArmorItem(HolyhellArmorMaterials material, Type type, Properties settings) {
        super(material, type, settings);
        armorMaterial = material;

    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);

        if ((hasCorrectHelmOn(HolyhellArmorMaterials.EVANGELIST, player) ||
                hasCorrectChestOn(HolyhellArmorMaterials.EVANGELIST, player) ||
                hasCorrectLegOn(HolyhellArmorMaterials.EVANGELIST, player) ||
                hasCorrectBootsOn(HolyhellArmorMaterials.EVANGELIST, player))
                && (!player.hasEffect(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN.get())
                && player.getHealth() >= player.getMaxHealth())) {

            player.addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION.get(), 100));


        } else {
            player.removeEffect(HolyHellEffects.DIVINE_PROTECTION.get());
        }
    }

    private boolean hasCorrectHelmOn(HolyhellArmorMaterials material, Player player) {

        if (!(player.getInventory().getArmor(3).getItem() instanceof ArmorItem)) {
            return false;
        }
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material;
    }

    private boolean hasCorrectChestOn(HolyhellArmorMaterials material, Player player) {

        if (!(player.getInventory().getArmor(2).getItem() instanceof ArmorItem)) {
            return false;
        }
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());

        return breastplate.getMaterial() == material;
    }

    private boolean hasCorrectLegOn(HolyhellArmorMaterials material, Player player) {
        if (!(player.getInventory().getArmor(1).getItem() instanceof ArmorItem)) {
            return false;
        }

        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());

        return leggings.getMaterial() == material;
    }

    private boolean hasCorrectBootsOn(HolyhellArmorMaterials material, Player player) {
        if (!(player.getInventory().getArmor(0).getItem() instanceof ArmorItem)) {
            return false;
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());


        return boots.getMaterial() == material;
    }
}