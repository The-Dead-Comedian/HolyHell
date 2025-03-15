package com.dead_comedian.holyhell.block;


import com.dead_comedian.holyhell.block.entity.DiviningTableBlockEntity;
import com.dead_comedian.holyhell.entity.CherubEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


public class DiviningTableBlock extends BaseEntityBlock implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DiviningTableBlock(Properties p_54120_) {
        super(p_54120_);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, HolyHellBlockEntities.DIVINING_TABLE_BLOCK_ENTITY.get(), (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (((DiviningTableBlockEntity) pLevel.getBlockEntity(pPos)).getTimer() >= 1500 ||
                ((DiviningTableBlockEntity) pLevel.getBlockEntity(pPos)).getTimer() <= 0) {


            pLevel.playLocalSound(pPos, HolyHellSound.DIVINING_TABLE_INTERACT.get(), SoundSource.BLOCKS, 1, 1 + pLevel.random.nextInt(), false);

            CherubEntity cherubEntity = new CherubEntity(HolyHellEntities.CHERUB.get(), pLevel);
            pLevel.addFreshEntity(cherubEntity);
            cherubEntity.moveTo(pPos.offset(0, 1, 0), cherubEntity.getYRot(), cherubEntity.getXRot());

            ((DiviningTableBlockEntity) pLevel.getBlockEntity(pPos)).setTimer(1);

            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return this.defaultBlockState().setValue(FACING, p_49820_.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        super.createBlockStateDefinition(p_49915_);
        p_49915_.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DiviningTableBlockEntity(pPos, pState);
    }
}
