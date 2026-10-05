package com.dead_comedian.holyhell.server.block;

import com.dead_comedian.holyhell.server.registries.HolyHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class MediumCarvedMarbleBlock extends CarvedMarbleBlock {
    public MediumCarvedMarbleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER));

    }

    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add( HALF);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.updateConnections(context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return this.updateConnections(pLevel, pPos);
    }

    private BlockState updateConnections(LevelAccessor level, BlockPos pos) {
        return this.defaultBlockState()
                .setValue(HALF, !isSameBlockAbove(level,pos) ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER);
    }

    private boolean isSameBlockAbove(LevelAccessor level, BlockPos pos){
        return (level.getBlockState(pos).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_1.get()) && level.getBlockState(pos.above()).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_1.get()))
        || (level.getBlockState(pos).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_2.get()) && level.getBlockState(pos.above()).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_2.get()))
        || (level.getBlockState(pos).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_3.get()) && level.getBlockState(pos.above()).is(HolyHellBlocks.CARVED_MARBLE_MEDIUM_3.get())
        );
    }

}
