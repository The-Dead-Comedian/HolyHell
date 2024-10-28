package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;

import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
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

public class AtheistAmazementFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {
    int timer = 1;
    public static final Identifier TEXTURE1 = new Identifier(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement1.png");
    public static final Identifier TEXTURE2 = new Identifier(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement2.png");
    public static final Identifier TEXTURE3 = new Identifier(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement3.png");
    public static final Identifier TEXTURE4 = new Identifier(Holyhell.MOD_ID,"textures/entity/atheist_amazement/atheist_amazement4.png");

    private final ModelPart bone;

    public AtheistAmazementFeatureRenderer(FeatureRendererContext<T, PlayerEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        ModelPart modelPart = loader.getModelPart(HolyHellModelLayers.ATHEIST_AMAZEMENT);
        this.bone = modelPart.getChild("bone");

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, 0.025F, -13.0F, 27.0F, 0.0F, 28.0F, new Dilation(0.0F))
                .uv(0, 28).cuboid(-5.0F, -32.0F, -5.0F, 12.0F, 32.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 23.975F, -1.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    protected Identifier getTexture(T entity) {
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
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (HailingHereticEntity.HereticAttackGoal.shouldRender()) {


            VertexConsumer vertexConsumer = null;
            timer++;
            if (timer <= 20) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE1));

            } else if (timer <= 40 && timer > 20) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE2));
            } else if (timer <= 60 && timer > 40) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE3));
            } else if (timer <= 80 && timer > 60) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE4));
                timer = 1;
            }
                matrixStack.push();



                this.bone.render(matrixStack, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV);

                matrixStack.pop();
            }

        }
    }

