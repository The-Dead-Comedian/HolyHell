package com.dead_comedian.holyhell.client.renderer;



import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.BabThreeModel;
import com.dead_comedian.holyhell.entity.BabThreeEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabThreeRenderer extends MobRenderer<BabThreeEntity, BabThreeModel<BabThreeEntity>> {
    private static final ResourceLocation TEXTURE =  ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/bab/bab3.png");

    public BabThreeRenderer(EntityRendererProvider.Context context) {
        super(context, new BabThreeModel<>(context.bakeLayer(HolyHellModelLayers.BAB2)), 0.6F);


    }

    @Override
    public ResourceLocation getTextureLocation(BabThreeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabThreeEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
