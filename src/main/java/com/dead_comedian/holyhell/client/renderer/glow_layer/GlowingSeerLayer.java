package com.dead_comedian.holyhell.client.renderer.glow_layer;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class GlowingSeerLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation EMISSIVE_TEXTURE = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/all_seer/all_seer.png");
    private static final ResourceLocation EMISSIVE_TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/all_seer/all_seer_blinking.png");

    public GlowingSeerLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       T entity, float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {

        if (entity instanceof AllSeerEntity seerEntity) {
            VertexConsumer vertexconsumer = seerEntity.getShield() <= 0 && ageInTicks % 14 <= 6 ? bufferSource.getBuffer(RenderType.eyes(EMISSIVE_TEXTURE_2)) : bufferSource.getBuffer(RenderType.eyes(EMISSIVE_TEXTURE));
            this.getParentModel().renderToBuffer(
                    poseStack,
                    vertexconsumer,
                    15728880,
                    OverlayTexture.NO_OVERLAY
            );
        }
    }


}