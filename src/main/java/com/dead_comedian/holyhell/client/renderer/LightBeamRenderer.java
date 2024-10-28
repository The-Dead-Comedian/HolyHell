package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.*;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class LightBeamRenderer extends EntityRenderer<LightBeamEntity> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/light_beam.png");
    private final LightBeamModel<LightBeamEntity> model;
    private final LightBeamModel1<LightBeamEntity> model1;
    private final LightBeamModel2<LightBeamEntity> model2;
    private final LightBeamModel3<LightBeamEntity> model3;
    private final LightBeamModel4<LightBeamEntity> model4;
    private final LightBeamModel5<LightBeamEntity> model5;


    public LightBeamRenderer(EntityRendererFactory.Context context) {
        super(context);

        this.model = new LightBeamModel<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM));
        this.model1 = new LightBeamModel1<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM1));
        this.model2 = new LightBeamModel2<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM2));
        this.model3 = new LightBeamModel3<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM3));
        this.model4 = new LightBeamModel4<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM4));
        this.model5 = new LightBeamModel5<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM5));

    }


    @Override
    public void render(LightBeamEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        if (mobEntity.getLevel() == 0) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 1) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model1.getLayer(TEXTURE));
            this.model1.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 2) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model2.getLayer(TEXTURE));
            this.model2.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 3) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model3.getLayer(TEXTURE));
            this.model3.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 4) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model4.getLayer(TEXTURE));
            this.model4.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 5) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model5.getLayer(TEXTURE));
            this.model5.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        }



        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LightBeamEntity entity) {
        return TEXTURE;
    }

}