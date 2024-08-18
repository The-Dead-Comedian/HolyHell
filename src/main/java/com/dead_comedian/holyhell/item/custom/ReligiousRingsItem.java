package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ReligiousRingsItem extends Item {



    public ReligiousRingsItem( Item.Settings settings ) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(HolyHellEffects.JESISTANCE, 40 , 1));

        return super.use(world, user, hand);
    }
}
