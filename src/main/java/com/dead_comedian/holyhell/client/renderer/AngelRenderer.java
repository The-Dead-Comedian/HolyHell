package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class AngelRenderer extends MobRenderer<AngelEntity, AngelModel<AngelEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/angel.png");

    public AngelRenderer(EntityRendererProvider.Context context) {
        super(context, new AngelModel<>(context.bakeLayer(HolyHellModelLayers.ANGEL)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(AngelEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AngelEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}