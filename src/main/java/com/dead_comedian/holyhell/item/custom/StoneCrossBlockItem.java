package com.dead_comedian.holyhell.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;


public class StoneCrossBlockItem extends BlockItem {

    public StoneCrossBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ActionResult place(ItemPlacementContext itemPlaceContext) {
        World world = itemPlaceContext.getWorld();
        BlockPos pos = itemPlaceContext.getBlockPos();
        PlayerEntity player = itemPlaceContext.getPlayer();
        if (player != null) {
            if (!player.isSneaking()) {
                if (player.getHorizontalFacing() == Direction.NORTH) {
                    for (BlockPos forBlockPos : BlockPos.iterate(pos.add(0, 0, 0), pos.add(1, 2, 0))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return ActionResult.FAIL;
                        }
                    }
                }
                if (player.getHorizontalFacing() == Direction.SOUTH) {
                    for (BlockPos forBlockPos : BlockPos.iterate(pos.add(0, 0, 0), pos.add(-1, 2, 0))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return ActionResult.FAIL;
                        }
                    }
                }
                if (player.getHorizontalFacing() == Direction.EAST) {
                    for (BlockPos forBlockPos : BlockPos.iterate(pos.add(0, 0, 0), pos.add(0, 2, -1))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return ActionResult.FAIL;
                        }
                    }
                }
                if (player.getHorizontalFacing() == Direction.WEST) {
                    for (BlockPos forBlockPos : BlockPos.iterate(pos.add(0, 0, 0), pos.add(0, 2, 1))) {
                        if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                            return ActionResult.FAIL;
                        }
                    }
                }
            } else {
                //FIXES SET BLOCK PLACEMENT
                for (BlockPos forBlockPos : BlockPos.iterate(pos.add(0, 0, 0), pos.add(1, 2, 0))) {
                    if (world.getBlockState(forBlockPos).getBlock() != Blocks.AIR) {
                        return ActionResult.FAIL;
                    }
                }
            }
            return super.place(itemPlaceContext);
        }
        return super.place(itemPlaceContext);
    }
}

