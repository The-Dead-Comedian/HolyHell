package com.dead_comedian.holyhell.server.block.entity;

import com.dead_comedian.holyhell.server.registries.HolyHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;


import java.util.List;


public class FallingSmashingBlockEntity extends BlockEntity {

    public FallingSmashingBlockEntity( BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY.get(), pos, state);
    }
    public FallingSmashingBlockEntity(BlockState state,BlockPos pos) {
        super(HolyHellBlockEntities.CHANDELIER_BLOCK_ENTITY.get(), pos, state);
    }
    public FallingSmashingBlockEntity(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }



    public List<Entity> getEntitiesOnBlock(Level world, BlockPos pos) {
        return world.getEntities(null, new AABB(pos).inflate(1, 20, 1).move(0, -21, 0));
    }


    public void tick(Level world, BlockPos pos, BlockState state) {
        for (Entity entity : getEntitiesOnBlock(world, pos)) {
            if (world.getBlockState(pos.below()).isAir() && pos.getY() >= world.getMinBuildHeight() && entity instanceof LivingEntity) {
                getEntitiesOnBlock(world, pos).removeAll(getEntitiesOnBlock(world, pos));
            }
        }
    }
}
