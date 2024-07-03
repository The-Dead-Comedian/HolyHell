package com.dead_comedian.holyhell.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SaintEyeItem extends Item {

    public SaintEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {


        Box userHitbox = new Box(user.getBlockPos()).expand(50);

        List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, userHitbox);
        for(LivingEntity i : list){

            i.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200 ,1));
            user.removeStatusEffect(StatusEffects.GLOWING);
        }

        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    
}
