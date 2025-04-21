package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.client.model.entity.HolyCowModel;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.entity.HolyCowEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HolyCowRenderer extends MobRenderer<HolyCowEntity, HolyCowModel<HolyCowEntity>> {
    public HolyCowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HolyCowModel<>(pContext.bakeLayer(HolyHellModelLayers.HOLY_COW)), 0.75f);

    }

    @Override
    public ResourceLocation getTextureLocation(HolyCowEntity pEntity) {
        return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/holy_cow.png");
    }


    @Override
    public void render(HolyCowEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}