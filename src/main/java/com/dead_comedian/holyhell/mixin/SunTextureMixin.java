package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public abstract class SunTextureMixin {

    @Unique
    int sprite_no = 0;
    @Unique
    int cooldown = 0;
    @Unique
    int switch_time = 150;
    @Unique
    int switch_count = 0;

    @Shadow
    @Nullable
    private ClientLevel level;


    @Inject(method = "renderSky", at = @At("TAIL"))
    private void renderCustomZenith(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float partialTick, Camera camera, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {
            Level level = Minecraft.getInstance().level;
            if (level == null || level.dimension() != Level.OVERWORLD) {
                return;
            }

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(false);


            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            holyHell$animateEye();

            PoseStack poseStack = new PoseStack();
            poseStack.last().pose().mul(frustumMatrix);


            BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

            poseStack.pushPose();

            poseStack.translate(0.0F, 0.0F, 0.0F);
            poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));

            float size = 200.0F;

            bufferBuilder.addVertex(poseStack.last().pose(), -size, 100.0F, -size).setUv(0.0F, 0.0F);
            bufferBuilder.addVertex(poseStack.last().pose(), size, 100.0F, -size).setUv(1.0F, 0.0F);
            bufferBuilder.addVertex(poseStack.last().pose(), size, 100.0F, size).setUv(1.0F, 1.0F);
            bufferBuilder.addVertex(poseStack.last().pose(), -size, 100.0F, size).setUv(0.0F, 1.0F);

            BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());


            poseStack.popPose();

            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
        }
    }


    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"))
    private void redirectSunTexture(int textureUnit, ResourceLocation originalTexture) {

        if (originalTexture.toString().contains("sun")) {


            if (Minecraft.getInstance().player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA)) {

                RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye5.png"));

                RenderSystem.setShaderColor(1F, 1F, 1F, 0.0F);
                return;
            }
        }
        RenderSystem.setShaderTexture(textureUnit, originalTexture);
    }


    @Unique
    private void holyHell$animateEye() {

        switch_time--;
        if (switch_count <= 6 && cooldown <= 0 && switch_time <= 0) {
            sprite_no = level.getRandom().nextInt(0, 5);
            switch_time = level.getRandom().nextInt(70, 230);
            switch_count++;
        } else if (switch_count >= 6 && switch_time <= 0) {
            sprite_no = 0;
            switch_count = 0;
            cooldown = level.getRandom().nextInt(500, 12000);

        }

        if (cooldown >= 0) {
            cooldown--;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        if (sprite_no == 0) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye1.png"));
        } else if (sprite_no == 1) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye2.png"));
        } else if (sprite_no == 2) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye3.png"));
        } else if (sprite_no == 3) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye4.png"));
        } else if (sprite_no == 4) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye5.png"));
        }
    }
}
