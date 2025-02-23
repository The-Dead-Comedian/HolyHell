package com.dead_comedian.holyhell.particle;


import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class StunParticle2 extends TextureSheetParticle {
    private final SpriteSet spriteProvider;
    private boolean birtd;

    StunParticle2(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, boolean birtd) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.lifetime = 55;
        quadSize = this.birtd ? 0.1f : 0.15f;
        this.birtd = birtd;
        this.alpha = this.birtd ? this.alpha : 0.0f;
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();


        if (birtd) {
            if (age < lifetime) {
                if (alpha > 0.1F) alpha -= 0.015F;
                else this.remove();
            }
            xd = Mth.cos(age * 0.115F) * -0.05F;
            zd = Mth.sin(age * 0.115F) * -0.05F;
        } else {
            // Create a circular orbit effect
            float radius = 0.8F; // Radius of the circular motion
            float speed = 0.4F;  // Speed of rotation

            // Circular motion equations using sine and cosine
            xd = Mth.cos(age * speed) * radius;
            zd = Mth.sin(age * speed) * radius;

            // Add a small up-and-down motion to enhance the dizziness effect
            yd = Mth.sin(age * 0.5F) * 0.1F;

            // Make particles fade in and out to enhance the effect
            if (age < lifetime * 0.1F) {
                alpha = Math.min(1.0F, age / (lifetime * 0.1F));
            } else if (age > lifetime * 0.8F) {
                alpha = Math.max(0.0F, 1.0F - (age - lifetime * 0.8F) / (lifetime * 0.2F));
            } else {
                alpha = 0.9F + Mth.sin(age * 0.4F) * 0.1F; // Slight pulsing effect
            }
        }

        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    protected int getLightColor(float f) {
        BlockPos blockPos = new BlockPos((int) this.x, (int) this.y, (int) this.z);
        if (this.level.hasChunkAt(blockPos)) {
            return LevelRenderer.getLightColor(this.level, blockPos);
        }
        return 0;
    }

    public record Provider(SpriteSet spriteProvider) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {

            return new StunParticle2(clientWorld, d, e, f, this.spriteProvider, true);
        }
    }
}
