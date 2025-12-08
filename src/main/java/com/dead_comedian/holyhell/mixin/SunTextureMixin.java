package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyhellDimensions;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
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

    private static final ResourceLocation SKY_BOX = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye_ring.png");

    @Unique
    int spriteNo = 0;
    @Unique
    int cooldown = 0;
    @Unique
    int switchTime = 150;
    @Unique
    int switchCount = 0;

    @Shadow
    @Nullable
    private ClientLevel level;

    @Unique
    private static final float[][][] CUBE_FACES = {
            {{-1, -1, -1}, {-1, -1, 1}, {-1, 1, 1}, {-1, 1, -1}}, // -X
            {{1, -1, 1}, {1, -1, -1}, {1, 1, -1}, {1, 1, 1}}, // +X
            {{-1, 1, -1}, {-1, 1, 1}, {1, 1, 1}, {1, 1, -1}}, // +Y
            {{-1, -1, 1}, {-1, -1, -1}, {1, -1, -1}, {1, -1, 1}}, // -Y
            {{-1, -1, -1}, {-1, 1, -1}, {1, 1, -1}, {1, -1, -1}}, // -Z
            {{1, -1, 1}, {-1, -1, 1}, {-1, 1, 1}, {1, 1, 1}}  // +Z
    };


    //ANGEL

    @Unique
    private void drawFace(Matrix4f matrix, ResourceLocation texture, float[][] verts) {
        RenderSystem.setShaderTexture(0, texture);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);


        bufferBuilder.addVertex(matrix, verts[0][0], verts[0][1], verts[0][2]).setUv(0.0F, 0.0F);
        bufferBuilder.addVertex(matrix, verts[1][0], verts[1][1], verts[1][2]).setUv(1.0F, 0.0F);
        bufferBuilder.addVertex(matrix, verts[2][0], verts[2][1], verts[2][2]).setUv(1.0F, 1.0F);
        bufferBuilder.addVertex(matrix, verts[3][0], verts[3][1], verts[3][2]).setUv(0.0F, 1.0F);

        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());


    }

    @Inject(method = "renderSky", at = @At("TAIL"))
    private void renderAngelBottom(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float partialTick, Camera camera, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;

        if (player != null) {
            if (player.level().dimension() == HolyhellDimensions.ANGEL) {
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.disableCull();

                PoseStack poseStack = new PoseStack();
                poseStack.last().pose().mul(frustumMatrix);
                Matrix4f matrix = poseStack.last().pose();

                poseStack.pushPose();

                // Draw each face of the cube
                for (int i = 0; i < 6; i++) {
                    drawFace(matrix, SKY_BOX, CUBE_FACES[i]);
                }

                poseStack.popPose();
                RenderSystem.enableCull();
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
            }
        }
    }



    //END

    @Unique
    public boolean isLookingIntoVoidInEnd(LocalPlayer player) {
        if (player == null) return false;

        // 1. Check End dimension
        if (player.level().dimension() != Level.END) {
            return false;
        }

        // 2. Looking downward: pitch (XRot) > some threshold
        float pitch = player.getXRot();
        if (pitch < 0.0f) {
            return false;
        }

        // 3. Ray trace along look vector
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getLookAngle();
        Vec3 target = eyePos.add(lookVec.scale(256));  // cast a long ray

        ClipContext ctx = new ClipContext(eyePos, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);

        BlockHitResult hit = player.level().clip(ctx);

        boolean miss = (hit.getType() == HitResult.Type.MISS);
        boolean targetBelowVoid = (target.y < 0);

        return miss && targetBelowVoid;
    }

    @Inject(method = "renderSky", at = @At("TAIL"))
    private void renderBigVoidEyes(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float partialTick, Camera camera, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getData(HolyHellAttachments.VISION_SHADER)) {
            Level level = Minecraft.getInstance().level;
            if (level == null || level.dimension() != Level.END) {
                return;
            }

            if (isLookingIntoVoidInEnd(Minecraft.getInstance().player) && Minecraft.getInstance().player.hasEffect(HolyHellEffects.PARANOIA)) {

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


                RandomSource randomsource = RandomSource.create(10842L);
                int eyeCount = (Minecraft.getInstance().player.getEffect(HolyHellEffects.PARANOIA).getAmplifier() + 1) * 75;
                for (int j = 0; j < eyeCount; ++j) {
                    float f1 = randomsource.nextFloat() * 2.0F - 1.0F;
                    float f2 = randomsource.nextFloat() * 2.0F - 1.0F;
                    float f3 = randomsource.nextFloat() * 2.0F - 1.0F;
                    float f5 = Mth.lengthSquared(f1, f2, f3);

                    RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/big_void_eye.png"));


                    if (!(f5 <= 0.010000001F) && !(f5 >= 1.0F)) {
                        Vector3f vector3f = (new Vector3f(f1, f2, f3)).normalize(100.0F);
                        float f6 = (float) (randomsource.nextDouble() * (double) (float) Math.PI * (double) 2.0F);
                        Quaternionf quaternionf = (new Quaternionf()).rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), vector3f).rotateZ(f6);

                        float size = 10.0F;

                        bufferBuilder.addVertex(poseStack.last().pose(), -size, 130.0F, -size).setUv(0.0F, 0.0F);
                        bufferBuilder.addVertex(poseStack.last().pose(), size, 130.0F, -size).setUv(1.0F, 0.0F);
                        bufferBuilder.addVertex(poseStack.last().pose(), size, 130.0F, size).setUv(1.0F, 1.0F);
                        bufferBuilder.addVertex(poseStack.last().pose(), -size, 130.0F, size).setUv(0.0F, 1.0F);

                        poseStack.mulPose(quaternionf);

                    }
                }


                BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());


                poseStack.popPose();

                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
            }
        }
    }


    //OVERWORLD


    @Inject(method = "renderSky", at = @At("TAIL"))
    private void renderCustomZenith(Matrix4f frustumMatrix, Matrix4f projectionMatrix, float partialTick, Camera camera, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getData(HolyHellAttachments.VISION_SHADER)) {
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


            if (Minecraft.getInstance().player.getData(HolyHellAttachments.VISION_SHADER)) {

                RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye5.png"));

                RenderSystem.setShaderColor(1F, 1F, 1F, 0.0F);
                return;
            }
        }
        RenderSystem.setShaderTexture(textureUnit, originalTexture);
    }

    @Unique
    private void holyHell$animateEye() {

        switchTime--;
        if (switchCount <= 6 && cooldown <= 0 && switchTime <= 0) {
            spriteNo = level.getRandom().nextInt(0, 5);
            switchTime = level.getRandom().nextInt(70, 230);
            switchCount++;
        } else if (switchCount >= 6 && switchTime <= 0) {
            spriteNo = 0;
            switchCount = 0;
            cooldown = level.getRandom().nextInt(500, 12000);

        }

        if (cooldown >= 0) {
            cooldown--;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        if (spriteNo == 0) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye1.png"));
        } else if (spriteNo == 1) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye2.png"));
        } else if (spriteNo == 2) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye3.png"));
        } else if (spriteNo == 3) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye4.png"));
        } else if (spriteNo == 4) {
            RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/environment/eye5.png"));
        }
    }
}
