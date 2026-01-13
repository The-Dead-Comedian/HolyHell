package com.dead_comedian.holyhell.server.block;

import com.dead_comedian.holyhell.server.registries.HolyHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

public class MarbleCollumnBlock extends Block {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public MarbleCollumnBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN);
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
                .setValue(UP, this.canConnect(level, pos, Direction.UP))
                .setValue(DOWN, this.canConnect(level, pos, Direction.DOWN));

    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(ItemTags.PICKAXES)) {
            if (level.getBlockState(pos.above()).is(HolyHellBlocks.MARBLE.get())) {
                level.removeBlock(pos,false);
                level.removeBlock(pos.above(),false);
                level.setBlock(pos,HolyHellBlocks.ATLAS_STATUE.get()
                        .defaultBlockState().setValue(MarbleStatueBlock.HALF, DoubleBlockHalf.LOWER)
                        .setValue(MarbleStatueBlock.FACING, player.getDirection().getOpposite()),11 );

                level.setBlock(pos.above(),HolyHellBlocks.ATLAS_STATUE.get()
                        .defaultBlockState().setValue(MarbleStatueBlock.HALF, DoubleBlockHalf.UPPER)
                        .setValue(MarbleStatueBlock.FACING, player.getDirection().getOpposite()),11 );

                level.playSound(player,pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS,1,1);
                return ItemInteractionResult.SUCCESS;
            }

        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);

    }

    private boolean canConnect(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.relative(direction);
        BlockState neighborState = level.getBlockState(neighborPos);
        return neighborState.is(this);
    }
}
