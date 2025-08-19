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
import net.minecraft.world.ItemInteractionResult;
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
    public BlockState updateShape(BlockState state, Direction pDirection, BlockState pNeighborState, LevelAccessor level, BlockPos pos, BlockPos pNeighborPos) {
        return pDirection == Direction.DOWN && !this.canPlaceAt(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, pDirection, pNeighborState, level, pos, pNeighborPos);

    }

    public boolean canPlaceAt(BlockState state, LevelAccessor world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, true), 11);
            level.playLocalSound(pos, HolyHellSound.CANDELABRA_LIGHT.get(), SoundSource.BLOCKS, 1, 1 + level.random.nextInt(), false);

            return ItemInteractionResult.SUCCESS;
        }
        if (state.getValue(LIT) && itemStack.isEmpty()) {
            level.setBlock(pos, state.setValue(LIT, false), 11);
            return ItemInteractionResult.SUCCESS;
        }
        if (itemStack.is(Item.byBlock(HolyHellBlocks.CANDELABRA.get())) && state.getValue(CANDLE) < 3) {
            if (!player.isCreative()){
                itemStack.shrink(1);}
            level.setBlock(pos, state.setValue(CANDLE, state.getValue(CANDLE) + 1),11);
            level.playLocalSound(pos, HolyHellSound.CANDELABRA_PLACE.get(), SoundSource.BLOCKS, 1, 1 + level.random.nextInt(), false);
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CANDLE, LIT);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource pRandom) {
        super.animateTick(state, level, pos, pRandom);
        if (state.getValue(LIT)) {
            double d = pos.getX();
            double e = pos.getY();
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


            if (state.getValue(CANDLE) == 0) {
                d = pos.getX() + 0.5;
                e = pos.getY() + 0.9;
                f = pos.getZ() + 0.5;
            } else if (state.getValue(CANDLE) == 1) {
                d = pos.getX() + 0.3;
                e = pos.getY() + 1.1;
                f = pos.getZ() + 0.5;
                d1 = pos.getX() + 0.5;
                e1 = pos.getY() + 1.1;
                f1 = pos.getZ() + 0.3;
                d2 = pos.getX() + 0.5;
                e2 = pos.getY() + 1.1;
                f2 = pos.getZ() + 0.7;
                d3 = pos.getX() + 0.7;
                e3 = pos.getY() + 1.1;
                f3 = pos.getZ() + 0.5;
                level.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
            } else if (state.getValue(CANDLE) == 2) {
                d = pos.getX() + 0.5;
                e = pos.getY() + 1.1;
                f = pos.getZ() + 0.5;
                d1 = pos.getX() + 0.5;
                e1 = pos.getY() + 1.1;
                f1 = pos.getZ() + 0.2;
                d2 = pos.getX() + 0.5;
                e2 = pos.getY() + 1.1;
                f2 = pos.getZ() + 0.8;
                d3 = pos.getX() + 0.8;
                e3 = pos.getY() + 1.1;
                f3 = pos.getZ() + 0.5;
                d4 = pos.getX() + 0.2;
                e4 = pos.getY() + 1.1;
                f4 = pos.getZ() + 0.5;
                level.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                level.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
            } else if (state.getValue(CANDLE) == 3) {
                d = pos.getX() + 0.5;
                e = pos.getY() + 1.1;
                f = pos.getZ() + 0.35;

                d1 = pos.getX() + 0.65;
                e1 = pos.getY() + 1.1;
                f1 = pos.getZ() + 0.5;

                d2 = pos.getX() + 0.5;
                e2 = pos.getY() + 1.1;
                f2 = pos.getZ() + 0.65;

                d3 = pos.getX() + 0.35;
                e3 = pos.getY() + 1.1;
                f3 = pos.getZ() + 0.5;


                d4 = pos.getX() + 0.1;
                e4 = pos.getY() + 1;
                f4 = pos.getZ() + 0.5;

                d5 = pos.getX() + 0.9;
                e5 = pos.getY() + 1;
                f5 = pos.getZ() + 0.5;

                d6 = pos.getX() + 0.5;
                e6 = pos.getY() + 1;
                f6 = pos.getZ() + 0.1;

                d7 = pos.getX() + 0.5;
                e7 = pos.getY() + 1;
                f7 = pos.getZ() + 0.9;
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
}
