package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.block.FallingBlock.canFallThrough;

public class FallingCrossBlockEntity extends BlockEntity {


    public FallingCrossBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY, pos, state);
    }

    public List<Entity> getEntitiesOnBlock(World world, BlockPos pos) {
        return world.getOtherEntities(null, new Box(pos).expand(1, 20, 1).offset(0, -21, 0));
    }



    public void tick(World world, BlockPos pos, BlockState state) {



        for (Entity entity : getEntitiesOnBlock(world, pos)) {
            if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
                FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
                this.configureFallingBlockEntity(fallingBlockEntity);
            }
        }
    }



    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(6.0F, 40);
    }


}
