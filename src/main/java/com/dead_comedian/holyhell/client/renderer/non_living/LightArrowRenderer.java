package com.dead_comedian.holyhell.client.renderer.non_living;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.JudgementSpearModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.LightArrowModel;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.entity.non_living.LightArrowEntity;
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


public class LightArrowRenderer extends EntityRenderer<LightArrowEntity> {

    private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/light_arrow.png");
    private final LightArrowModel<LightArrowEntity> model;

    public LightArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LightArrowModel<>(context.bakeLayer(HolyHellModelLayers.LIGHT_ARROW));
    }


    @Override
    public void render(LightArrowEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {



        pPoseStack.pushPose();
        pPoseStack.translate(0, -0.75f, 0);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-pEntity.getYRot()-180));
        VertexConsumer vertexConsumer = pBuffer.getBuffer(this.model.renderType(TEXTURE1));
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
        pPoseStack.popPose();

        // Call the parent render method
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, 15728640);
    }

    @Override
    protected int getBlockLightLevel(LightArrowEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(LightArrowEntity entity) {
        return TEXTURE1;
    }

}