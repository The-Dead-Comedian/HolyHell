package com.dead_comedian.holyhell.server.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;


public class DivineProtectionEffect extends MobEffect {



    public DivineProtectionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {

        return super.shouldApplyEffectTickThisTick(duration, amplifier);
    }

}