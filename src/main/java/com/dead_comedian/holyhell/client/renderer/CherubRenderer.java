package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.client.model.entity.CherubModel;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.entity.CherubEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.awt.*;


public class CherubRenderer extends MobRenderer<CherubEntity, CherubModel<CherubEntity>> {
    public CherubRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CherubModel<>(pContext.bakeLayer(HolyHellModelLayers.CHERUB)), 0.75f);

    }

    @Override
    public ResourceLocation getTextureLocation(CherubEntity pEntity) {
        return new ResourceLocation(HolyHell.MOD_ID, "textures/entity/cherub.png");
    }


    @Override
    public void render(CherubEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
//        if ( !pEntity.isInvisible()) {
//           ColoredParticleInitialData data = new ColoredParticleInitialData(0xFFFF77);
//
//
//
//            pMatrixStack.pushPose();
//				List<TrailPoint> positions = ((PositionTrackedEntity) CherubEntity).getPastPositions();
//				VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setRenderType(getTrailRenderType());
//
//				float size = 0.15f;
//				float alpha = 1f;	y
//
//				float x = (float) Mth.lerp(pPartialTicks, pEntity.prevX, pEntity.getX());
//				float y = (float) Mth.lerp(pPartialTicks, pEntity.prevY, pEntity.getY());
//				float z = (float) Mth.lerp(pPartialTicks, pEntity.prevZ, pEntity.getZ());
//
//            pMatrixStack.translate(-x, -y, -z);
//				builder.setColor(new Color(0xFFFF77))
//					.setAlpha(alpha)
//					.renderTrail(pMatrixStack,
//						positions,
//						f -> Mth.sqrt(f) * size,
//						f -> builder.setAlpha((float) Math.cbrt(Math.max(0, (alpha * f) - 0.1f)))
//					)
//					.renderTrail(pMatrixStack,
//						positions,
//						f -> (Mth.sqrt(f) * size) / 1.5f,
//						f -> builder.setAlpha((float) Math.cbrt(Math.max(0, (((alpha * f) / 1.5f) - 0.1f))))
//					);
//
//				pMatrixStack.popPose();
//			}

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}