package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import javax.swing.*;

public class ReligiousRingsItem extends Item {


    public ReligiousRingsItem(Item.Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (user.hasStatusEffect(HolyHellEffects.JESISTANCE)
                    && user.getStatusEffect(HolyHellEffects.JESISTANCE).getAmplifier() <= 5) {


                user.addStatusEffect(new StatusEffectInstance(HolyHellEffects.JESISTANCE, 2000,
                        user.getStatusEffect(HolyHellEffects.JESISTANCE).getAmplifier() + 1));


            } else if (!user.hasStatusEffect(HolyHellEffects.JESISTANCE)) {
                user.addStatusEffect(new StatusEffectInstance(HolyHellEffects.JESISTANCE, 2000, 1));
            }
        }
        if (!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
        }

        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
