package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HolyHell.MOD_ID);


    public static final RegistryObject<SoundEvent> CROSS_FALL = registerSoundEvents("cross_fall");

    public static final RegistryObject<SoundEvent> HOLY_SHIELD_BLOCK = registerSoundEvents("holy_shield_block");

    public static final RegistryObject<SoundEvent> RINGS_HOLD = registerSoundEvents("rings_hold");

    public static final RegistryObject<SoundEvent> CANDELABRA_PLACE = registerSoundEvents("candelabra_place");
    public static final RegistryObject<SoundEvent> CANDELABRA_LIGHT = registerSoundEvents("candelabra_light");
    public static final RegistryObject<SoundEvent> DIVINING_TABLE_INTERACT = registerSoundEvents("divining_table_interact");
    public static final RegistryObject<SoundEvent> GLOBE_SPIN = registerSoundEvents("globe_spin");

    public static final ForgeSoundType CANDELABRA_SOUNDS = new ForgeSoundType(1f, 1f,
            () -> SoundEvents.LANTERN_BREAK, () -> SoundEvents.LANTERN_STEP, HolyHellSound.CANDELABRA_PLACE,
            () -> SoundEvents.LANTERN_HIT, () -> SoundEvents.LANTERN_FALL);

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HolyHell.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}