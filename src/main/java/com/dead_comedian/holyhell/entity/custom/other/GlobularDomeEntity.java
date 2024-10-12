package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class GlobularDomeEntity extends Entity {
    private int ticksLeft;

    public GlobularDomeEntity(EntityType<?> type, World world) {
        super(type, world);
        this.ticksLeft =200;

    }

    @Override
    public void tick() {
        super.tick();
        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
