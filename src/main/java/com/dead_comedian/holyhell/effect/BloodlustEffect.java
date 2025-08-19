package com.dead_comedian.holyhell.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BloodlustEffect extends MobEffect {
    protected final RandomSource random = RandomSource.create();
    public boolean woah = false;

    public BloodlustEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }


    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        if (!woah) {
            livingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(livingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH) * 0.8);
            livingEntity.setHealth((float) (livingEntity.getHealth() * 0.8));
            woah = true;
        }
        super.onEffectAdded(livingEntity, amplifier);
    }

    @Override
    public void onMobRemoved(LivingEntity livingEntity, int amplifier, Entity.RemovalReason reason) {
        super.onMobRemoved(livingEntity, amplifier, reason);
        if (woah) {
            livingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(livingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH) / 0.8);
            woah = false;
        }
    }
}