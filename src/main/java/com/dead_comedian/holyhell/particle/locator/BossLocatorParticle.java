package com.dead_comedian.holyhell.particle.locator;

import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

public class BossLocatorParticle extends TextureSheetParticle {
    private final SpriteSet spriteProvider;


    BossLocatorParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.lifetime = 1;
        quadSize =  2f;
        this.alpha = 0.83f;
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
        super.render(buffer, renderInfo, partialTicks);
        if (!Minecraft.getInstance().player.getData(HolyHellAttachments.VISION_SHADER)) {
            remove();
        }
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

            return new BossLocatorParticle(clientWorld, d, e, f, this.spriteProvider);
        }
    }
}
