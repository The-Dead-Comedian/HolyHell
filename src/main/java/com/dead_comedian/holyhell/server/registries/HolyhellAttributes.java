package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HolyhellAttributes {

    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, HolyHell.MOD_ID);

    public static Holder<Attribute> MAX_SEER_SHIELD = ATTRIBUTES.register("max_seer_shield", () -> (
            new RangedAttribute("holyhell.attribute.name.generic.max_seer_shield", (double) 20.0F, (double) 1.0F, (double) 1024.0F)).setSyncable(true)
    );

    public static void register(IEventBus iEventBus) {
        ATTRIBUTES.register(iEventBus);
    }
}
