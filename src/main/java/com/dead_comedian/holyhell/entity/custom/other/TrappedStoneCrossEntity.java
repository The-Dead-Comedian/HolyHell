package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class TrappedStoneCrossEntity extends PersistentProjectileEntity {


    public TrappedStoneCrossEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);

    }


    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);
    }


    public void detectPlayer() {
        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(1, 50, 1).offset(0, -50, 0));


        for (Entity i : entityBelow) {
            if (i instanceof PlayerEntity && !i.isSneaking() && i.collidesWith(this)) {
                System.out.println("works");
                this.setNoGravity(false);
            }else {
                System.out.println("no works");
                this.setNoGravity(true);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.detectPlayer();


    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }


}
