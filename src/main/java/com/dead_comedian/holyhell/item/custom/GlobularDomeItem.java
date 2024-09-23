package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class GlobularDomeItem extends Item {
    public GlobularDomeItem(Settings settings) {
        super(settings);
    }


    public ActionResult castSpell(World world, ItemStack itemStack, BlockPos blockPos, Direction direction, PlayerEntity player) {

        player.stopUsingItem();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        } else {



            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.SPAWNER)) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof MobSpawnerBlockEntity) {
                    MobSpawnerBlockEntity mobSpawnerBlockEntity = (MobSpawnerBlockEntity)blockEntity;
                    EntityType<?> entityType = HolyHellEntities.GLOBULAR_DOME;
                    mobSpawnerBlockEntity.setEntityType(entityType, world.getRandom());
                    blockEntity.markDirty();
                    world.updateListeners(blockPos, blockState, blockState, 3);
                    world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                    itemStack.decrement(1);
                    return ActionResult.CONSUME;
                }
            }

            BlockPos blockPos2;
            if (blockState.getCollisionShape(world, blockPos).isEmpty()) {
                blockPos2 = blockPos;
            } else {
                blockPos2 = blockPos.offset(direction);
            }
            EntityType<?> entityType2;

                entityType2 = HolyHellEntities.GLOBULAR_DOME;



            if (entityType2.spawnFromItemStack((ServerWorld)world, itemStack, player, blockPos2, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockPos, blockPos2) && direction == Direction.UP) != null) {
                itemStack.decrement(1);
                world.emitGameEvent(player, GameEvent.ENTITY_PLACE, blockPos);
            }

            return ActionResult.CONSUME;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        castSpell(world, user.getStackInHand(hand), user.getBlockPos(), user.getMovementDirection(),user);
        return super.use(world, user, hand);
    }
}
