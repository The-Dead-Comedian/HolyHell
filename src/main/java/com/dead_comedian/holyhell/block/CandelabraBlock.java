package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CandelabraBlock extends Block {
    protected final ParticleOptions particle;
    public static final IntegerProperty CANDLE = IntegerProperty.create("candle", 0, 3);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public CandelabraBlock(BlockBehaviour.Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(CANDLE, 0));
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(LIT, false));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT)) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, true));
            return InteractionResult.SUCCESS;
        }
        if (state.getValue(LIT) && itemStack.isEmpty()) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, false));
            return InteractionResult.SUCCESS;
        }
        if (itemStack.is(Item.byBlock(HolyHellBlocks.CANDELABRA)) && state.getValue(CANDLE) < 3) {
           if (!player.isCreative()){
            itemStack.shrink(1);}
            world.setBlockAndUpdate(pos, state.setValue(CANDLE, state.getValue(CANDLE) + 1));

            return InteractionResult.SUCCESS;
        }

        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {

        builder.add(CANDLE);
        builder.add(LIT);
        super.createBlockStateDefinition(builder);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
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
                world.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
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
                world.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
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
                world.addParticle(this.particle, d1, e1, f1, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d2, e2, f2, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d3, e3, f3, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);

                world.addParticle(this.particle, d4, e4, f4, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d5, e5, f5, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d6, e6, f6, 0.0, 0.0, 0.0);
                world.addParticle(this.particle, d7, e7, f7, 0.0, 0.0, 0.0);
            }
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);


        }
    }
}
