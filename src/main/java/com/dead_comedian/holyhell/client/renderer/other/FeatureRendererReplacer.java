package com.dead_comedian.holyhell.client.renderer.other;

import com.dead_comedian.holyhell.client.models.entity.LightBeamModel;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class FeatureRendererReplacer extends EntityRenderer<LightBeamEntity> implements FeatureRendererContext<LightBeamEntity, LightBeamModel<LightBeamEntity>> {
    protected FeatureRendererReplacer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public LightBeamModel<LightBeamEntity> getModel() {
        return null;
    }

    @Override
    public Identifier getTexture(LightBeamEntity entity) {
        return null;
    }
}
