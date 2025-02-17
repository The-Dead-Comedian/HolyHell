package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.BabOneModel;

import com.dead_comedian.holyhell.entity.BabOneEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabOneRenderer extends MobRenderer<BabOneEntity, BabOneModel<BabOneEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/bab1.png");

    public BabOneRenderer(EntityRendererProvider.Context context) {
        super(context, new BabOneModel<>(context.bakeLayer(HolyHellModelLayers.BAB)), 0.6F);

    }

    @Override
    public ResourceLocation getTextureLocation(BabOneEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabOneEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
