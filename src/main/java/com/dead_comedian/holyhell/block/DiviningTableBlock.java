package com.dead_comedian.holyhell.block;


import com.dead_comedian.holyhell.entity.BabTwoEntity;
import com.dead_comedian.holyhell.entity.CherubEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

import java.util.List;



public class DiviningTableBlock extends HorizontalDirectionalBlock {
    public DiviningTableBlock(Properties p_54120_) {
        super(p_54120_);
    }

    @Override
        public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        p_60504_.playLocalSound(p_60505_, HolyHellSound.DIVINING_TABLE_INTERACT.get(), SoundSource.BLOCKS, 1, 1 + p_60504_.random.nextInt(), false);
        CherubEntity cherubEntity = new CherubEntity(HolyHellEntities.CHERUB.get(), p_60504_);
        p_60504_.addFreshEntity(cherubEntity);
        cherubEntity.moveTo(p_60505_.offset(0,1,0), cherubEntity.getYRot(), cherubEntity.getXRot());
        return InteractionResult.SUCCESS;
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
}
