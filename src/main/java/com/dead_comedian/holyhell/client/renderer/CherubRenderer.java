package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.CherubModel;
import com.dead_comedian.holyhell.entity.CherubEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;


public class CherubRenderer extends MobRenderer<CherubEntity, CherubModel<CherubEntity>> {
    public CherubRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CherubModel<>(pContext.bakeLayer(HolyHellModelLayers.CHERUB)), 0.75f);

    }

    @Override
    public ResourceLocation getTextureLocation(CherubEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/cherub.png");
    }


    @Override
    public void render(CherubEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}