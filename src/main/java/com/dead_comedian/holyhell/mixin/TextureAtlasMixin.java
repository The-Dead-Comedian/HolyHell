package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextureAtlas.class)
public abstract class TextureAtlasMixin {

//    @ModifyReturnValue(method = "getSprite", at = @At(value = "HEAD"))
//    private TextureAtlasSprite modifyBirchTexture(TextureAtlasSprite original, ResourceLocation location) {
//
//        if (holyHell$isBirchTexture(location)) {
//            Minecraft mc = Minecraft.getInstance();
//            if (mc.player != null && mc.player.hasEffect(HolyHellEffects.ANGELIC_VISION)) {
//                ResourceLocation customTexture =  ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID,
//                        "textures/block/birch_eye");
//
//                TextureAtlas atlas = (TextureAtlas) (Object) this;
//                TextureAtlasSprite customSprite = atlas.getSprite(customTexture);
//
//                if (!customSprite.contents().name().getPath().equals("missingno")) {
//                    return customSprite;
//                }
//            }
//        }
//        return original;
//    }

    @Unique
    private boolean holyHell$isBirchTexture(ResourceLocation location) {
        String path = location.getPath();
        return path.contains("birch_log");
    }


}
