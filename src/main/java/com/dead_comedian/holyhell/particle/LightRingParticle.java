
package com.dead_comedian.holyhell.particle;


import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.function.Consumer;

public class LightRingParticle extends TextureSheetParticle {


    private static final Vector3f field_38334 = (new Vector3f(0.5F, 0.5F, 0.5F)).normalize();
    private static final Vector3f field_38335 = new Vector3f(-1.0F, -1.0F, 0.0F);


    public LightRingParticle(ClientLevel world, double xCoord, double yCoord, double zCoord,
                             SpriteSet spriteSet, double xd, double yd, double zd) {
        super(world, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.5f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.quadSize = 0f;
        this.lifetime = 50;
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
        quadSize += 0.20f;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet spriteProvider) {
            this.sprites = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel clientWorld,
                                       double x, double y, double z, double xd, double yd, double zd) {
            return new LightRingParticle(clientWorld, x, y, z, this.sprites, xd, yd, zd);
        }
    }


    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        this.alpha = 1.0F - Mth.clamp(((float)this.age + tickDelta) / (float)this.lifetime, 0.0F, 1.0F);
        this.buildGeometry(vertexConsumer, camera, tickDelta, (quaternion) -> {
            quaternion.mul((new Quaternionf()).rotationX(-36.14F));
        });


    }


    private void vertex(VertexConsumer vertexConsumer, Vector3f pos, float u, float v, int light) {
        vertexConsumer.vertex((double)pos.x(), (double)pos.y(), (double)pos.z()).uv(u, v).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
    }
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta,  Consumer<Quaternionf> rotator) {
        Vec3 vec3d = camera.getPosition();
        float f = (float)(Mth.lerp((double)tickDelta, this.xo, this.x) - vec3d.x());
        float g = (float)(Mth.lerp((double)tickDelta, this.yo, this.y) - vec3d.y());
        float h = (float)(Mth.lerp((double)tickDelta, this.zo, this.z) - vec3d.z());
        Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(0.0F, field_38334.x(), field_38334.y(), field_38334.z());
        rotator.accept(quaternionf);
        quaternionf.transform(field_38335);
        Vector3f[] vector3fs = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float i = this.getQuadSize(tickDelta);

        int j;
        for(j = 0; j < 4; ++j) {
            Vector3f vector3f = vector3fs[j];
            vector3f.rotate(quaternionf);
            vector3f.mul(i);
            vector3f.add(f, g, h);
        }

        j = this.getLightColor(tickDelta);
        this.vertex(vertexConsumer, vector3fs[0], this.getU1(), this.getV1(), j);
        this.vertex(vertexConsumer, vector3fs[1], this.getU1(), this.getV0(), j);
        this.vertex(vertexConsumer, vector3fs[2], this.getU0(), this.getV0(), j);
        this.vertex(vertexConsumer, vector3fs[3], this.getU0(), this.getV1(), j);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            return new LightRingParticle(level, pX, pY, pZ,  this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
