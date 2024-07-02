package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.effect.ModEffects;
import com.dead_comedian.holyhell.entity.ModModelLayers;
import com.dead_comedian.holyhell.entity.custom.LastPrayerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class ReligiousRingsFeatureRenderer <T extends LivingEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {
    public static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID,"textures/entity/religious_rings.png");
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    public ReligiousRingsFeatureRenderer(FeatureRendererContext<T, PlayerEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        ModelPart modelPart = loader.getModelPart(ModModelLayers.RELIGIOUS_RINGS);
        this.bone = modelPart.getChild("bone");
        this.bone2 = modelPart.getChild("bone2");
        this.bone3 = modelPart.getChild("bone3");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(40, 91).cuboid(-11.0F, -3.5F, -11.0F, 22.0F, 7.0F, 22.0F, new Dilation(1.0F)), ModelTransform.pivot(0.075F, 7.5F, 0.1F));

        ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(-0.05F, 7.45F, -0.1F));

        ModelPartData cube_r1 = bone2.addChild("cube_r1", ModelPartBuilder.create().uv(40, 91).cuboid(-11.0F, -3.5F, -11.0F, 22.0F, 7.0F, 22.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData bone3 = modelPartData.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 7.5F, 0.0F));

        ModelPartData cube_r2 = bone3.addChild("cube_r2", ModelPartBuilder.create().uv(40, 91).cuboid(-11.0F, -8.0F, -6.5F, 22.0F, 7.0F, 22.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, -4.5F, -1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    protected Identifier getTexture(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity.hasStatusEffect(ModEffects.CLARITY)) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));

            for(int m = 0; m < 1; ++m) {
                matrixStack.push();
                float n = j * (float)(-(10 + m ));
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(n));

                this.bone.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
                this.bone2.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
                this.bone3.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
                matrixStack.pop();
            }

        }
    }
}
