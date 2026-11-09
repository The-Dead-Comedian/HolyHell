package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LightBeamEntity extends Entity {
    AllSeerEntity creator;

    public LightBeamEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public LightBeamEntity(EntityType<? extends Entity> entityType, Level level, AllSeerEntity creator) {
        super(entityType, level);
        this.creator = creator;
        this.noPhysics = false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }


    @Override
    public void tick() {
        super.tick();

        this.setPos(this.getX() + this.getDeltaMovement().x,
                this.getY() + this.getDeltaMovement().y,
                this.getZ() + this.getDeltaMovement().z);

        if (this.level().isClientSide) return;

        if (this.tickCount > 100) {
            this.discard();
            return;
        }


        AABB aabb = new AABB(this.getX() - 1, this.getY() - 1, this.getZ() - 1, this.getX() + 1, this.getY() + 7, this.getZ() + 1);
        List<Entity> list = this.level().getEntities(this, aabb, (entity) -> !(entity instanceof AllSeerEntity));

        for (Entity entity : list) {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getBoundingBox().intersects(this.getBoundingBox())) {
                    livingEntity.hurt(this.level().damageSources().indirectMagic(this, creator), 10);
                    this.discard();
                }
            }
        }

    }
}
