package com.dead_comedian.holyhell.server.item.custom.block_item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class StoneCrossItem extends BlockItem {
    public StoneCrossItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public InteractionResult place(BlockPlaceContext itemPlaceContext) {
        Level world = itemPlaceContext.getLevel();
        BlockPos pos = itemPlaceContext.getClickedPos();
        Player player = itemPlaceContext.getPlayer();

        if (player == null) {
            return super.place(itemPlaceContext);
        }

        BlockPos endPos;

            // Dynamic placement based on player direction
            Direction direction = player.getDirection();
            endPos = switch (direction) {
                case NORTH -> pos.offset(1, 2, 0);
                case SOUTH -> pos.offset(-1, 2, 0);
                case EAST -> pos.offset(0, 2, 1);
                case WEST -> pos.offset(0, 2, -1);
                case UP, DOWN -> null;
            };
        

        // Check if all required spaces are clear
        if (!isSpaceClear(world, pos, endPos)) {
            return InteractionResult.FAIL;
        }

        return super.place(itemPlaceContext);
    }

    private boolean isSpaceClear(Level world, BlockPos startPos, BlockPos endPos) {
        for (BlockPos pos : BlockPos.betweenClosed(startPos, endPos)) {
            if (world.getBlockState(pos).getBlock() != Blocks.AIR) {
                return false;  // Found a non-air block
            }
        }
        return true;  // All blocks were air
    }
}
