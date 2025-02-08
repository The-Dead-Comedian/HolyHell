package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;

import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

public class AtheistAmazementFeatureRenderer<T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>> {
    int timer = 1;
    public static final ResourceLocation TEXTURE1 = new ResourceLocation(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement1.png");
    public static final ResourceLocation TEXTURE2 = new ResourceLocation(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement2.png");
    public static final ResourceLocation TEXTURE3 = new ResourceLocation(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement3.png");
    public static final ResourceLocation TEXTURE4 = new ResourceLocation(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement4.png");

    private final ModelPart bone;

    public AtheistAmazementFeatureRenderer(RenderLayerParent<T, PlayerModel<T>> context, EntityModelSet loader) {
        super(context);
        ModelPart modelPart = loader.bakeLayer(HolyHellModelLayers.ATHEIST_AMAZEMENT);
        this.bone = modelPart.getChild("bone");

    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bone = modelPartData.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.025F, -13.0F, 27.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-5.0F, -32.0F, -5.0F, 12.0F, 32.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 23.975F, -1.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    protected ResourceLocation getTextureLocation(T entity) {
        timer++;
        if (timer <= 20) {
            return TEXTURE1;
        } else if (timer <= 40 && timer > 20) {
            return TEXTURE2;
        } else if (timer <= 60 && timer > 40) {
            return TEXTURE3;
        } else if (timer <= 80 && timer > 60) {
            timer = 1;
            return TEXTURE4;
        }
        return TEXTURE1;

    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (HailingHereticEntity.HereticAttackGoal.shouldRender()) {


            VertexConsumer vertexConsumer = null;
            timer++;
            if (timer <= 20) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE1));

            } else if (timer <= 40 && timer > 20) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE2));
            } else if (timer <= 60 && timer > 40) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE3));
            } else if (timer <= 80 && timer > 60) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(TEXTURE4));
                timer = 1;
            }
                matrixStack.pushPose();



                this.bone.render(matrixStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY);

                matrixStack.popPose();
            }

        }
    }

