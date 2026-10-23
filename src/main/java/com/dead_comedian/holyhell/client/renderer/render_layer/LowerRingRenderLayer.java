package com.dead_comedian.holyhell.client.renderer.render_layer;


import com.dead_comedian.holyhell.ClientConfig;
import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class LowerRingRenderLayer<T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/religious_rings.png");
    private final ModelPart bb_main;
    private final ModelPart bb_main1;
    private int counter = 0;

    public LowerRingRenderLayer(RenderLayerParent<T, PlayerModel<T>> context, EntityModelSet loader) {
        super(context);
        ModelPart modelPart = loader.bakeLayer(HolyHellModelLayers.RELIGIOUS_RINGS);
        this.bb_main1 = modelPart.getChild("bb_main1");
        this.bb_main = modelPart.getChild("bb_main");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -28.0F, -11.0F, 22.0F, 7.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 28.0F, 0.0F));
        PartDefinition bb_main1 = modelPartData.addOrReplaceChild("bb_main1", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -17.0F, -11.0F, 22.0F, 7.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    protected ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));


        if (livingEntity.getData(HolyHellAttachments.RENDER_RINGS)) {

            if (ClientConfig.RING_SIZE != null) {
                poseStack.pushPose();
                poseStack.scale(ClientConfig.RING_SIZE.get().floatValue(), ClientConfig.RING_SIZE.get().floatValue(), ClientConfig.RING_SIZE.get().floatValue());
                poseStack.popPose();
            }

            poseStack.pushPose();
            float n = j * (float) (-11);
            poseStack.mulPose(Axis.YP.rotationDegrees(-n));
            this.bb_main1.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(n));
            this.bb_main.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();

        }
        else if (livingEntity.getData(HolyHellAttachments.RENDER_RINGS_BREAK)) {
            counter++;
            if (counter <= 80) {
                if (ClientConfig.RING_SIZE != null) {
                    poseStack.pushPose();
                    poseStack.scale(ClientConfig.RING_SIZE.get().floatValue(), ClientConfig.RING_SIZE.get().floatValue(), ClientConfig.RING_SIZE.get().floatValue());
                    poseStack.popPose();
                }
                float size = 1 + (float) counter / 10;

                poseStack.pushPose();

                poseStack.scale(size, 1, size);
                poseStack.translate(0, -0.6, 0);
                float n = j * (float) (-11);
                poseStack.mulPose(Axis.YP.rotationDegrees(-n));
                this.bb_main1.render(poseStack, vertexConsumer, -1572864, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();

                poseStack.pushPose();
                poseStack.scale(size, 1, size);
                poseStack.translate(0, -0.4, 0);
                poseStack.mulPose(Axis.YP.rotationDegrees(n));
                this.bb_main.render(poseStack, vertexConsumer, -1572864, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();

            } else {
                counter = 0;
            }
        }
    }
}