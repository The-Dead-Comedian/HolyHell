package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.HailingHereticModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HailingHereticRenderer extends MobEntityRenderer<HailingHereticEntity, HailingHereticModel<HailingHereticEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/hailing_heretic.png");

    public HailingHereticRenderer(EntityRendererFactory.Context context) {
        super(context, new HailingHereticModel<>(context.getPart(HolyHellModelLayers.HAILING_HERETIC)), 0.6f);
    }

    @Override
    public Identifier getTexture(HailingHereticEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(HailingHereticEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
