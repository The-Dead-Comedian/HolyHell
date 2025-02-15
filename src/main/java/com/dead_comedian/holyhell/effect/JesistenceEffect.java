package com.dead_comedian.holyhell.effect;


import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;


public class JesistenceEffect extends MobEffect {


    public JesistenceEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.level().playSound(null,pLivingEntity.blockPosition(), HolyHellSound.RINGS_START.get(), SoundSource.PLAYERS,1f,1+pLivingEntity.level().random.nextFloat());
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

}