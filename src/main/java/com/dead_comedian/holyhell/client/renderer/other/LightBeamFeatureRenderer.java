package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.LightBeamModel;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class LightBeamFeatureRenderer<T extends LightBeamEntity> extends EyesFeatureRenderer<T, LightBeamModel<T>> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam1.png"));

    public LightBeamFeatureRenderer(FeatureRendererContext<T, LightBeamModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {

        return SKIN;
    }
}