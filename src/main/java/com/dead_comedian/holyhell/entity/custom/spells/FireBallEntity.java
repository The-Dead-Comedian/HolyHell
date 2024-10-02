package com.dead_comedian.holyhell.entity.custom.spells;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FireBallEntity extends PersistentProjectileEntity {


    public FireBallEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }



    @Override
    public boolean hasNoGravity() {
        return false;
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);
        player.damage(player.getWorld().getDamageSources().playerAttack(player), 10.0F);
        this.discard();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    public void setVelocity(Direction horizontalFacing) {
    }
}
