package com.dead_comedian.holyhell.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SAINT_EYE;
    public ModFoodComponents() {
    }

    static{
    SAINT_EYE = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 2), 1.0F).build();
}
}
