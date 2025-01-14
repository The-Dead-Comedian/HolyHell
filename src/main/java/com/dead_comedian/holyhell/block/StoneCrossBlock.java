package com.dead_comedian.holyhell.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;


public class StoneCrossBlock extends HorizontalFacingBlock {
    public static final IntProperty PIECE = IntProperty.of("piece", 0, 5);

    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);


    public StoneCrossBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) this.getDefaultState().with(PIECE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        switch (state.get(FACING)) {
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
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PIECE);
        builder.add(FACING);
        super.appendProperties(builder);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {
            world.setBlockState(pos.up(), state.with(PIECE, 1), 3);
            world.setBlockState(pos.up().up(), state.with(PIECE, 2), 3);

            world.setBlockState(pos.add(1, 0, 0), state.with(PIECE, 3), 3);
            world.setBlockState(pos.up().add(1, 0, 0), state.with(PIECE, 4), 3);
            world.setBlockState(pos.up().up().add(1, 0, 0), state.with(PIECE, 5), 3);
        } else {
            world.setBlockState(pos.up(), state.with(PIECE, 1), 3);
            world.setBlockState(pos.up().up(), state.with(PIECE, 2), 3);

            world.setBlockState(pos.add(0, 0, 1), state.with(PIECE, 3), 3);
            world.setBlockState(pos.up().add(0, 0, 1), state.with(PIECE, 4), 3);
            world.setBlockState(pos.up().up().add(0, 0, 1), state.with(PIECE, 5), 3);
        }

    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {

            if (state.get(PIECE) == 1) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.down(), false);

                world.removeBlock(pos.up().add(1, 0, 0), false);
                world.removeBlock(pos.add(1, 0, 0), false);
                world.removeBlock(pos.down().add(1, 0, 0), false);

            } else if (state.get(PIECE) == 2) {
                world.removeBlock(pos.down(), false);
                world.removeBlock(pos.down().down(), false);

                world.removeBlock(pos.down().down().add(1, 0, 0), false);
                world.removeBlock(pos.add(1, 0, 0), false);
                world.removeBlock(pos.down().add(1, 0, 0), false);

            } else if (state.get(PIECE) == 0) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.up().up(), false);
                world.removeBlock(pos.add(1, 0, 0), false);
                world.removeBlock(pos.up().up().add(1, 0, 0), false);
                world.removeBlock(pos.up().add(1, 0, 0), false);
            } else if (state.get(PIECE) == 3) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.up().up(), false);

                world.removeBlock(pos.add(-1, 0, 0), false);
                world.removeBlock(pos.up().up().add(-1, 0, 0), false);
                world.removeBlock(pos.up().add(-1, 0, 0), false);
            } else if (state.get(PIECE) == 4) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.down(), false);

                world.removeBlock(pos.up().add(-1, 0, 0), false);
                world.removeBlock(pos.add(-1, 0, 0), false);
                world.removeBlock(pos.down().add(-1, 0, 0), false);

            } else if (state.get(PIECE) == 5) {
                world.removeBlock(pos.down(), false);
                world.removeBlock(pos.down().down(), false);

                world.removeBlock(pos.down().down().add(-1, 0, 0), false);
                world.removeBlock(pos.add(-1, 0, 0), false);
                world.removeBlock(pos.down().add(-1, 0, 0), false);
            }
        } else {

            if (state.get(PIECE) == 1) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.down(), false);

                world.removeBlock(pos.up().add(0, 0, 1), false);
                world.removeBlock(pos.add(0, 0, 1), false);
                world.removeBlock(pos.down().add(0, 0, 1), false);

            } else if (state.get(PIECE) == 2) {
                world.removeBlock(pos.down(), false);
                world.removeBlock(pos.down().down(), false);

                world.removeBlock(pos.down().down().add(0, 0, 1), false);
                world.removeBlock(pos.add(0, 0, 1), false);
                world.removeBlock(pos.down().add(0, 0, 1), false);

            } else if (state.get(PIECE) == 0) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.up().up(), false);
                world.removeBlock(pos.add(0, 0, 1), false);
                world.removeBlock(pos.up().up().add(0, 0, 1), false);
                world.removeBlock(pos.up().add(0, 0, 1), false);
            } else if (state.get(PIECE) == 3) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.up().up(), false);

                world.removeBlock(pos.add(0, 0, -1), false);
                world.removeBlock(pos.up().up().add(0, 0, -1), false);
                world.removeBlock(pos.up().add(0, 0, -1), false);
            } else if (state.get(PIECE) == 4) {
                world.removeBlock(pos.up(), false);
                world.removeBlock(pos.down(), false);

                world.removeBlock(pos.up().add(0, 0, -1), false);
                world.removeBlock(pos.add(-0, 0, -1), false);
                world.removeBlock(pos.down().add(0, 0, -1), false);

            } else if (state.get(PIECE) == 5) {
                world.removeBlock(pos.down(), false);
                world.removeBlock(pos.down().down(), false);

                world.removeBlock(pos.down().down().add(0, 0, -1), false);
                world.removeBlock(pos.add(0, 0, -1), false);
                world.removeBlock(pos.down().add(0, 0, -1), false);

            }
        }

        super.onBreak(world, pos, state, player);
    }
}
