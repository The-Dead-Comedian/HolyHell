package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.item.ModItems;
import com.dead_comedian.holyhell.mixin.ItemRendererAccessor;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useMoltenShieldModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.HOLY_GRAIL) && renderMode != ModelTransformationMode.GROUND && renderMode != ModelTransformationMode.GUI && renderMode != ModelTransformationMode.FIXED) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Holyhell.MOD_ID, "holy_grail_3d", "inventory"));
        }
        return value;
    }
}