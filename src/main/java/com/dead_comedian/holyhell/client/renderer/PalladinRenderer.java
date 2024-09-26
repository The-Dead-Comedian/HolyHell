package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.PalladinModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.PalladinEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PalladinRenderer extends MobEntityRenderer<PalladinEntity, PalladinModel<PalladinEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/paladin.png");

    public PalladinRenderer(EntityRendererFactory.Context context) {
        super(context, new PalladinModel<>(context.getPart(HolyHellModelLayers.PALLADIN)), 0.6f);
    }

    @Override
    public Identifier getTexture(PalladinEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PalladinEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
