package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import javax.swing.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ReligiousRingsItem extends Item {


    public ReligiousRingsItem(Item.Properties settings) {
        super(settings);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {
            if (user.hasEffect(HolyHellEffects.JESISTANCE)
                    && user.getEffect(HolyHellEffects.JESISTANCE).getAmplifier() <= 5) {


                user.addEffect(new MobEffectInstance(HolyHellEffects.JESISTANCE, 2000,
                        user.getEffect(HolyHellEffects.JESISTANCE).getAmplifier() + 1));


            } else if (!user.hasEffect(HolyHellEffects.JESISTANCE)) {
                user.addEffect(new MobEffectInstance(HolyHellEffects.JESISTANCE, 2000, 1));
            }
        }
        if (!user.isCreative()) {
            user.getItemInHand(hand).shrink(1);
        }

        return InteractionResultHolder.consume(user.getItemInHand(hand));
    }
}
