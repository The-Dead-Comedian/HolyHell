package net.deadcomedian.holyhell.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.deadcomedian.holyhell.HolyHell;

import net.deadcomedian.holyhell.entity.custom.AllSeerEntity;
import net.deadcomedian.holyhell.entity.custom.HailingHereticEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HailingHereticRenderer extends MobRenderer<HailingHereticEntity, HailingHereticModel<HailingHereticEntity>> {
	public HailingHereticRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new HailingHereticModel<>(pContext.bakeLayer(ModModelLayers.HAILING_HERETIC_LAYER)), 1f);
	}

	@Override
	public ResourceLocation getTextureLocation(HailingHereticEntity pEntity) {
		return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/hailingheretic.png");
	}


}