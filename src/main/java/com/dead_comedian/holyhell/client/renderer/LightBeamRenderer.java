package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.*;
import com.dead_comedian.holyhell.client.renderer.feature.LightBeamFogFeatureRenderer;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.CreeperChargeFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class LightBeamRenderer extends EntityRenderer<LightBeamEntity>  {
    int timer = 1;
    private static final Identifier TEXTURE1 = new Identifier(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam1.png");
    private static final Identifier TEXTURE2 = new Identifier(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam2.png");
    private static final Identifier TEXTURE3 = new Identifier(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam3.png");
    private static final Identifier TEXTURE4 = new Identifier(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam4.png");
    private static final Identifier TEXTURE5 = new Identifier(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam5.png");
    private final LightBeamModel<LightBeamEntity> model;

    protected final List<FeatureRenderer<LightBeamEntity, LightBeamModel<LightBeamEntity>>> features = Lists.newArrayList();

    public LightBeamRenderer(EntityRendererFactory.Context context) {
        super(context);

        this.model = new LightBeamModel<>(context.getPart(HolyHellModelLayers.LIGHT_BEAM));



    }

    public final boolean addFeature(FeatureRenderer<LightBeamEntity, LightBeamModel<LightBeamEntity>> feature) {
        return this.features.add(feature);
    }

    @Override
    public void render(LightBeamEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = null;
        timer++;

        

        if (timer <= 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE1));
            vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(TEXTURE1, f*0.1F % 1.0f, f * 0.01f % 1.0f));

        } else if (timer <= 40 && timer > 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE2));
            vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(TEXTURE2, f*0.1F % 1.0f, f * 0.01f % 1.0f));
        } else if (timer <= 60 && timer > 40) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE3));
            vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(TEXTURE3, f*0.1F % 1.0f, f * 0.01f % 1.0f));
        } else if (timer <= 80 && timer > 60) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE4));
            vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(TEXTURE4, f*0.1F % 1.0f, f * 0.01f % 1.0f));
        } else if (timer <= 100 && timer > 80) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE5));
            vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(TEXTURE5, f*0.1F % 1.0f, f * 0.01f % 1.0f));
            timer = 1;
        }


        if (mobEntity.getLevel() == 0) {

            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 1) {

            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 2) {

            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        } else if (mobEntity.getLevel() == 3) {
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 4) {
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);

        } else if (mobEntity.getLevel() == 5) {
            this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.3F);
        }


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }




    @Override
    public Identifier getTexture(LightBeamEntity entity) {

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