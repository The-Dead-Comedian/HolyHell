package com.dead_comedian.holyhell.block;


import com.dead_comedian.holyhell.block.entity.FallingSmashingBlockEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.ToIntFunction;

public class ChandelierBlock extends BaseEntityBlock implements EntityBlock, Fallable {
    protected final ParticleOptions particle;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return (Boolean) p_152848_.getValue(LIT) ? 15 : 0;
    };

    public ChandelierBlock(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {

        // If the block ABOVE changed, recheck survival
        if (direction == Direction.UP && !state.canSurvive(level, pos)) {
            // Replace with air
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
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        for (int i = 1; i < 15; i++) {
            if (level.getBlockState(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ())).is(Blocks.LIGHT)) {
                level.setBlock(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()), Blocks.AIR.defaultBlockState(), 11);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

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
                d = pPos.getX() + 1.05;
                e = pPos.getY() + 0.5;
                f = pPos.getZ() + 1.05;

                d1 = pPos.getX() + 1.05;
                e1 = pPos.getY() + 0.5;
                f1 = pPos.getZ() + -0.05;

                d2 = pPos.getX() + -0.05;
                e2 = pPos.getY() + 0.5;
                f2 = pPos.getZ() + -0.05;

                d3 = pPos.getX() + -0.05;
                e3 = pPos.getY() + 0.5;
                f3 = pPos.getZ() + 1.05;


                d4 = pPos.getX() + 0.8;
                e4 = pPos.getY() + 0.4;
                f4 = pPos.getZ() + 0.8;

                d5 = pPos.getX() + 0.8;
                e5 = pPos.getY() + 0.4;
                f5 = pPos.getZ() + 0.2;

                d6 = pPos.getX() + 0.2;
                e6 = pPos.getY() + 0.4;
                f6 = pPos.getZ() + 0.8;

                d7 = pPos.getX() + 0.2;
                e7 = pPos.getY() + 0.4;
                f7 = pPos.getZ() + 0.2;
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
    public void onLand(Level pLevel, BlockPos pPos, BlockState pState, BlockState pReplaceableState, FallingBlockEntity pFallingBlock) {
        List<Entity> wiw = pLevel.getEntities(null, new AABB(pPos).inflate(1, 1, 1));

        if (!pFallingBlock.isSilent()) {
            pLevel.playSound((Player) null, pPos, HolyHellSounds.STONE_CRACK.get(), SoundSource.BLOCKS, 0.8f, 1);
        }
        for (Entity entity : wiw) {
            entity.hurt(pLevel.damageSources().fallingBlock(entity), 20);
        }
        for (int i = 0; i < 20; i++) {

            if (pLevel instanceof ServerLevel) {
                ((ServerLevel) pLevel).sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE.getType(), (double) pPos.getX(), (double) pPos.getY(), (double) pPos.getZ(), 15, 1.0, 1.0, 1.0, 0.2);
            }
        }
        wiw.clear();

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
