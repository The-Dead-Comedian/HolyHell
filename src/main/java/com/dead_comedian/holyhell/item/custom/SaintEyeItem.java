package com.dead_comedian.holyhell.item.custom;

import java.util.List;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class SaintEyeItem extends Item {

    public SaintEyeItem(Properties settings) {
        super(settings);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {


        AABB userHitbox = new AABB(user.blockPosition()).inflate(50);

        List<LivingEntity> list = world.getEntitiesOfClass(LivingEntity.class, userHitbox);
        for(LivingEntity i : list){

            i.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200 ,1));
            user.removeEffect(MobEffects.GLOWING);
        }

        super.releaseUsing(stack, world, user, remainingUseTicks);
    }

    
}
