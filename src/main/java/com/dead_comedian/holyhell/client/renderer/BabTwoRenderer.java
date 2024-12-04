package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.BabOneModel;
import com.dead_comedian.holyhell.client.models.entity.BabTwoModel;
import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import com.dead_comedian.holyhell.entity.custom.BabTwoEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BabTwoRenderer extends MobEntityRenderer<BabTwoEntity, BabTwoModel<BabTwoEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/bab2.png");

    public BabTwoRenderer(EntityRendererFactory.Context context) {
        super(context, new BabTwoModel<>(context.getPart(HolyHellModelLayers.BAB1)), 0.6F);


    }

    @Override
    public Identifier getTexture(BabTwoEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabTwoEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
