package com.dead_comedian.holyhell.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;

public class SaintEyeItem extends Item {

    public SaintEyeItem(Settings settings) {
        super(settings);
    }
    PlayerEntity playerEntity;
    LivingEntity livingEntity;
    //BlockPos blockPos= playerEntity.getBlockPos();
   // BlockPos blockPosLiving = livingEntity.getBlockPos();

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {


        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }
}
