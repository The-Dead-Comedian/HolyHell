package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.other.FallingSwordModel;
import com.dead_comedian.holyhell.client.models.entity.other.SwordCrossModel;
import com.dead_comedian.holyhell.entity.custom.other.FallingSwordEntity;
import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;


public class SwordCrossRenderer<T extends SwordCrossEntity> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/sword_cross.png");
    private final SwordCrossModel<SwordCrossEntity> model;

    public SwordCrossRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SwordCrossModel<>(context.bakeLayer(HolyHellModelLayers.SWORD_CROSS));
    }





    @Override
    public void render(T mobEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        Minecraft mc = Minecraft.getInstance();
        matrixStack.mulPose(Axis.YP.rotationDegrees(-mc.gameRenderer.getMainCamera().getYRot()));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public ResourceLocation getTextureLocation(SwordCrossEntity entity) {
        return TEXTURE;
    }

}
