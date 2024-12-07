package com.dead_comedian.holyhell.entity.custom.other;

import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import com.dead_comedian.holyhell.entity.custom.BabTwoEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class SwordCrossEntity extends PersistentProjectileEntity {

    int cooldown;

    public SwordCrossEntity(EntityType<? extends PersistentProjectileEntity> entityType, double d, double e, double f, World world) {
        super(entityType, world);
        this.setPos(d, e, f);
        this.cooldown =0;
    }

    public SwordCrossEntity(EntityType<SwordCrossEntity> fireBallEntityEntityType, World world) {
        super(fireBallEntityEntityType, world);
    }


    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

    }


    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox());
        for (Entity i : entityBelow) {
            if (this.collidesWith(i)) {
                i.damage(i.getWorld().getDamageSources().magic(), 5.0F);

            }
        }

        if(++this.cooldown > 30){
            this.discard();
        }


    }


    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof LivingEntity;
    }



    public boolean collidesWith(Entity other) {
        return canCollide(this, other);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }


}
