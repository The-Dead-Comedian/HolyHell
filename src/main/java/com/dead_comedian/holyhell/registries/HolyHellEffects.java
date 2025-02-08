package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.effect.ClarityEffect;
import com.dead_comedian.holyhell.effect.ConfusionEffect;
import com.dead_comedian.holyhell.effect.CustomStatusEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HolyHellEffects {
    public static final MobEffect CLARITY = registerStatusEffect("clarity",
            new ClarityEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            "7107DE5E-7CE8-4030-940E-514C1F160890", -0.25f,
                            AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final MobEffect CONFUSION = registerStatusEffect("confusion",
            new ConfusionEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final MobEffect JESISTANCE = registerStatusEffect("jesistance",
            new CustomStatusEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));

    public static final MobEffect ENLIGHTENED = registerStatusEffect("enlightened",
            new ConfusionEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    private static MobEffect registerStatusEffect(String name, MobEffect statusEffect) {
        return Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(Holyhell.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Holyhell.LOGGER.info("Registering Mod Effects for " + Holyhell.MOD_ID);
    }
}
