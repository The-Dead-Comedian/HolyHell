package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellEffects {


    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HolyHell.MOD_ID);


    public static final RegistryObject<MobEffect> CLARITY = MOB_EFFECTS.register("clarity",
            () -> new ClarityEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            "7107DE5E-7CE8-4030-940E-514C1F160890", -0.25f,
                            AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<DivineProtectionEffect> DIVINE_PROTECTION = MOB_EFFECTS.register("divine_protection",
            () -> new DivineProtectionEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    public static final RegistryObject<DivineProtectionCooldownEffect> DIVINE_PROTECTION_COOLDOWN = MOB_EFFECTS.register("divine_protection_cooldown",
            () -> new DivineProtectionCooldownEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<ConfusionEffect> CONFUSION = MOB_EFFECTS.register("confusion",
            () -> new ConfusionEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<BloodlustEffect> BLOODLUST = MOB_EFFECTS.register("bloodlust",
            () -> new BloodlustEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<JesistenceEffect> JESISTANCE = MOB_EFFECTS.register("jesistance",
            () -> new JesistenceEffect(MobEffectCategory.BENEFICIAL, 0x36ebab));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
