package com.dead_comedian.holyhell.client.renderer.render_layer;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.List;
import java.util.Map;

public class UpperRingRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID,"textures/entity/religious_rings.png");
    private final ModelPart bb_main;

    public UpperRingRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context, EntityModelSet loader) {
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
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {
        if (abstractClientPlayer.hasEffect(HolyHellEffects.JESISTANCE.get())) {
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));

            for(int m = 0; m < 1; ++m) {
                poseStack.pushPose();
                float n = v3 * (float) (-(10 + m));
                poseStack.mulPose(Axis.YP.rotationDegrees(n));

                this.bb_main.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

                poseStack.popPose();
            }

        }
    }
}
