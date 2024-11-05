package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.*;

import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BabOneRenderer extends MobEntityRenderer<BabOneEntity, BabOneModel<BabOneEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/bab1.png");

    public BabOneRenderer(EntityRendererFactory.Context context) {
        super(context, new BabOneModel<>(context.getPart(HolyHellModelLayers.BAB)), 0.6F);

    }

    @Override
    public Identifier getTexture(BabOneEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabOneEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
