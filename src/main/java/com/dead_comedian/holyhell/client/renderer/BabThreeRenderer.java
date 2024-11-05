package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.BabThreeModel;
import com.dead_comedian.holyhell.client.models.entity.BabTwoModel;
import com.dead_comedian.holyhell.entity.custom.BabThreeEntity;
import com.dead_comedian.holyhell.entity.custom.BabTwoEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BabThreeRenderer extends MobEntityRenderer<BabThreeEntity, BabThreeModel<BabThreeEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/bab3.png");

    public BabThreeRenderer(EntityRendererFactory.Context context) {
        super(context, new BabThreeModel<>(context.getPart(HolyHellModelLayers.BAB2)), 0.6F);


    }

    @Override
    public Identifier getTexture(BabThreeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabThreeEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
