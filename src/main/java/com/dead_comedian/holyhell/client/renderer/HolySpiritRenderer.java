package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.HailingHereticModel;
import com.dead_comedian.holyhell.client.models.entity.HolySpiritModel;
import com.dead_comedian.holyhell.client.models.entity.LightBeamModel;
import com.dead_comedian.holyhell.client.models.entity.LightBeamModel1;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.HolySpiritEntity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HolySpiritRenderer extends MobEntityRenderer<HolySpiritEntity, HolySpiritModel<HolySpiritEntity>> {
    private static final Identifier NORMAL = new Identifier(Holyhell.MOD_ID, "textures/entity/holy_spirit.png");


    public HolySpiritRenderer(EntityRendererFactory.Context context) {
        super(context, new HolySpiritModel<>(context.getPart(HolyHellModelLayers.HOLY_SPIRIT)), 0.6f);

    }

    @Override
    public Identifier getTexture(HolySpiritEntity entity) {
        return NORMAL;

    }

    @Override
    public void render(HolySpiritEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
