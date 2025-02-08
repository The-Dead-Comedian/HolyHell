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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;


public class CandleholderBlock extends Block {
    public static final IntegerProperty PIECE = IntegerProperty.create("piece",0,2);
    protected final ParticleOptions particle;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public CandleholderBlock(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(PIECE, 0));
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(LIT, false));    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PIECE);
        builder.add(LIT);
        super.createBlockStateDefinition(builder);
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {

            world.setBlock(pos.above(), state.setValue(PIECE, 1), 3);
            world.setBlock(pos.above().above(), state.setValue(PIECE, 2), 3);

    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT) && state.getValue(PIECE) == 2) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, true));
            return InteractionResult.SUCCESS;
        }
        if (state.getValue(LIT) && state.getValue(PIECE) == 2) {
            world.setBlockAndUpdate(pos, state.setValue(LIT, false));
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
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


          if (state.getValue(LIT)) {
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
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if(state.getValue(PIECE) == 1){
            world.removeBlock(pos.above(), false);
            world.removeBlock(pos.below(), false);

        } else if (state.getValue(PIECE) == 2) {
            world.removeBlock(pos.below(), false);
            world.removeBlock(pos.below().below(), false);

        } else if (state.getValue(PIECE) == 0) {
            world.removeBlock(pos.above(), false);
            world.removeBlock(pos.above().above(), false);}

        super.playerWillDestroy(world, pos, state, player);
    }
}
