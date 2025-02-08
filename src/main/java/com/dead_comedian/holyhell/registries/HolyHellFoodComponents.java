package com.dead_comedian.holyhell.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class HolyHellFoodComponents {
    public static final FoodProperties SAINT_EYE;
    public HolyHellFoodComponents() {
    }

    static{
    SAINT_EYE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 2), 1.0F).build();
}
}
