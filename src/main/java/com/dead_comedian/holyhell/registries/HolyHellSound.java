package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HolyHell.MOD_ID);


    public static final RegistryObject<SoundEvent> CROSS_FALL = registerSoundEvents("cross_fall");

    public static final RegistryObject<SoundEvent> HOLY_SHIELD_BLOCK = registerSoundEvents("holy_shield_block");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HolyHell.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}