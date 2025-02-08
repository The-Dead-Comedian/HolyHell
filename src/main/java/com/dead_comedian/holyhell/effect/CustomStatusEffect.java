package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.registries.HolyHellSounds;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CustomStatusEffect extends MobEffect {


    public CustomStatusEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }


}
