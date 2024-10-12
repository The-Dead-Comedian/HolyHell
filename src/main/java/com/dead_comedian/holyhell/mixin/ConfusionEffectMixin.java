package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.other.AtheistAmazementFeatureRenderer;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(LivingEntity.class)
public abstract class ConfusionEffectMixin {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(method = "tick", at = @At(value = "RETURN"))
        private void addFeature(CallbackInfo ci) {

      //  if(((LivingEntity) entity).hasStatusEffect(HolyHellEffects.CONFUSION)){

        //}
    }
    }

