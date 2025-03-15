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


public class GlobeBlockEntity extends BlockEntity {


    public GlobeBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.GLOBE_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
    }


}
