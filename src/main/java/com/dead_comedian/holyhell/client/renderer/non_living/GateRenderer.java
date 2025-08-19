package com.dead_comedian.holyhell.client.renderer.non_living;




import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.GateModel;
import com.dead_comedian.holyhell.entity.non_living.GateEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class GateRenderer extends EntityRenderer<GateEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/gate.png");
    private final GateModel<GateEntity> model;

    public GateRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new GateModel<>(context.bakeLayer(HolyHellModelLayers.GATE));
    }





    @Override
    public void render(GateEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public ResourceLocation getTextureLocation(GateEntity entity) {
        return TEXTURE;
    }

}