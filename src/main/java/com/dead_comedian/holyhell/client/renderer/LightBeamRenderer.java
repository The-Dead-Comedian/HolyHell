package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.*;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

@Environment(value = EnvType.CLIENT)
public class LightBeamRenderer extends EntityRenderer<LightBeamEntity> {
    int timer = 1;
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam4.png");
    private static final ResourceLocation TEXTURE5 = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam5.png");
    private final LightBeamModel<LightBeamEntity> model;
    private final LightBeamModel1<LightBeamEntity> model1;
    private final LightBeamModel2<LightBeamEntity> model2;
    private final LightBeamModel3<LightBeamEntity> model3;
    private final LightBeamModel4<LightBeamEntity> model4;
    private final LightBeamModel5<LightBeamEntity> model5;


    public LightBeamRenderer(EntityRendererProvider.Context context) {
        super(context);

        this.model = new LightBeamModel<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM));
        this.model1 = new LightBeamModel1<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM1));
        this.model2 = new LightBeamModel2<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM2));
        this.model3 = new LightBeamModel3<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM3));
        this.model4 = new LightBeamModel4<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM4));
        this.model5 = new LightBeamModel5<>(context.bakeLayer(HolyHellModelLayers.LIGHT_BEAM5));


    }


    @Override
    public void render(LightBeamEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        VertexConsumer vertexConsumer = null;
        timer++;
        if (timer <= 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE1));

        } else if (timer <= 40 && timer > 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE2));
        } else if (timer <= 60 && timer > 40) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE3));
        } else if (timer <= 80 && timer > 60) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE4));
        } else if (timer <= 100 && timer > 80) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE5));
            timer = 1;
        }


        if (mobEntity.getLevel() == 0) {

            this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 1) {

            this.model1.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 2) {

            this.model2.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 3) {

            this.model3.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 4) {

            this.model4.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 5) {
            this.model5.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.3F);
        }


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(LightBeamEntity entity) {

        timer++;
        if (timer <= 20) {
            return TEXTURE1;
        } else if (timer <= 40 && timer > 20) {
            return TEXTURE2;
        } else if (timer <= 60 && timer > 40) {
            return TEXTURE3;
        } else if (timer <= 80 && timer > 60) {
            return TEXTURE4;
        } else if (timer <= 100 && timer > 80) {
            timer = 1;
            return TEXTURE5;
        }
        return TEXTURE1;

    }
}