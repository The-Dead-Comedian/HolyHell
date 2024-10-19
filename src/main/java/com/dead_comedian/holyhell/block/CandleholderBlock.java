package com.dead_comedian.holyhell.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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


public class CandleholderBlock extends Block {
    public static final IntProperty PIECE = IntProperty.of("piece",0,2);
    protected final ParticleEffect particle;
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public CandleholderBlock(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
        this.setDefaultState((BlockState) this.getDefaultState().with(PIECE, 0));
        this.setDefaultState((BlockState) this.getDefaultState().with(LIT, false));    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PIECE);
        builder.add(LIT);
        super.appendProperties(builder);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {

            world.setBlockState(pos.up(), state.with(PIECE, 1), 3);
            world.setBlockState(pos.up().up(), state.with(PIECE, 2), 3);

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.FLINT_AND_STEEL) && !state.get(LIT) && state.get(PIECE) == 2) {
            world.setBlockState(pos, state.with(LIT, true));
            return ActionResult.SUCCESS;
        }
        if (state.get(LIT) && state.get(PIECE) == 2) {
            world.setBlockState(pos, state.with(LIT, false));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
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


          if (state.get(LIT)) {
                d = pos.getX() + 0.5;
                e = pos.getY() + 0.35;
                f = pos.getZ() + 0.3;

                d1 = pos.getX() + 0.7;
                e1 = pos.getY() + 0.35;
                f1 = pos.getZ() + 0.5;

                d2 = pos.getX() + 0.5;
                e2 = pos.getY() + 0.35;
                f2 = pos.getZ() + 0.7;

                d3 = pos.getX() + 0.3;
                e3 = pos.getY() + 0.35;
                f3 = pos.getZ() + 0.5;


                d4 = pos.getX();
                e4 = pos.getY() + 0.4;
                f4 = pos.getZ() + 0.5;

                d5 = pos.getX() + 1;
                e5 = pos.getY() + 0.4;
                f5 = pos.getZ() + 0.5;

                d6 = pos.getX() + 0.5;
                e6 = pos.getY() + 0.4;
                f6 = pos.getZ() ;

                d7 = pos.getX() + 0.5;
                e7 = pos.getY() + 0.4;
                f7 = pos.getZ() + 1;
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
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(state.get(PIECE) == 1){
            world.removeBlock(pos.up(), false);
            world.removeBlock(pos.down(), false);

        } else if (state.get(PIECE) == 2) {
            world.removeBlock(pos.down(), false);
            world.removeBlock(pos.down().down(), false);

        } else if (state.get(PIECE) == 0) {
            world.removeBlock(pos.up(), false);
            world.removeBlock(pos.up().up(), false);}

        super.onBreak(world, pos, state, player);
    }
}
