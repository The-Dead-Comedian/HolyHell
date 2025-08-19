
package com.dead_comedian.holyhell.particle;


import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class KamikazeExplosionParticle extends TextureSheetParticle {


    private static final Vector3f field_38334 = (new Vector3f(0.5F, 0.5F, 0.5F)).normalize();
    private static final Vector3f field_38335 = new Vector3f(-1.0F, -1.0F, 0.0F);


    public KamikazeExplosionParticle(ClientLevel world, double xCoord, double yCoord, double zCoord,
                                     SpriteSet spriteSet, double xd, double yd, double zd) {
        super(world, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.5f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.quadSize = 0f;
        this.lifetime = 30;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        quadSize += 0.08f;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet spriteProvider) {
            this.sprites = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel clientWorld,
                                       double x, double y, double z, double xd, double yd, double zd) {
            return new KamikazeExplosionParticle(clientWorld, x, y, z, this.sprites, xd, yd, zd);
        }
    }




    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            return new KamikazeExplosionParticle(level, pX, pY, pZ,  this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
