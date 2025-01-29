package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.block.FallingBlock.canFallThrough;

public class FallingCrossBlockEntity extends BlockEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final float FALLING_BLOCK_ENTITY_DAMAGE_MULTIPLIER = 2.0F;
    private static final int FALLING_BLOCK_ENTITY_MAX_DAMAGE = 40;


    public FallingCrossBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.FALLING_CROSS_BLOCK_ENTITY, pos, state);
    }

    public List<Entity> getEntitiesOnBlock(World world, BlockPos pos) {
        return world.getOtherEntities(null, new Box(pos).expand(1, 20, 1).offset(0, -21, 0));
    }

    public void tick(World world, BlockPos pos, BlockState state) {


        System.out.println("yay");
        for (Entity entity : getEntitiesOnBlock(world, pos)) {
            if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
                FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
                this.configureFallingBlockEntity(fallingBlockEntity);
            }
        }
    }

    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(1031, pos, 0);
        }

    }

    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            world.syncWorldEvent(1029, pos, 0);
        }

    }

    public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingAnvil(attacker);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(2.0F, 40);
    }


}
