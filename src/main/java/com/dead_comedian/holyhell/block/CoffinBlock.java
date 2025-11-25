package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class CoffinBlock extends BaseEntityBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public CoffinBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(OPEN,false).setValue(ACTIVATED,false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HALF, FACING, ACTIVATED,OPEN);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        switch (state.getValue(FACING)) {
            case NORTH -> level.setBlock(pos.north(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
            case SOUTH -> level.setBlock(pos.south(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
            case EAST -> level.setBlock(pos.east(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
            case WEST -> level.setBlock(pos.west(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            switch (state.getValue(FACING)) {
                case NORTH -> level.removeBlock(pos.north(), false);
                case SOUTH -> level.removeBlock(pos.south(), false);
                case EAST -> level.removeBlock(pos.east(), false);
                case WEST -> level.removeBlock(pos.west(), false);
            }
        }
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            switch (state.getValue(FACING)) {
                case NORTH -> level.removeBlock(pos.south(), false);
                case SOUTH -> level.removeBlock(pos.north(), false);
                case EAST -> level.removeBlock(pos.west(), false);
                case WEST -> level.removeBlock(pos.east(), false);
            }
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Nullable
    public BlockEntity getLowerBlockEntity(BlockPos pos, BlockState state, Level level) {
            switch (state.getValue(FACING)) {
                case NORTH -> {
                    return level.getBlockEntity(pos.south());
                }
                case SOUTH -> {
                    return level.getBlockEntity(pos.north());
                }
                case EAST -> {
                    return level.getBlockEntity(pos.west());
                }
                case WEST -> {
                    return level.getBlockEntity(pos.east());
                }
                default -> {
                    return null;
                }
            }
    }



    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }



    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CoffinBlockEntity) {

            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = null;

            if(pState.getValue(HALF) == DoubleBlockHalf.LOWER){
                entity = pLevel.getBlockEntity(pPos);
            }
            else if(pState.getValue(HALF) == DoubleBlockHalf.UPPER){
                entity = this.getLowerBlockEntity(pPos, pState, pLevel);
                System.out.println(entity);
                System.out.println(entity.getBlockPos());
            }




            if(entity instanceof CoffinBlockEntity && entity != null) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (CoffinBlockEntity)entity, entity.getBlockPos());
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
            if(!pState.getValue(ACTIVATED)) {
                ((CoffinBlockEntity) entity).setStoredPlayer(pPlayer.getUUID());
            }
        }


        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if(pState.getValue(HALF)==DoubleBlockHalf.LOWER) return new CoffinBlockEntity(pPos, pState);
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, HolyHellBlockEntities.COFFIN_BLOCK_ENTITY.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(level, player, pos, state, pBlockEntity, pTool);
        if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state))) {
            preventDropFromBottomPart(level, pos, state, player);
        }
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (half == DoubleBlockHalf.UPPER) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.is(state.getBlock()) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState postDestroyState = belowState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(belowPos, postDestroyState, 35);
                level.levelEvent(player, 2001, belowPos, Block.getId(belowState));
            }
        }
    }

}
