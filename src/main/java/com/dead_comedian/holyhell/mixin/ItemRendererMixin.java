package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.Holyhell;

import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "render", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useMoltenShieldModel(BakedModel value, ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if (stack.is(HolyHellItems.HOLY_GRAIL) && renderMode != ItemDisplayContext.GROUND && renderMode != ItemDisplayContext.GUI && renderMode != ItemDisplayContext.FIXED) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelResourceLocation(Holyhell.MOD_ID, "holy_grail_3d", "inventory"));
        }

        return value;
    }
}