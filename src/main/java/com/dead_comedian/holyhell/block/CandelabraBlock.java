package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.ToIntFunction;


public class CandelabraBlock extends Block {
    protected final ParticleOptions particle;
    public static final IntegerProperty CANDLE = IntegerProperty.create("candle", 0, 3);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final ToIntFunction<BlockState> LIGHT_EMISSION= (p_152848_) -> {
        return (Boolean)p_152848_.getValue(LIT) ? 4 * ((Integer)p_152848_.getValue(CANDLE)+1) : 0;
    };
    public CandelabraBlock(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(CANDLE, 0).setValue(LIT, false));
    }


    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pDirection == Direction.DOWN && !this.canPlaceAt(pState, pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);

    }

    public boolean canPlaceAt(BlockState state, LevelAccessor world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !pState.getValue(LIT)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 11);
            pLevel.playLocalSound(pPos, HolyHellSound.CANDELABRA_LIGHT.get(), SoundSource.BLOCKS, 1, 1 + pLevel.random.nextInt(), false);

            return InteractionResult.SUCCESS;
        }
        if (pState.getValue(LIT) && itemStack.isEmpty()) {
            pLevel.setBlock(pPos, pState.setValue(LIT, false), 11);
            return InteractionResult.SUCCESS;
        }
        if (itemStack.is(Item.byBlock(HolyHellBlocks.CANDELABRA.get())) && pState.getValue(CANDLE) < 3) {
           if (!pPlayer.isCreative()){
            itemStack.shrink(1);}
            pLevel.setBlock(pPos, pState.setValue(CANDLE, pState.getValue(CANDLE) + 1),11);
            pLevel.playLocalSound(pPos, HolyHellSound.CANDELABRA_PLACE.get(), SoundSource.BLOCKS, 1, 1 + pLevel.random.nextInt(), false);
            return InteractionResult.SUCCESS;
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CANDLE, LIT);
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


            if (pState.getValue(CANDLE) == 0) {
                d = pPos.getX() + 0.5;
                e = pPos.getY() + 0.9;
                f = pPos.getZ() + 0.5;
            } else if (pState.getValue(CANDLE) == 1) {
                d = pPos.getX() + 0.3;
                e = pPos.getY() + 1.1;
                f = pPos.getZ() + 0.5;
                d1 = pPos.getX() + 0.5;
                e1 = pPos.getY() + 1.1;
                f1 = pPos.getZ() + 0.3;
                d2 = pPos.getX() + 0.5;
                e2 = pPos.getY() + 1.1;
                f2 = pPos.getZ() + 0.7;
                d3 = pPos.getX() + 0.7;
                e3 = pPos.getY() + 1.1;
                f3 = pPos.getZ() + 0.5;
                pLevel.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
            } else if (pState.getValue(CANDLE) == 2) {
                d = pPos.getX() + 0.5;
                e = pPos.getY() + 1.1;
                f = pPos.getZ() + 0.5;
                d1 = pPos.getX() + 0.5;
                e1 = pPos.getY() + 1.1;
                f1 = pPos.getZ() + 0.2;
                d2 = pPos.getX() + 0.5;
                e2 = pPos.getY() + 1.1;
                f2 = pPos.getZ() + 0.8;
                d3 = pPos.getX() + 0.8;
                e3 = pPos.getY() + 1.1;
                f3 = pPos.getZ() + 0.5;
                d4 = pPos.getX() + 0.2;
                e4 = pPos.getY() + 1.1;
                f4 = pPos.getZ() + 0.5;
                pLevel.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                pLevel.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
            } else if (pState.getValue(CANDLE) == 3) {
                d = pPos.getX() + 0.5;
                e = pPos.getY() + 1.1;
                f = pPos.getZ() + 0.35;

                d1 = pPos.getX() + 0.65;
                e1 = pPos.getY() + 1.1;
                f1 = pPos.getZ() + 0.5;

                d2 = pPos.getX() + 0.5;
                e2 = pPos.getY() + 1.1;
                f2 = pPos.getZ() + 0.65;

                d3 = pPos.getX() + 0.35;
                e3 = pPos.getY() + 1.1;
                f3 = pPos.getZ() + 0.5;


                d4 = pPos.getX() + 0.1;
                e4 = pPos.getY() + 1;
                f4 = pPos.getZ() + 0.5;

                d5 = pPos.getX() + 0.9;
                e5 = pPos.getY() + 1;
                f5 = pPos.getZ() + 0.5;

                d6 = pPos.getX() + 0.5;
                e6 = pPos.getY() + 1;
                f6 = pPos.getZ() + 0.1;

                d7 = pPos.getX() + 0.5;
                e7 = pPos.getY() + 1;
                f7 = pPos.getZ() + 0.9;
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
