package com.dead_comedian.holyhell.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import java.util.function.Supplier;

public class StaticAmbientLoopSound extends AbstractTickableSoundInstance {
    private final Supplier<Boolean> shouldStop;
    private final Supplier<Float> getPitch;
    private final Runnable onStop;

    protected StaticAmbientLoopSound(int x, int y, int z, SoundEvent sound, SoundSource source,

                                     Supplier<Boolean> shouldStop, Supplier<Float> getPitch, Runnable onStop) {
        super(sound, source, SoundInstance.createUnseededRandom());


        this.x = x;
        this.y = y;
        this.z = z;
        this.looping = true;
        this.volume = 0;

        this.shouldStop = shouldStop;
        this.getPitch = getPitch;
        this.onStop = onStop;
    }

    @Override
    public void tick()
    {
        if(this.isStopped())
        {
            return;
        }

        if(shouldStop.get())
        {
            this.stop();
            onStop.run();
            return;
        }

        volume = 1;
        pitch = getPitch.get();
    }

    @Override
    public boolean canStartSilent()
    {
        return true;
    }
}
