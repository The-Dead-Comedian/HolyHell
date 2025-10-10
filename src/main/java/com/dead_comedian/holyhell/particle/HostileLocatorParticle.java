package com.dead_comedian.holyhell.particle;

import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

public class HostileLocatorParticle extends TextureSheetParticle {
    private final SpriteSet spriteProvider;



    HostileLocatorParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
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

        if(!Minecraft.getInstance().player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)){
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
       return 15728640;
    }

    public record Provider(SpriteSet spriteProvider) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType defaultParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {

            return new HostileLocatorParticle(clientWorld, d, e, f, this.spriteProvider);
        }
    }
}
