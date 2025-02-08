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


public class StoneCrossBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty PIECE = IntegerProperty.create("piece", 0, 5);

    protected static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);


    public StoneCrossBlock(Properties settings) {
        super(settings);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(PIECE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

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
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PIECE);
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (state.getValue(FACING) == Direction.NORTH) {
            world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
            world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

            world.setBlock(pos.offset(1, 0, 0), state.setValue(PIECE, 3), 3);
            world.setBlock(pos.above().offset(1, 0, 0), state.setValue(PIECE, 4), 3);
            world.setBlock(pos.above().above().offset(1, 0, 0), state.setValue(PIECE, 5), 3);
        } else if (state.getValue(FACING) == Direction.SOUTH) {
            world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
            world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

            world.setBlock(pos.offset(-1, 0, 0), state.setValue(PIECE, 3), 3);
            world.setBlock(pos.above().offset(-1, 0, 0), state.setValue(PIECE, 4), 3);
            world.setBlock(pos.above().above().offset(-1, 0, 0), state.setValue(PIECE, 5), 3);
        } else if (state.getValue(FACING) == Direction.WEST) {
            world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
            world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

            world.setBlock(pos.offset(0, 0, -1), state.setValue(PIECE, 3), 3);
            world.setBlock(pos.above().offset(0, 0, -1), state.setValue(PIECE, 4), 3);
            world.setBlock(pos.above().above().offset(0, 0, -1), state.setValue(PIECE, 5), 3);
        } else {
            world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
            world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

            world.setBlock(pos.offset(0, 0, 1), state.setValue(PIECE, 3), 3);
            world.setBlock(pos.above().offset(0, 0, 1), state.setValue(PIECE, 4), 3);
            world.setBlock(pos.above().above().offset(0, 0, 1), state.setValue(PIECE, 5), 3);
        }

    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {

            if (state.getValue(PIECE) == 1) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.below(), false);

                world.removeBlock(pos.above().offset(1, 0, 0), false);
                world.removeBlock(pos.offset(1, 0, 0), false);
                world.removeBlock(pos.below().offset(1, 0, 0), false);

            } else if (state.getValue(PIECE) == 2) {
                world.removeBlock(pos.below(), false);
                world.removeBlock(pos.below().below(), false);

                world.removeBlock(pos.below().below().offset(1, 0, 0), false);
                world.removeBlock(pos.offset(1, 0, 0), false);
                world.removeBlock(pos.below().offset(1, 0, 0), false);

            } else if (state.getValue(PIECE) == 0) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.above().above(), false);
                world.removeBlock(pos.offset(1, 0, 0), false);
                world.removeBlock(pos.above().above().offset(1, 0, 0), false);
                world.removeBlock(pos.above().offset(1, 0, 0), false);
            } else if (state.getValue(PIECE) == 3) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.above().above(), false);

                world.removeBlock(pos.offset(-1, 0, 0), false);
                world.removeBlock(pos.above().above().offset(-1, 0, 0), false);
                world.removeBlock(pos.above().offset(-1, 0, 0), false);
            } else if (state.getValue(PIECE) == 4) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.below(), false);

                world.removeBlock(pos.above().offset(-1, 0, 0), false);
                world.removeBlock(pos.offset(-1, 0, 0), false);
                world.removeBlock(pos.below().offset(-1, 0, 0), false);

            } else if (state.getValue(PIECE) == 5) {
                world.removeBlock(pos.below(), false);
                world.removeBlock(pos.below().below(), false);

                world.removeBlock(pos.below().below().offset(-1, 0, 0), false);
                world.removeBlock(pos.offset(-1, 0, 0), false);
                world.removeBlock(pos.below().offset(-1, 0, 0), false);
            }
        } else {

            if (state.getValue(PIECE) == 1) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.below(), false);

                world.removeBlock(pos.above().offset(0, 0, 1), false);
                world.removeBlock(pos.offset(0, 0, 1), false);
                world.removeBlock(pos.below().offset(0, 0, 1), false);

            } else if (state.getValue(PIECE) == 2) {
                world.removeBlock(pos.below(), false);
                world.removeBlock(pos.below().below(), false);

                world.removeBlock(pos.below().below().offset(0, 0, 1), false);
                world.removeBlock(pos.offset(0, 0, 1), false);
                world.removeBlock(pos.below().offset(0, 0, 1), false);

            } else if (state.getValue(PIECE) == 0) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.above().above(), false);
                world.removeBlock(pos.offset(0, 0, 1), false);
                world.removeBlock(pos.above().above().offset(0, 0, 1), false);
                world.removeBlock(pos.above().offset(0, 0, 1), false);
            } else if (state.getValue(PIECE) == 3) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.above().above(), false);

                world.removeBlock(pos.offset(0, 0, -1), false);
                world.removeBlock(pos.above().above().offset(0, 0, -1), false);
                world.removeBlock(pos.above().offset(0, 0, -1), false);
            } else if (state.getValue(PIECE) == 4) {
                world.removeBlock(pos.above(), false);
                world.removeBlock(pos.below(), false);

                world.removeBlock(pos.above().offset(0, 0, -1), false);
                world.removeBlock(pos.offset(-0, 0, -1), false);
                world.removeBlock(pos.below().offset(0, 0, -1), false);

            } else if (state.getValue(PIECE) == 5) {
                world.removeBlock(pos.below(), false);
                world.removeBlock(pos.below().below(), false);

                world.removeBlock(pos.below().below().offset(0, 0, -1), false);
                world.removeBlock(pos.offset(0, 0, -1), false);
                world.removeBlock(pos.below().offset(0, 0, -1), false);

            }
        }

        super.playerWillDestroy(world, pos, state, player);
    }
}
