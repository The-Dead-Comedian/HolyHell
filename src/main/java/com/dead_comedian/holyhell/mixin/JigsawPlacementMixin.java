package com.dead_comedian.holyhell.mixin;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import com.dead_comedian.holyhell.server.registries.HolyhellDataComps;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(JigsawPlacement.class)
public abstract class JigsawPlacementMixin {


    //Holy Shield Sound
    @WrapOperation(
            method = "addPieces",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Rotation;getRandom(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/Rotation;")
    )
    private static Rotation holyhell$CovenantNotRotate(RandomSource random, Operation<Rotation> original, @Local(argsOnly = true) Holder<StructureTemplatePool> startPool) {

        boolean ours = startPool.unwrapKey().map(key -> key.location().equals(
                        ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "covenant/start_pool")))
                .orElse(false);

        return ours ? Rotation.CLOCKWISE_90 : original.call(random);
    }
}