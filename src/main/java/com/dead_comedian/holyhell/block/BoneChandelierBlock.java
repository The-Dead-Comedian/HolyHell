package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.ToIntFunction;

public class BoneChandelierBlock extends Block {
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
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos neighborPos) {
        return direction == Direction.UP && !this.canSurvive(blockState, levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return canSupportCenter(levelReader, blockPos.below(), Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !pState.getValue(LIT)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 11);
            pLevel.playLocalSound(pPos, HolyHellSound.CANDELABRA_LIGHT.get(), SoundSource.BLOCKS, 1, 1 + pLevel.random.nextInt(), false);

            for (int i = 1; i < 15; i++) {
                if (pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ())).is(Blocks.AIR) || pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ())).is(Blocks.CAVE_AIR) || pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ())).is(Blocks.VOID_AIR)) {
                    pLevel.setBlock(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ()), Blocks.LIGHT.defaultBlockState(), 11);
                } else {
                    i = 16;
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (pState.getValue(LIT) && itemStack.isEmpty()) {
            pLevel.setBlock(pPos, pState.setValue(LIT, false), 11);
            for (int i = 1; i < 15; i++) {
                if (pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ())).is(Blocks.LIGHT)) {
                    pLevel.setBlock(new BlockPos(pPos.getX(), pPos.getY() - i, pPos.getZ()), Blocks.AIR.defaultBlockState(), 11);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);
        if (pState.getValue(LIT)) {
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
                d = pPos.getX() + 1.15;
                e = pPos.getY() + 0.9;
                f = pPos.getZ() + 1.15;

                d1 = pPos.getX() + 1.15;
                e1 = pPos.getY() + 0.9;
                f1 = pPos.getZ() + -0.15;

                d2 = pPos.getX() + -0.15;
                e2 = pPos.getY() + 0.9;
                f2 = pPos.getZ() + -0.15;

                d3 = pPos.getX() + -0.15;
                e3 = pPos.getY() + 0.9;
                f3 = pPos.getZ() + 1.15;


                d4 = pPos.getX() + 0.85;
                e4 = pPos.getY() + 0.8;
                f4 = pPos.getZ() + 0.85;

                d5 = pPos.getX() + 0.15;
                e5 = pPos.getY() + 0.8;
                f5 = pPos.getZ() + 0.15;

                d6 = pPos.getX() + 0.15;
                e6 = pPos.getY() + 0.8;
                f6 = pPos.getZ() + 0.85;

                d7 = pPos.getX() + 0.85;
                e7 = pPos.getY() + 0.8;
                f7 = pPos.getZ() + 0.15;
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


}
