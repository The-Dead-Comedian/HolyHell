package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.effect.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

public class HolyHellEffects {


    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Holyhell.MOD_ID);


    public static final DeferredHolder<MobEffect,ClarityEffect> CLARITY = MOB_EFFECTS.register("clarity",
            () -> new ClarityEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final DeferredHolder<MobEffect, AngelicVisionEffect> ANGELIC_VISION = MOB_EFFECTS.register("angelic_vision",
            () -> new AngelicVisionEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    public static final DeferredHolder<MobEffect, DivineProtectionEffect> DIVINE_PROTECTION = MOB_EFFECTS.register("divine_protection",
            () -> new DivineProtectionEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    public static final DeferredHolder<MobEffect, DivineProtectionCooldownEffect> DIVINE_PROTECTION_COOLDOWN = MOB_EFFECTS.register("divine_protection_cooldown",
            () -> new DivineProtectionCooldownEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final DeferredHolder<MobEffect, ConfusionEffect> CONFUSION = MOB_EFFECTS.register("confusion",
            () -> new ConfusionEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final DeferredHolder<MobEffect, BloodlustEffect> BLOODLUST = MOB_EFFECTS.register("bloodlust",
            () -> new BloodlustEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final DeferredHolder<MobEffect, JesistenceEffect> JESISTANCE = MOB_EFFECTS.register("jesistance",
            () -> new JesistenceEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
