package net.deadcomedian.holyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.deadcomedian.holyhell.HolyHell;

import net.deadcomedian.holyhell.entity.custom.AllSeerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AllSeerRenderer extends MobRenderer<AllSeerEntity, AllSeerModel<AllSeerEntity>> {
    public AllSeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AllSeerModel<>(pContext.bakeLayer(ModModelLayers.ALL_SEER_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(AllSeerEntity pEntity) {
        return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/allseer.png");
    }


}