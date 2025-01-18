package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import com.dead_comedian.holyhell.entity.custom.other.GlobularDomeEntity;
import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
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

    String owner;


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


        BlockPos blockPos = user.getBlockPos();
        GlobularDomeEntity globularDomeEntity = new GlobularDomeEntity(HolyHellEntities.GLOBULAR_DOME, user.getWorld());
        user.getWorld().spawnEntity(globularDomeEntity);
        globularDomeEntity.refreshPositionAndAngles(blockPos, globularDomeEntity.getYaw(), globularDomeEntity.getPitch());
        globularDomeEntity.setUser(owner);
        user.getItemCooldownManager().set(this, 200);

        if (!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
        }

        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
