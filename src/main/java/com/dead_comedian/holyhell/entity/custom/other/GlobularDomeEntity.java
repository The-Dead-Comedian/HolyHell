package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class GlobularDomeEntity extends Entity {
    private int ticksLeft;
    PlayerEntity user;


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



    private void launchLivingEntities(List<Entity> entities) {
        double d = (this.getBoundingBox().minX + this.getBoundingBox().maxX) / 2.0;
        double e = (this.getBoundingBox().minZ + this.getBoundingBox().maxZ) / 2.0;
        Iterator var6 = entities.iterator();

        while(var6.hasNext()) {
            Entity entity = (Entity)var6.next();
            if (entity instanceof LivingEntity) {
                double f = entity.getX() - d;
                double g = entity.getZ() - e;
                double h = Math.max(f * f + g * g, 0.1);
                entity.addVelocity(f / h * 4.0, 0.20000000298023224, g / h * 4.0);
            }
        }

    }
}
