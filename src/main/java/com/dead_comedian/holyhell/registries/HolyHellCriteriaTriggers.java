package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HolyHellCriteriaTriggers extends CriteriaTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> CRITERIA_TRIGGERS = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, Holyhell.MOD_ID);

    public static Supplier<PlayerTrigger> KILLED_BY_CROSS = CRITERIA_TRIGGERS.register("killed_by_cross", PlayerTrigger::new);
    public static Supplier<PlayerTrigger> BAB_MERGE = CRITERIA_TRIGGERS.register("bab_merge", PlayerTrigger::new);
    public static Supplier<PlayerTrigger> KEBAB = CRITERIA_TRIGGERS.register("kebab", PlayerTrigger::new);


    public static void register(IEventBus eventBus) {

        CRITERIA_TRIGGERS.register(eventBus);
    }


}