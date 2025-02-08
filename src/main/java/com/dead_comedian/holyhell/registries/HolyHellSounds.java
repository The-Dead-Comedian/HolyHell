package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class HolyHellSounds {

    public static final SoundEvent CLARITY_MUSIC = registerSoundEvent("clarity_music");






    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Holyhell.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        Holyhell.LOGGER.info("Registering Sounds for " + Holyhell.MOD_ID);
    }
}
