package com.dead_comedian.holyhell.server.block;

import com.dead_comedian.holyhell.server.block.entity.FallingSmashingBlockEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FallingCrossBlock extends BaseEntityBlock implements EntityBlock, Fallable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public FallingCrossBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public void onLand(Level pLevel, BlockPos pPos, BlockState pState, BlockState pReplaceableState, FallingBlockEntity pFallingBlock) {
        List<Entity> wiw = pLevel.getEntities(null, new AABB(pPos).inflate(1, 1, 1));

        if (!pFallingBlock.isSilent()) {
            pLevel.playSound((Player) null, pPos, HolyHellSounds.STONE_CRACK.get(), SoundSource.BLOCKS, 0.8f, 1);
        }
        for (Entity entity : wiw) {
            entity.hurt(pLevel.damageSources().fallingBlock(entity), 20);
        }
        for (int i = 0; i < 20; i++) {

            if (pLevel instanceof ServerLevel) {
                ((ServerLevel) pLevel).sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE.getType(), (double) pPos.getX(), (double) pPos.getY(), (double) pPos.getZ(), 15, 1.0, 1.0, 1.0, 0.2);
            }
        }
        wiw.clear();

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
        return new FallingSmashingBlockEntity(HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY  .get(), pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
