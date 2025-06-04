package com.dead_comedian.holyhell.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;


public class DivineProtectionEffect extends MobEffect {

    int repeat = 75;

    public DivineProtectionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        repeat--;
        if(repeat <= 0){
            repeat=75;
        }
        return super.isDurationEffectTick(pDuration, pAmplifier);
    }

}