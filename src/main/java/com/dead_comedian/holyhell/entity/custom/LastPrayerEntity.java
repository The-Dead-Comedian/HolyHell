package com.dead_comedian.holyhell.entity.custom;



import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class LastPrayerEntity extends Entity {

    private boolean startedAttack;
    private int ticksleft;

    public LastPrayerEntity(EntityType<? extends LastPrayerEntity> entityType, World world) {
        super(entityType, world);
        this.ticksleft =22;
    }

    public LastPrayerEntity(World world, double x , double y, double z, float yaw){

        this.setYaw(yaw* 57.295776F);
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





        }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
    }




