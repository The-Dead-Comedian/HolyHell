package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.BabTwoModel;
import com.dead_comedian.holyhell.entity.BabTwoEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabTwoRenderer extends MobRenderer<BabTwoEntity, BabTwoModel<BabTwoEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/bab2.png");

    public BabTwoRenderer(EntityRendererProvider.Context context) {
        super(context, new BabTwoModel<>(context.bakeLayer(HolyHellModelLayers.BAB1)), 0.6F);


    }

    @Override
    public ResourceLocation getTextureLocation(BabTwoEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabTwoEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
