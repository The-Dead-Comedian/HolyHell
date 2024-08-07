package com.dead_comedian.holyhell.item.custom;


import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class HolyGrailItem extends Item {
    private final EntityType<?> entityType;
    public HolyGrailItem(EntityType<?> type, Item.Settings settings ) {
        super(settings);
        this.entityType = type;

    }


    private void spawnEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.spawnFromItemStack(world, stack, (PlayerEntity)null, pos, SpawnReason.SPAWN_EGG, true, false);
    }

    public ActionResult castSpell(World world, ItemStack itemStack, BlockPos blockPos,Direction direction, PlayerEntity player) {
        float a = player.getPitch();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        } else {



            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.SPAWNER)) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof MobSpawnerBlockEntity) {
                    MobSpawnerBlockEntity mobSpawnerBlockEntity = (MobSpawnerBlockEntity)blockEntity;
                    EntityType<?> entityType = HolyHellEntities.CHRISTIANCROSS;
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
                if(a == -90.0F){
                    entityType2 = HolyHellEntities.CHRISTIANCROSS;
                blockPos2= player.getBlockPos().up(2);
                } else if ( a == 90.0F ) {
                    entityType2 = HolyHellEntities.AREASPELL;
                } else{
                    entityType2 = HolyHellEntities.ANGEL;
                }
            if (entityType2.spawnFromItemStack((ServerWorld)world, itemStack, player, blockPos2, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockPos, blockPos2) && direction == Direction.UP) != null) {
                itemStack.decrement(1);
                world.emitGameEvent(player, GameEvent.ENTITY_PLACE, blockPos);
            }

            return ActionResult.CONSUME;
        }
    }


//test

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);


        castSpell(world, itemStack, user.getBlockPos(), user.getMovementDirection(), user);



            BlockPos blockPos = blockHitResult.getBlockPos();
            if (!(world.getBlockState(blockPos).getBlock() instanceof FluidBlock)) {
                return TypedActionResult.pass(itemStack);
            } else if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos, blockHitResult.getSide(), itemStack)) {
                EntityType<?> entityType = HolyHellEntities.CHRISTIANCROSS;
                Entity entity = entityType.spawnFromItemStack((ServerWorld) world, itemStack, user, blockPos, SpawnReason.SPAWN_EGG, false, false);
                if (entity == null) {
                    return TypedActionResult.pass(itemStack);
                } else {
                    if (!user.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    world.emitGameEvent(user, GameEvent.ENTITY_PLACE, entity.getPos());
                    return TypedActionResult.consume(itemStack);
                }
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }







}
