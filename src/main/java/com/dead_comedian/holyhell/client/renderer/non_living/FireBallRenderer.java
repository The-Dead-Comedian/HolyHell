package com.dead_comedian.holyhell.client.renderer.non_living;



import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.FireBallModel;
import com.dead_comedian.holyhell.entity.non_living.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class FireBallRenderer extends EntityRenderer<FireBallEntity> {
    int timer = 1;
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball3.png");
    private final FireBallModel<FireBallEntity> model;

    public FireBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FireBallModel<>(context.bakeLayer(HolyHellModelLayers.FIREBALL));
    }


    @Override
    public void render(FireBallEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // Save the current pose state
        pPoseStack.pushPose();

        // Apply rotations from the second method
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));

        // Handle shake animation if your entity has this property
        float shakeFactor = 0.0F;
        if (pEntity.shakeTime > 0) {
            shakeFactor = (float)pEntity.shakeTime - pPartialTicks;
            if (shakeFactor > 0.0F) {
                float shake = -Mth.sin(shakeFactor * 3.0F) * shakeFactor;
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(shake));
            }
        }

        // Apply the 45-degree X-axis rotation
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(90.0F));

        // Handle texture cycling from first method
        VertexConsumer vertexConsumer = null;
        timer++;
        if (timer <= 20) {
            vertexConsumer = pBuffer.getBuffer(this.model.renderType(TEXTURE1));
        } else if (timer <= 40 && timer > 20) {
            vertexConsumer = pBuffer.getBuffer(this.model.renderType(TEXTURE2));
        } else if (timer <= 60 && timer > 40) {
            vertexConsumer = pBuffer.getBuffer(this.model.renderType(TEXTURE3));
            timer = 1;
        }

        // Render the model
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        // Restore the original pose state
        pPoseStack.popPose();

        // Call the parent render method
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, 15728640);
    }

    @Override
    public ResourceLocation getTextureLocation(FireBallEntity entity) {
        timer++;

        if (timer <= 20) {
            return TEXTURE1;
        } else if (timer <= 40 && timer > 20) {
            return TEXTURE1;
        } else if (timer <= 60 && timer > 40) {
            timer = 1;
            return TEXTURE1;
        }
        return TEXTURE1;
    }

}