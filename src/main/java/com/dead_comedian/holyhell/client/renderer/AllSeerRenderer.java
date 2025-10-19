package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.AllSeerModel;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.client.renderer.glow_layer.GlowingSeerLayer;
import com.dead_comedian.holyhell.entity.AllSeerEntity;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class AllSeerRenderer extends MobRenderer<AllSeerEntity, AllSeerModel<AllSeerEntity>> {
    public AllSeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AllSeerModel<>(pContext.bakeLayer(HolyHellModelLayers.ALL_SEER)), 0);
        this.addLayer(new GlowingSeerLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(AllSeerEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/all_seer.png");
    }


    @Override
    public void render(AllSeerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.scale(10,10,4);
        pMatrixStack.pushPose();
        pMatrixStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, -1572864);
    }
}