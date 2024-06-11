package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect CLARITY;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Holyhell.MOD_ID, name),
                new Clarityeffect(StatusEffectCategory.BENEFICIAL, 16379618));
    }

    public static void registerEffects() {
        CLARITY = registerStatusEffect("clarity");
    }
}
