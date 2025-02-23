package com.dead_comedian.holyhell.effect;


import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;


public class JesistenceEffect extends MobEffect {

    int repeat = 75;

    public JesistenceEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {



        repeat--;
        if(repeat <= 0){
            repeat=75;
        }
        System.out.println(repeat);
        return super.isDurationEffectTick(pDuration, pAmplifier);
    }
}