package com.dead_comedian.holyhell.client.renderer.feature;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;

import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class ReligiousRingsLowerFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {
    public static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID,"textures/entity/religious_rings.png");
    private final ModelPart bb_main1;

    public ReligiousRingsLowerFeatureRenderer(FeatureRendererContext<T, PlayerEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        ModelPart modelPart = loader.getModelPart(HolyHellModelLayers.RELIGIOUS_RINGS);
        this.bb_main1 = modelPart.getChild("bb_main1");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main1", ModelPartBuilder.create().uv(0, 0).cuboid(    -11.0F, -17.0F, -11.0F, 22.0F, 7.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    protected Identifier getTexture(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity.hasStatusEffect(HolyHellEffects.JESISTANCE)) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));

            for(int m = 0; m < 1; ++m) {
                matrixStack.push();
                float n = j * (float) (-(10 + m));
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-n));

                this.bb_main1.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);

                matrixStack.pop();
            }

        }
    }
}
