package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.BabOneModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.BabEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BabRenderer extends MobEntityRenderer<BabEntity, BabOneModel<BabEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/bab1.png");

    public BabRenderer(EntityRendererFactory.Context context) {
        super(context, new BabOneModel<>(context.getPart(HolyHellModelLayers.BAB)), 0.6f);
    }

    @Override
    public Identifier getTexture(BabEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
