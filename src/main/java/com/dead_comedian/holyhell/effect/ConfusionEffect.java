package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.entity.ai.ConfusionAggroGoal;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;


public class ConfusionEffect extends MobEffect {
    protected final RandomSource random = RandomSource.create();

    public ConfusionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {

        if(entity instanceof Mob){
            ((Mob) entity).goalSelector.addGoal(2, new ConfusionAggroGoal((Mob) entity));
        }
    }

}