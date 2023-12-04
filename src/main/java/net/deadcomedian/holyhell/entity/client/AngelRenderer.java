package net.deadcomedian.holyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.entity.custom.AngelEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AngelRenderer extends MobRenderer<AngelEntity, AngelModel<AngelEntity>> {
    public AngelRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AngelModel<>(pContext.bakeLayer(ModModelLayers.ANGEL_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(AngelEntity pEntity) {
        return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/angel.png");
    }


}