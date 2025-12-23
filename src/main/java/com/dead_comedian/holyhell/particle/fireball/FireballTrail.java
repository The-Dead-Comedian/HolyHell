package com.dead_comedian.holyhell.particle.fireball;


import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

public class FireballTrail extends TextureSheetParticle {
    private final SpriteSet spriteProvider;


    FireballTrail(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.lifetime = 7;
        quadSize =  0.15f;
        this.alpha = 1.0f;
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
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

            return new FireballTrail(clientWorld, d, e, f, this.spriteProvider);
        }
    }
}
