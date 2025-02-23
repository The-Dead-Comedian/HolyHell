package com.dead_comedian.holyhell.entity.non_living;


import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class SwordCrossEntity extends Entity {

    int cooldown;

    public SwordCrossEntity(EntityType<? extends AbstractArrow> entityType, double d, double e, double f, Level world) {
        super(entityType, world);
        this.setPosRaw(d, e, f);
        this.cooldown =0;
    }

    public SwordCrossEntity(EntityType<SwordCrossEntity> fireBallEntityEntityType, Level world) {
        super(fireBallEntityEntityType, world);
    }


    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox());
        for (Entity i : entityBelow) {
            if (this.canCollideWith(i)) {
                i.hurt(i.level().damageSources().magic(), 5.0F);

            }
        }

        if(++this.cooldown > 30){
            this.discard();
        }


    }


    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof LivingEntity;
    }



    public boolean canCollideWith(Entity other) {
        return canCollide(this, other);
    }

}