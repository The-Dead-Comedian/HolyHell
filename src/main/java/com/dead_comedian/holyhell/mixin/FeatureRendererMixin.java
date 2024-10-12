package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.other.AtheistAmazementFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;


import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntityRenderer.class)
public abstract class FeatureRendererMixin {

            @Inject(method = "<init>", at = @At(value = "RETURN"))
        private void addFeature(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {

                ((PlayerEntityRenderer) (Object) this).addFeature(new ReligiousRingsUpperFeatureRenderer<>(
                        ((FeatureRendererContext) (Object) this), ctx.getModelLoader()));
                ((PlayerEntityRenderer) (Object) this).addFeature(new ReligiousRingsLowerFeatureRenderer<>(
                        ((FeatureRendererContext) (Object) this), ctx.getModelLoader()));

                    ((PlayerEntityRenderer) (Object) this).addFeature(new AtheistAmazementFeatureRenderer<>(
                           ((FeatureRendererContext) (Object) this), ctx.getModelLoader()));
        }
    }

