package com.dead_comedian.holyhell.entity.custom;



import com.dead_comedian.holyhell.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class LastPrayerEntity extends Entity {

    private boolean startedAttack;
    private int ticksLeft;
    private int warmup = 0;

    public LastPrayerEntity(EntityType<? extends LastPrayerEntity> entityType, World world) {
        super(entityType, world);
        this.ticksLeft =22;
    }

    public LastPrayerEntity(World world, double x, double y, double z, float yaw) {
        this((EntityType<? extends LastPrayerEntity>) ModEntities.LASTPRAYER, world);
     
        this.setYaw(yaw * 57.295776F);
        this.setPosition(x, y, z);
    }

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(LastPrayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);



    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }


    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.warmup == 0) {
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox().expand(0.2, 0.0, 0.2));
            Iterator var15 = list.iterator();

            while(var15.hasNext()) {
                LivingEntity livingEntity = (LivingEntity)var15.next();
                this.damage(livingEntity);
            }
        }

        if (!this.startedAttack) {
            this.getWorld().sendEntityStatus(this, (byte)4);
            this.startedAttack = true;
        }

        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    private void damage(LivingEntity target) {

        if (target.isAlive() && !target.isInvulnerable()) {


                target.damage(this.getDamageSources().generic(), 6.0F);
            }

        }
    }








