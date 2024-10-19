package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CandelabraBlock extends Block {
    protected final ParticleEffect particle;
    public static final IntProperty CANDLE = IntProperty.of("candle", 0, 3);
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public CandelabraBlock(AbstractBlock.Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
        this.setDefaultState((BlockState) this.getDefaultState().with(CANDLE, 0));
        this.setDefaultState((BlockState) this.getDefaultState().with(LIT, false));
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.FLINT_AND_STEEL) && !state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, true));
            return ActionResult.SUCCESS;
        }
        if (state.get(LIT) && itemStack.isEmpty()) {
            world.setBlockState(pos, state.with(LIT, false));
            return ActionResult.SUCCESS;
        }
        if (itemStack.isOf(Item.fromBlock(HolyHellBlocks.CANDELABRA)) && state.get(CANDLE) < 3) {
           if (!player.isCreative()){
            itemStack.decrement(1);}
            world.setBlockState(pos, state.with(CANDLE, state.get(CANDLE) + 1));

            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(CANDLE);
        builder.add(LIT);
        super.appendProperties(builder);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
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


            if (state.get(CANDLE) == 0) {
                d = pos.getX() + 0.5;
                e = pos.getY() + 0.9;
                f = pos.getZ() + 0.5;
            } else if (state.get(CANDLE) == 1) {
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
            } else if (state.get(CANDLE) == 2) {
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
            } else if (state.get(CANDLE) == 3) {
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
