package com.dead_comedian.holyhell.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CarvedPumpkinEyeItem extends BlockItem {
    public CarvedPumpkinEyeItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.literal("Eye").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
