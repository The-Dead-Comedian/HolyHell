package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.other.BlindingBombModel;
import com.dead_comedian.holyhell.entity.custom.other.BlindingBombEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class BlindingBombRenderer extends EntityRenderer<BlindingBombEntity> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/blinding_bomb.png");
    private final BlindingBombModel<BlindingBombEntity> model;

    public BlindingBombRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BlindingBombModel<>(context.getPart(HolyHellModelLayers.BLINDING_BOMB));
    }





    @Override
    public void render(BlindingBombEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public Identifier getTexture(BlindingBombEntity entity) {
        return TEXTURE;
    }

}
