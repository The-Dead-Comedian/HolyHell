package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;


import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FallingCrossBlock extends BaseEntityBlock implements EntityBlock, Fallable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public FallingCrossBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void onLand(Level pLevel, BlockPos pPos, BlockState pState, BlockState pReplaceableState, FallingBlockEntity pFallingBlock) {
        List<Entity> wiw = pLevel.getEntities(null, new AABB(pPos).inflate(1, 1, 1));
        for (Entity entity : wiw) {
            entity.hurt(pLevel.damageSources().fallingBlock(entity), 20);

        }
        pLevel.playSound(null, pPos, HolyHellSound.CROSS_FALL.get(), SoundSource.BLOCKS,3,1+pLevel.random.nextFloat());

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.playLocalSound(pPos, HolyHellSound.CROSS_FALL.get(), SoundSource.BLOCKS,6,1,true);

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY.get(), (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FallingCrossBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
