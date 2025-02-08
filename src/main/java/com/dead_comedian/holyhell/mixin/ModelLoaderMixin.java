package com.dead_comedian.holyhell.mixin;
import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelBakery.class)
public abstract class ModelLoaderMixin {

    @Shadow protected abstract void loadTopLevel(ModelResourceLocation modelResourceLocation);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target =  "Lnet/minecraft/client/resources/model/ModelBakery;loadTopLevel(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addMoltenShield(BlockColors blockColors, ProfilerFiller profiler, Map<ResourceLocation, BlockModel> jsonUnbakedModels, Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStates, CallbackInfo ci) {
        this.loadTopLevel(new ModelResourceLocation(Holyhell.MOD_ID, "holy_grail_3d", "inventory"));
    }
}