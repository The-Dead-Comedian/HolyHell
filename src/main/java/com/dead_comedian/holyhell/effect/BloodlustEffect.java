package com.dead_comedian.holyhell.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
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
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (!woah) {
            pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(pLivingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH) * 0.8);
            pLivingEntity.setHealth((float) (pLivingEntity.getHealth() * 0.8));
            woah = true;
        }
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (woah) {
            pLivingEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(pLivingEntity.getAttributeBaseValue(Attributes.MAX_HEALTH) / 0.8);
            woah = false;   
        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
}