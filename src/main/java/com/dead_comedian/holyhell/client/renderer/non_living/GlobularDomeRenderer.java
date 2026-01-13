package com.dead_comedian.holyhell.client.renderer.non_living;



import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
import com.dead_comedian.holyhell.server.entity.non_living.GlobularDomeEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class GlobularDomeRenderer extends EntityRenderer<GlobularDomeEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/globular_dome.png");
    private final GlobularDomeModel<GlobularDomeEntity> model;

    public GlobularDomeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new GlobularDomeModel<>(context.bakeLayer(HolyHellModelLayers.GLOBULAR_DOME));
    }





    @Override
    public void render(GlobularDomeEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public ResourceLocation getTextureLocation(GlobularDomeEntity entity) {
        return TEXTURE;
    }

}