package com.dead_comedian.holyhell.client.renderer.feature;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class ReligiousRingsUpperFeatureRenderer<T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Holyhell.MOD_ID,"textures/entity/religious_rings.png");
    private final ModelPart bb_main;

    public ReligiousRingsUpperFeatureRenderer(RenderLayerParent<T, PlayerModel<T>> context, EntityModelSet loader) {
        super(context);
        ModelPart modelPart = loader.bakeLayer(HolyHellModelLayers.RELIGIOUS_RINGSV);
        this.bb_main = modelPart.getChild("bb_main");

    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -28.0F, -11.0F, 22.0F, 7.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 28.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    protected ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity.hasEffect(HolyHellEffects.JESISTANCE)) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));

            for(int m = 0; m < 1; ++m) {
                matrixStack.pushPose();
                float n = j * (float) (-(10 + m));
                matrixStack.mulPose(Axis.YP.rotationDegrees(n));

                this.bb_main.render(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

                matrixStack.popPose();
            }

        }
    }
}
