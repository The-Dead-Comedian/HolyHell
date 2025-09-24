package com.dead_comedian.holyhell.block;


import com.dead_comedian.holyhell.registries.HolyHellSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


import java.util.function.ToIntFunction;

public class CandleholderBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);
    protected final ParticleOptions particle;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return (Boolean) p_152848_.getValue(LIT) ? 15 : 0;
    };

    public CandleholderBlock(BlockBehaviour.Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(LIT, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (direction.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            return neighborState.getBlock() instanceof CandleholderBlock && neighborState.getValue(HALF) != half ? neighborState.setValue(HALF, half) : Blocks.AIR.defaultBlockState();
        } else {
            return half == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? belowState.isFaceSturdy(level, belowPos, Direction.UP) : belowState.is(this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HALF, LIT);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            return (this.defaultBlockState().setValue(LIT,false)).setValue(HALF, DoubleBlockHalf.LOWER);
        } else return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT) && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            level.setBlock(pos, state.setValue(LIT, true), 11);
            level.playLocalSound(pos, HolyHellSounds.CANDELABRA_LIGHT.get(), SoundSource.BLOCKS, 1, 1 + level.random.nextInt(), false);
            return ItemInteractionResult.SUCCESS;
        }
        if (state.getValue(LIT) && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            level.setBlock(pos, state.setValue(LIT, false), 11);
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }


    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        if (pState.getValue(LIT) && pState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            double d = pPos.getX();
            double e = pPos.getY() + 1;
            double f = pPos.getZ();
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


            if (pState.getValue(LIT)) {
                d = pPos.getX() + 0.5;
                e = pPos.getY() + 0.35 + 1;
                f = pPos.getZ() + 0.3;

                d1 = pPos.getX() + 0.7;
                e1 = pPos.getY() + 0.35 + 1;
                f1 = pPos.getZ() + 0.5;

                d2 = pPos.getX() + 0.5;
                e2 = pPos.getY() + 0.35 + 1;
                f2 = pPos.getZ() + 0.7;

                d3 = pPos.getX() + 0.3;
                e3 = pPos.getY() + 0.35 + 1;
                f3 = pPos.getZ() + 0.5;


                d4 = pPos.getX();
                e4 = pPos.getY() + 0.4 + 1;
                f4 = pPos.getZ() + 0.5;

                d5 = pPos.getX() + 1;
                e5 = pPos.getY() + 0.4 + 1;
                f5 = pPos.getZ() + 0.5;

                d6 = pPos.getX() + 0.5;
                e6 = pPos.getY() + 0.4 + 1;
                f6 = pPos.getZ();

                d7 = pPos.getX() + 0.5;
                e7 = pPos.getY() + 0.4 + 1;
                f7 = pPos.getZ() + 1;
                pLevel.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);

                pLevel.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d5, e5, f5, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d6, e6, f6, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d7, e7, f7, 0.0, 0.0, 0.0);
            }
            pLevel.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            pLevel.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);


        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state, level, pos))) {
            preventDropFromBottomPart(level, pos, state, player);
        }

        return super.playerWillDestroy(level, pos, state, player);
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
