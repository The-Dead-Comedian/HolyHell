package com.dead_comedian.holyhell.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;


public class JesistenceEffect extends MobEffect {

    int repeat = 75;

    public JesistenceEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {


        repeat--;
        if (repeat <= 0) {
            repeat = 75;
        }
        return super.shouldApplyEffectTickThisTick(duration, amplifier);
    }

}