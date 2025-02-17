package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.client.model.entity.HereticModel;
import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HereticRenderer extends MobRenderer<HereticEntity, HereticModel<HereticEntity>> {
    public HereticRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HereticModel<>(pContext.bakeLayer(HolyHellModelLayers.HERETIC)), 0.75f);

    }

    @Override
    public ResourceLocation getTextureLocation(HereticEntity pEntity) {
        return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/heretic.png");
    }


    @Override
    public void render(HereticEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}