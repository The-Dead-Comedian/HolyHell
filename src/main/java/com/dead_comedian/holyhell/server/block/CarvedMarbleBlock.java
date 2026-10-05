package com.dead_comedian.holyhell.server.block;

import com.dead_comedian.holyhell.server.block.entity.CarvedMarbleBlockEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CarvedMarbleBlock extends BaseEntityBlock {


    protected CarvedMarbleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    protected VoxelShape getVisualShape(BlockState p_309057_, BlockGetter p_308936_, BlockPos p_308956_, CollisionContext p_309006_) {
        return Shapes.empty();
    }

    protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
        return 1.0F;
    }

    protected boolean propagatesSkylightDown(BlockState p_309084_, BlockGetter p_309133_, BlockPos p_309097_) {
        return true;
    }


    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CarvedMarbleBlockEntity carvedMarbleBlockEntity) {

            if (stack.isEmpty()) {
                dropLoot(carvedMarbleBlockEntity, level, pos, player);
                carvedMarbleBlockEntity.setStoredState(null);
                return ItemInteractionResult.SUCCESS;
            }
            if (stack.is(HolyHellTags.Items.GLASS)) {
                dropLoot(carvedMarbleBlockEntity, level, pos, player);
                carvedMarbleBlockEntity.setStoredState(Block.byItem(stack.getItem()).defaultBlockState());
                stack.consume(1, player);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CarvedMarbleBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.getBlockEntity(pos) instanceof CarvedMarbleBlockEntity carvedMarbleBlockEntity &&
                carvedMarbleBlockEntity.blockState != null &&
                !player.hasCorrectToolForDrops(state, level, pos)) {
            dropLoot(carvedMarbleBlockEntity, level, pos, player);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    public void dropLoot(CarvedMarbleBlockEntity entity, Level level, BlockPos pos, Player player) {
        if (entity.blockState != null && !player.isCreative()) {
            DefaultDispenseItemBehavior.spawnItem(
                    level,
                    entity.blockState.getBlock().asItem().getDefaultInstance(),
                    0,
                    Direction.UP,
                    Vec3.atBottomCenterOf(pos).add(-0.5, 0.5, -0.5)
            );
        }
    }
}
