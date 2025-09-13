package com.dead_comedian.holyhell.mixin;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelShaper.class)
public abstract class BlockModelShaperMixin {

    @Inject(method = "getTexture",at=@At(value = "RETURN",shift = At.Shift.BEFORE))
    private void a(BlockState blockState, Level level, BlockPos pos, CallbackInfoReturnable<TextureAtlasSprite> cir){

        if(blockState == Blocks.BIRCH_LOG.defaultBlockState()){
//            blockState
        }
    }
}
