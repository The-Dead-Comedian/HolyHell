package com.dead_comedian.holyhell.client.renderer.non_living;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.AngelProjectileModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.JudgementSpearModel;
import com.dead_comedian.holyhell.server.entity.non_living.AngelProjectileEntity;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class JudgementSpearRenderer extends EntityRenderer<JudgementSpearEntity> {

    private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/judgement_spear.png");
    private final JudgementSpearModel<JudgementSpearEntity> model;

    public JudgementSpearRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new JudgementSpearModel<>(context.bakeLayer(HolyHellModelLayers.JUDGEMENT_SPEAR));
    }


    @Override
    public void render(JudgementSpearEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot())));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot()) - 90));
        if (pEntity.getSagnant()) {
            pPoseStack.translate(0, -1.8f, 0);
        }
        pPoseStack.pushPose();
        VertexConsumer vertexConsumer = pBuffer.getBuffer(this.model.renderType(TEXTURE1));
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();

        // Call the parent render method
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, 15728640);
    }

    @Override
    protected int getBlockLightLevel(JudgementSpearEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(JudgementSpearEntity entity) {
        return TEXTURE1;
    }

}