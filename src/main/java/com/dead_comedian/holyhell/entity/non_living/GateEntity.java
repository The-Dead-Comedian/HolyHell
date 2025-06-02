package com.dead_comedian.holyhell.entity.non_living;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class GateEntity extends Entity {
    private int ticksLeft;

    public GateEntity(EntityType<?> type, Level world) {
        super(type, world);

        this.ticksLeft = 100;


    }

    @Override
    protected void defineSynchedData() {

    }


    @Override
    public void tick() {
        super.tick();
        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }


}