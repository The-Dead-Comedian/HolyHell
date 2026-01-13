package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.server.entity.AngelEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AngelRenderer extends MobRenderer<AngelEntity, AngelModel<AngelEntity>> {
    public AngelRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AngelModel<>(pContext.bakeLayer(HolyHellModelLayers.ANGEL)), 0.75f);

    }

    @Override
    public ResourceLocation getTextureLocation(AngelEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/angel.png");
    }


    @Override
    public void render(AngelEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}