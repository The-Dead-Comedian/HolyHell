package com.dead_comedian.holyhell.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class StoneCrossBlockItem extends BlockItem {

    public StoneCrossBlockItem(Block block, Properties settings) {
        super(block, settings);
    }

    public InteractionResult place(BlockPlaceContext itemPlaceContext) {
        Level world = itemPlaceContext.getLevel();
        BlockPos pos = itemPlaceContext.getClickedPos();
        Player player = itemPlaceContext.getPlayer();
        if (player != null) {
            if (!player.isShiftKeyDown()) {
                if (player.getDirection() == Direction.NORTH) {
                    for (BlockPos forBlockPos : BlockPos.betweenClosed(pos.offset(0, 0, 0), pos.offset(1, 2, 0))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return InteractionResult.FAIL;
                        }
                    }
                }
                if (player.getDirection() == Direction.SOUTH) {
                    for (BlockPos forBlockPos : BlockPos.betweenClosed(pos.offset(0, 0, 0), pos.offset(-1, 2, 0))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return InteractionResult.FAIL;
                        }
                    }
                }
                if (player.getDirection() == Direction.EAST) {
                    for (BlockPos forBlockPos : BlockPos.betweenClosed(pos.offset(0, 0, 0), pos.offset(0, 2, -1))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return InteractionResult.FAIL;
                        }
                    }
                }
                if (player.getDirection() == Direction.WEST) {
                    for (BlockPos forBlockPos : BlockPos.betweenClosed(pos.offset(0, 0, 0), pos.offset(0, 2, 1))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return InteractionResult.FAIL;
                        }
                    }
                }
            } else {
                //FIXES SET BLOCK PLACEMENT
                for (BlockPos forBlockPos : BlockPos.betweenClosed(pos.offset(0, 0, 0), pos.offset(1, 2, 0))) {
                    if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                        return InteractionResult.FAIL;
                    }
                }
            }
            return super.place(itemPlaceContext);
        }
        return super.place(itemPlaceContext);
    }
}

