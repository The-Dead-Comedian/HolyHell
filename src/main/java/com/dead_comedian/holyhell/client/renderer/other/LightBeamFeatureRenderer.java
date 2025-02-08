package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.LightBeamModel;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(value = EnvType.CLIENT)
public class LightBeamFeatureRenderer<T extends LightBeamEntity> extends EyesLayer<T, LightBeamModel<T>> {

    private static final RenderType SKIN = RenderType.eyes(ResourceLocation.tryBuild(Holyhell.MOD_ID, "textures/entity/lightbeam/light_beam1.png"));

    public LightBeamFeatureRenderer(RenderLayerParent<T, LightBeamModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {

        return SKIN;
    }
}