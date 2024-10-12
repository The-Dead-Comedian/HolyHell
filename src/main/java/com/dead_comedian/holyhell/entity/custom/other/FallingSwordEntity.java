package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FallingSwordEntity extends PersistentProjectileEntity {

    private int ticksLeft;
    public FallingSwordEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.ticksLeft =150;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);


    }
    @Override
    public void tick() {
        super.tick();
        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }


}
