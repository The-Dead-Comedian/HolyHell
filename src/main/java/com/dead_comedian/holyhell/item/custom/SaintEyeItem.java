package com.dead_comedian.holyhell.item.custom;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;


import java.util.List;


public class SaintEyeItem extends Item {

    public SaintEyeItem(Properties settings) {
        super(settings);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);

        Level level = entity.level();

        AABB userHitbox = new AABB(entity.blockPosition()).inflate(50);

        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, userHitbox);
        for (LivingEntity i : list) {

            i.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 1));
            entity.removeEffect(MobEffects.GLOWING);
        }
        list.clear();

    }


}
