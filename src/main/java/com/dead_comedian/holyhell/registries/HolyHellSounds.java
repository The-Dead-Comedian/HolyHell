package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class HolyHellSounds {

    public static final SoundEvent CLARITY_MUSIC = registerSoundEvent("clarity_music");






    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Holyhell.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Holyhell.LOGGER.info("Registering Sounds for " + Holyhell.MOD_ID);
    }
}
