package com.dead_comedian.holyhell.entity.custom.other;

import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.world.World;

import java.util.List;

public class TrappedStoneCrossEntity extends PersistentProjectileEntity {


    boolean playerDetected = false;

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

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.damage(this.getDamageSources().fallingBlock(this), 15);
        this.discard();
        ((PlayerEntity)target).giveItemStack(new ItemStack(HolyHellItems.TRAPPED_STONE_CROSS,1));
    }




    public void detectPlayer() {
        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0, 50, 0).offset(0, -51, 0));

        for (Entity i : entityBelow) {
            if (i instanceof PlayerEntity && !i.isSneaking() && i.collidesWith(this)) {
                playerDetected = true;
            }

        }

    }


    @Override
    public void tick() {
        super.tick();
        this.detectPlayer();
        if (!playerDetected) {
            setVelocity(0, 0, 0);
        }else {
            this.setVelocity(0, -1, 0);
        }


    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }


}
