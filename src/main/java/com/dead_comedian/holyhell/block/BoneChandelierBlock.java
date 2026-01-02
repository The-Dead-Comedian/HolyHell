package com.dead_comedian.holyhell.block;


import com.dead_comedian.holyhell.block.entity.FallingSmashingBlockEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.function.ToIntFunction;

public class BoneChandelierBlock extends BaseEntityBlock implements EntityBlock, Fallable {
    protected final ParticleOptions particle;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return (Boolean) p_152848_.getValue(LIT) ? 15 : 0;
    };

    public BoneChandelierBlock(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {

        if (direction == Direction.UP && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, direction, neighbourState, level, pos, neighbourPos);
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);

        return aboveState.isFaceSturdy(level, above, Direction.DOWN) || aboveState.is(Blocks.CHAIN);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos pos = ctx.getClickedPos();

        if (!ctx.getLevel().getBlockState(pos.above())
                .isFaceSturdy(ctx.getLevel(), pos.above(), Direction.DOWN)) {
            return null; // cancels placement
        }

        return this.defaultBlockState();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, true), 11);
            level.playLocalSound(pos, HolyHellSounds.CANDELABRA_LIGHT.get(), SoundSource.BLOCKS, 1, 1 + level.random.nextInt(), false);

            for (int i = 1; i < 15; i++) {
                if (level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.AIR) || level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.CAVE_AIR) || level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.VOID_AIR)) {
                    level.setBlock(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()), Blocks.LIGHT.defaultBlockState(), 11);
                } else {
                    i = 16;
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        if (state.getValue(LIT) && itemStack.isEmpty()) {
            level.setBlock(pos, state.setValue(LIT, false), 11);
            for (int i = 1; i < 15; i++) {
                if (level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.LIGHT)) {
                    level.setBlock(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()), Blocks.AIR.defaultBlockState(), 11);
                }
            }

            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }



    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        for (int i = 1; i < 15; i++) {
            if (level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.LIGHT)) {
                level.setBlock(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()), Blocks.AIR.defaultBlockState(), 11);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }


    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource pRandom) {
        super.animateTick(state, level, pos, pRandom);
        if (state.getValue(LIT)) {
            double d = pos.getX();
            double e = pos.getY() + 1;
            double f = pos.getZ();
            double d1;
            double e1;
            double f1;
            double d2;
            double e2;
            double f2;
            double d3;
            double e3;
            double f3;
            double d4;
            double e4;
            double f4;
            double d5;
            double e5;
            double f5;
            double d6;
            double e6;
            double f6;
            double d7;
            double e7;
            double f7;


            if (state.getValue(LIT)) {
                d = pos.getX() + 1.15;
                e = pos.getY() + 0.9;
                f = pos.getZ() + 1.15;

                d1 = pos.getX() + 1.15;
                e1 = pos.getY() + 0.9;
                f1 = pos.getZ() + -0.15;

                d2 = pos.getX() + -0.15;
                e2 = pos.getY() + 0.9;
                f2 = pos.getZ() + -0.15;

                d3 = pos.getX() + -0.15;
                e3 = pos.getY() + 0.9;
                f3 = pos.getZ() + 1.15;


                d4 = pos.getX() + 0.85;
                e4 = pos.getY() + 0.8;
                f4 = pos.getZ() + 0.85;

                d5 = pos.getX() + 0.15;
                e5 = pos.getY() + 0.8;
                f5 = pos.getZ() + 0.15;

                d6 = pos.getX() + 0.15;
                e6 = pos.getY() + 0.8;
                f6 = pos.getZ() + 0.85;

                d7 = pos.getX() + 0.85;
                e7 = pos.getY() + 0.8;
                f7 = pos.getZ() + 0.15;
                level.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);

                level.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d5, e5, f5, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d6, e6, f6, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d7, e7, f7, 0.0, 0.0, 0.0);
            }
            level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            level.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);


        }
    }
    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, HolyHellBlockEntities.CHANDELIER_BLOCK_ENTITY.get(), (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FallingSmashingBlockEntity(HolyHellBlockEntities.CHANDELIER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}