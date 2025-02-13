package com.dead_comedian.holyhell.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;


public class StoneCrossBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty PIECE = IntegerProperty.create("piece", 0, 5);

    protected static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);


    public StoneCrossBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(PIECE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {


        switch (pState.getValue(FACING)) {
            default: {
                return SOUTH_SHAPE;

            }
            case SOUTH: {
                return NORTH_SHAPE;
            }
            case WEST: {
                return EAST_SHAPE;
            }
            case EAST:
        }
        return WEST_SHAPE;

    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
     return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PIECE, FACING);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        Direction facing = pState.getValue(FACING);
        int xOffset = 0;
        int zOffset = 0;

        // Determine offsets based on facing direction
        switch (facing) {
            case NORTH -> xOffset = 1;
            case SOUTH -> xOffset = -1;
            case WEST -> zOffset = -1;
            case EAST -> zOffset = 1;
        }

        // Place vertical blocks (pieces 1 and 2)
        for (int y = 1; y <= 2; y++) {
            pLevel.setBlock(pPos.above(y), pState.setValue(PIECE, y), 3);
        }

        // Place offset blocks (pieces 3, 4, and 5)
        BlockPos offsetPos = pPos.offset(xOffset, 0, zOffset);
        for (int y = 0; y <= 2; y++) {
            pLevel.setBlock(offsetPos.above(y), pState.setValue(PIECE, y + 3), 3);
        }
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);

        Direction facing = pState.getValue(FACING);
        int piece = pState.getValue(PIECE);

        // Get offset direction based on facing
        Offset offset = switch (facing) {
            case NORTH -> new Offset(piece >= 3 ? -1 : 1, 0);  // X offset
            case SOUTH -> new Offset(piece >= 3 ? 1 : -1, 0);  // Flipped X offset
            case EAST -> new Offset(0, piece >= 3 ? -1 : 1);   // Z offset
            case WEST -> new Offset(0, piece >= 3 ? 1 : -1);   // Flipped Z offset
            default -> new Offset(0, 0);
        };

        // Get removal pattern based on piece type
        BlockRemovalPattern pattern = switch (piece) {
            case 0, 3 -> new BlockRemovalPattern(
                    new int[]{ 1, 2 },      // vertical offsets for first column
                    new int[]{ 0, 1, 2 }    // vertical offsets for second column
            );
            case 1, 4 -> new BlockRemovalPattern(
                    new int[]{ -1, 0, 1 },  // vertical offsets for first column
                    new int[]{ -1, 0, 1 }   // vertical offsets for second column
            );
            case 2, 5 -> new BlockRemovalPattern(
                    new int[]{ -2, -1, 0 }, // vertical offsets for first column
                    new int[]{ -2, -1, 0 }  // vertical offsets for second column
            );
            default -> null;
        };

        if (pattern != null) {
            // Remove blocks in first column (center)
            for (int yOffset : pattern.firstColumn()) {
                removeBlockWithDirectionalPattern(pLevel, pPos, 0, yOffset, 0, facing);
            }

            // Remove blocks in second column (offset)
            for (int yOffset : pattern.secondColumn()) {
                removeBlockWithDirectionalPattern(pLevel, pPos, offset.x(), yOffset, offset.z(), facing);
            }
        }
    }

    private void removeBlockWithDirectionalPattern(Level pLevel, BlockPos pPos, int xOffset, int yOffset, int zOffset, Direction facing) {
        // Apply directional transformations
        BlockPos targetPos = switch (facing) {
            case NORTH -> pPos.offset(xOffset, yOffset, zOffset);
            case SOUTH -> pPos.offset(xOffset, yOffset, -zOffset);  // Flip Z
            case EAST -> pPos.offset(xOffset, yOffset, zOffset);    // Swap X and Z
            case WEST -> pPos.offset(xOffset, yOffset, zOffset);  // Swap and negate X and Z
            default -> pPos;
        };

        pLevel.removeBlock(targetPos, false);
    }

    private record BlockRemovalPattern(int[] firstColumn, int[] secondColumn) {}
    private record Offset(int x, int z) {}
}
