package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerRenderer.class)
public abstract class FeatureRendererMixin {

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void addFeature(EntityRendererProvider.Context ctx, boolean slim, CallbackInfo ci) {

        ((PlayerRenderer) (Object) this).addLayer(new ReligiousRingsUpperFeatureRenderer<>(
                ((RenderLayerParent) (Object) this), ctx.getModelSet()));
        ((PlayerRenderer) (Object) this).addLayer(new ReligiousRingsLowerFeatureRenderer<>(
                ((RenderLayerParent) (Object) this), ctx.getModelSet()));

//        ((PlayerRenderer) (Object) this).addLayer(new AtheistAmazementFeatureRenderer<>(
//                ((RenderLayerParent) (Object) this), ctx.getModelSet()));
    }
}