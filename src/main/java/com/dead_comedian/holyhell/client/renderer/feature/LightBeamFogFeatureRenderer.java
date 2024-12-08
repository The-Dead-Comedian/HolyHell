package com.dead_comedian.holyhell.client.renderer.feature;

import com.dead_comedian.holyhell.client.models.entity.LightBeamFogModel;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;

import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;

public class LightBeamFogFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<LightBeamEntity, LightBeamFogModel<LightBeamEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/lightbeam/fog1.png");
    private final LightBeamFogModel<LightBeamEntity> model;

    public LightBeamFogFeatureRenderer(FeatureRendererContext<LightBeamEntity, LightBeamFogModel<LightBeamEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new LightBeamFogModel(loader.getModelPart(HolyHellModelLayers.FOG1));
    }

    @Override
    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01f;
    }

    @Override
    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    @Override
    protected EntityModel<LightBeamEntity> getEnergySwirlModel() {
        return this.model;
    }
}
