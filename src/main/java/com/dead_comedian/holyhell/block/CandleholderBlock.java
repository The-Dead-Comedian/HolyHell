package com.dead_comedian.holyhell.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;


import java.util.function.ToIntFunction;

public class CandleholderBlock extends Block {
    public static final IntegerProperty PIECE = IntegerProperty.create("piece", 0, 2);
    protected final ParticleOptions particle;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return (Boolean) p_152848_.getValue(LIT) ? 15 : 0;
    };

    public CandleholderBlock(BlockBehaviour.Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(PIECE, 0).setValue(LIT, false));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pDirection == Direction.DOWN && !this.canPlaceAt(pState, pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);

    }

    public boolean canPlaceAt(BlockState state, LevelAccessor world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP) || state.is(this.asBlock());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PIECE, LIT);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {

        world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
        world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !pState.getValue(LIT) && pState.getValue(PIECE) == 2) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 11);
            return InteractionResult.SUCCESS;
        }
        if (pState.getValue(LIT) && pState.getValue(PIECE) == 2) {
            pLevel.setBlock(pPos, pState.setValue(LIT, false), 11);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        if (pState.getValue(LIT)) {
            double d = pPos.getX();
            double e = pPos.getY();
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
                e = pPos.getY() + 0.35;
                f = pPos.getZ() + 0.3;

                d1 = pPos.getX() + 0.7;
                e1 = pPos.getY() + 0.35;
                f1 = pPos.getZ() + 0.5;

                d2 = pPos.getX() + 0.5;
                e2 = pPos.getY() + 0.35;
                f2 = pPos.getZ() + 0.7;

                d3 = pPos.getX() + 0.3;
                e3 = pPos.getY() + 0.35;
                f3 = pPos.getZ() + 0.5;


                d4 = pPos.getX();
                e4 = pPos.getY() + 0.4;
                f4 = pPos.getZ() + 0.5;

                d5 = pPos.getX() + 1;
                e5 = pPos.getY() + 0.4;
                f5 = pPos.getZ() + 0.5;

                d6 = pPos.getX() + 0.5;
                e6 = pPos.getY() + 0.4;
                f6 = pPos.getZ();

                d7 = pPos.getX() + 0.5;
                e7 = pPos.getY() + 0.4;
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
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
        if (pState.getValue(PIECE) == 1) {
            pLevel.removeBlock(pPos.above(), false);
            pLevel.removeBlock(pPos.below(), false);

        } else if (pState.getValue(PIECE) == 2) {
            pLevel.removeBlock(pPos.below(), false);
            pLevel.removeBlock(pPos.below().below(), false);

        } else if (pState.getValue(PIECE) == 0) {
            pLevel.removeBlock(pPos.above(), false);
            pLevel.removeBlock(pPos.above().above(), false);
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }
}
