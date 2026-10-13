package com.dead_comedian.holyhell.client.renderer.non_living;



import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.ChakramModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
import com.dead_comedian.holyhell.server.entity.non_living.ChakramEntity;
import com.dead_comedian.holyhell.server.entity.non_living.GlobularDomeEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class ChakramRenderer extends EntityRenderer<ChakramEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/chakram.png");
    private final ChakramModel<ChakramEntity> model;

    public ChakramRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ChakramModel<>(context.bakeLayer(HolyHellModelLayers.CHAKRAM));
    }





    @Override
    public void render(ChakramEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {

        poseStack.pushPose();


        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        float n = (entity.tickCount) * (float) (-44);
        poseStack.mulPose(Axis.YP.rotationDegrees(-n));


        poseStack.translate(0,-1.5,0);
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, entity.isFoil());
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ChakramEntity entity) {
        return TEXTURE;
    }

}