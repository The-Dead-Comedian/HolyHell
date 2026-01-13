package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderDispatcher.class)
public abstract class BlockEntityRendererDispatcherMixin implements ResourceManagerReloadListener {
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private <E extends BlockEntity> void rendererOverride(E blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getData(HolyHellAttachments.VISION_SHADER)) {
            ci.cancel();
        }
    }
}
