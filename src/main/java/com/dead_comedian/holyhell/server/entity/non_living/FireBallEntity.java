package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyhellParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireBallEntity extends ThrowableProjectile {
    private int counter = 5;
    private boolean hasBounced = false;

    public FireBallEntity(EntityType<FireBallEntity> fireBallEntityEntityType, Level level) {
        super(HolyHellEntities.FIREBALL.get(), level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        counter--;

        if (counter <= 0) {
            this.level().addParticle(HolyhellParticles.FIREBALL_TRAIL.get(), this.getBlockX(), this.getBlockY(), this.getBlockZ(), 0, 0, 0);
            counter = 5;
        }

        super.tick();
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = HolyhellParticles.FIREBALL_IMPACT.get();
            this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (!(entity instanceof Monster)) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10F);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {


        if (!hasBounced) {
            this.addDeltaMovement(this.getDeltaMovement().reverse().offsetRandom(this.level().getRandom(), 0.5F));
            hasBounced = true;
        } else {
            this.discard();
        }
    }
}