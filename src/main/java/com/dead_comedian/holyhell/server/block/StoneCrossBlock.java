package com.dead_comedian.holyhell.server.block;


import com.mojang.serialization.MapCodec;
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
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pPos, CollisionContext pContext) {


        switch (state.getValue(FACING)) {
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
    public void setPlacedBy(Level level, BlockPos pPos, BlockState state, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(level, pPos, state, pPlacer, pStack);
        Direction facing = state.getValue(FACING);
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
            level.setBlock(pPos.above(y), state.setValue(PIECE, y), 3);
        }

        // Place offset blocks (pieces 3, 4, and 5)
        BlockPos offsetPos = pPos.offset(xOffset, 0, zOffset);
        for (int y = 0; y <= 2; y++) {
            level.setBlock(offsetPos.above(y), state.setValue(PIECE, y + 3), 3);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        Direction facing = state.getValue(FACING);
        int piece = state.getValue(PIECE);
        Offset offset = switch (facing) {
            case NORTH -> new Offset(piece >= 3 ? -1 : 1, 0);
            case SOUTH -> new Offset(piece >= 3 ? 1 : -1, 0);
            case EAST -> new Offset(0, piece >= 3 ? -1 : 1);
            case WEST -> new Offset(0, piece >= 3 ? 1 : -1);
            default -> new Offset(0, 0);
        };
        BlockRemovalPattern pattern = switch (piece) {
            case 0, 3 -> new BlockRemovalPattern(
                    new int[]{1, 2},
                    new int[]{0, 1, 2}
            );
            case 1, 4 -> new BlockRemovalPattern(
                    new int[]{-1, 0, 1},
                    new int[]{-1, 0, 1}
            );
            case 2, 5 -> new BlockRemovalPattern(
                    new int[]{-2, -1, 0},
                    new int[]{-2, -1, 0}
            );
            default -> null;
        };

        if (pattern != null) {
            for (int yOffset : pattern.firstColumn()) {
                removeBlockWithDirectionalPattern(level, pos, 0, yOffset, 0, facing);
            }

            for (int yOffset : pattern.secondColumn()) {
                removeBlockWithDirectionalPattern(level, pos, offset.x(), yOffset, offset.z(), facing);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    private void removeBlockWithDirectionalPattern(Level level, BlockPos pPos, int xOffset, int yOffset, int zOffset, Direction facing) {
        BlockPos targetPos = switch (facing) {
            case NORTH -> pPos.offset(xOffset, yOffset, zOffset);
            case SOUTH -> pPos.offset(xOffset, yOffset, -zOffset);  // Flip Z
            case EAST -> pPos.offset(xOffset, yOffset, zOffset);    // Swap X and Z
            case WEST -> pPos.offset(xOffset, yOffset, zOffset);  // Swap and negate X and Z
            default -> pPos;
        };

        level.removeBlock(targetPos, false);
    }

    private record BlockRemovalPattern(int[] firstColumn, int[] secondColumn) {
    }

    private record Offset(int x, int z) {
    }
}
