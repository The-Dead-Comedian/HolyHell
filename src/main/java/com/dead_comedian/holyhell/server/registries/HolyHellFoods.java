package com.dead_comedian.holyhell.server.registries;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class HolyHellFoods {

    public HolyHellFoods() {
    }

    public static final FoodProperties SAINT_EYE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.8f).effect(new MobEffectInstance(HolyHellEffects.ANGELIC_VISION, 600), 1).build();

}
