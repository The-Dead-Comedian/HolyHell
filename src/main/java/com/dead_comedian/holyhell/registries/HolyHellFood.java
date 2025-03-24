package com.dead_comedian.holyhell.registries;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class HolyHellFood {
    public static final FoodProperties SAINT_EYE;
    public HolyHellFood() {
    }

    static{
    SAINT_EYE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.8F).build();
}
}
