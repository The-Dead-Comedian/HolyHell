//    package com.dead_comedian.holyhell.client.renderer.non_living;
//
//
//
//import com.dead_comedian.holyhell.HolyHell;
//import com.dead_comedian.holyhell.client.model.entity.non_living.FallingSwordModel;
//import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
//import com.dead_comedian.holyhell.entity.non_living.FallingSwordEntity;
//import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
//import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.entity.EntityRenderer;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.resources.ResourceLocation;
//
//
//public class FallingSwordRenderer extends EntityRenderer<FallingSwordEntity> {
//    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/falling_swords.png");
//    private final FallingSwordModel<FallingSwordEntity> model;
//
//    public FallingSwordRenderer(EntityRendererProvider.Context context) {
//        super(context);
//        this.model = new FallingSwordModel<>(context.bakeLayer(HolyHellModelLayers.FALLING_SWORD));
//    }
//
//
//
//
//
//    @Override
//    public void render(FallingSwordEntity mobEntity, float f, float g, PoseStack matrixStack,
//                       MultiBufferSource vertexConsumerProvider, int i) {
//        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
//        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(FallingSwordEntity entity) {
//        return TEXTURE;
//    }
//
//}