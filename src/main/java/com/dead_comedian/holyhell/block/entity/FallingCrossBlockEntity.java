package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;

import static net.minecraft.world.level.block.FallingBlock.isFree;

public class FallingCrossBlockEntity extends BlockEntity {


    public FallingCrossBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY, pos, state);
    }

    public List<Entity> getEntitiesOnBlock(Level world, BlockPos pos) {
        return world.getEntities(null, new AABB(pos).inflate(1, 20, 1).move(0, -21, 0));
    }



    public void tick(Level world, BlockPos pos, BlockState state) {



        for (Entity entity : getEntitiesOnBlock(world, pos)) {
            if (isFree(world.getBlockState(pos.below())) && pos.getY() >= world.getMinBuildHeight()) {
                FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(world, pos, state);
                this.configureFallingBlockEntity(fallingBlockEntity);

            }
        }
    }



    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtsEntities(6.0F, 40);
    }


}
