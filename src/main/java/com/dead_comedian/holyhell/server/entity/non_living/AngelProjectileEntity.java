package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class AngelProjectileEntity extends Projectile {

    private boolean hasBounced = false;

    @Nullable
    private Entity finalTarget;

    @Nullable
    private UUID targetId;

    private float sineTime = 0f;
    private static final float SPEED = 0.2f;
    private static final float TURN_RATE = 0.15f;
    private static final float WAVE_STRENGTH = 0.4f;
    private static final float WAVE_FREQUENCY = 0.4f;
    private static final float OVERSHOOT_FACTOR = 1.2f;

    public AngelProjectileEntity(EntityType<AngelProjectileEntity> type, Level level) {
        super(type, level);
    }

    public AngelProjectileEntity(Level level, LivingEntity shooter, Entity target) {
        super(HolyHellEntities.ANGEL_PROJECTILE.get(), level);
        this.setOwner(shooter);

        Vec3 center = shooter.getBoundingBox().getCenter();
        this.moveTo(center.x, center.y, center.z, shooter.getYRot(), shooter.getXRot());

        this.finalTarget = target;
        this.targetId = target.getUUID();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (finalTarget != null) {
            tag.putUUID("Target", finalTarget.getUUID());
        }

        tag.putFloat("SineTime", sineTime);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.hasUUID("Target")) {
            this.targetId = tag.getUUID("Target");
        }

        this.sineTime = tag.getFloat("SineTime");
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        double d0 = packet.getXa();
        double d1 = packet.getYa();
        double d2 = packet.getZa();
        this.setDeltaMovement(d0, d1, d2);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return entity instanceof AngelProjectileEntity;
    }

    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }


    protected void explode(double power) {


        if (!this.level().isClientSide) {


            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) (3 * power), Level.ExplosionInteraction.MOB);

            this.discard();

        }

    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!this.level().isClientSide) {
            this.playSound(SoundEvents.SHULKER_BULLET_HURT, 1.0F, 1.0F);
            ((ServerLevel) this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2, 0.2, 0.2, 0.0);
            this.destroy();
        }

        return true;
    }

    private void destroy() {
        this.discard();
        this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }

    @Override
    public void tick() {

        super.tick();

        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(0.1, 0.1, 0.1));
        for (Entity i : entityBelow) {
            if (i instanceof AngelProjectileEntity) {
                if (this.canCollideWith(i)) {
                    this.explode(0.5d);
                    if (this.level().isClientSide) {
                        this.level().addParticle(
                                HolyHellParticles.KAMIKAZE_EXPLOSION.get(),
                                this.getX(), this.getY(), this.getZ(),
                                0, 0, 0
                        );
                    }
                    this.discard();
                    i.discard();
                }
            }
        }

        if (!this.level().isClientSide) {

            // Restore target from UUID
            if (this.finalTarget == null && this.targetId != null) {
                this.finalTarget = ((ServerLevel) this.level()).getEntity(this.targetId);
                if (this.finalTarget == null) {
                    this.targetId = null;
                }
            }

            if (this.finalTarget != null && this.finalTarget.isAlive()
                    && !(this.finalTarget instanceof Player player && player.isSpectator())) {

                Vec3 currentPos = this.position();
                Vec3 targetPos = this.finalTarget.position()
                        .add(0, this.finalTarget.getBbHeight() * 0.5, 0);

                Vec3 toTarget = targetPos.subtract(currentPos);
                double distance = toTarget.length();

                if (distance > 0.001) {

                    Vec3 forward = toTarget.normalize();

                    // ---- Overshoot ----
                    Vec3 overshootTarget = targetPos.add(forward.scale(OVERSHOOT_FACTOR));
                    Vec3 desiredDirection = overshootTarget.subtract(currentPos).normalize();

                    // ---- Sine wave offset ----
                    sineTime += WAVE_FREQUENCY;

                    Vec3 perpendicular = desiredDirection.cross(new Vec3(0, 0, 0));
                    if (perpendicular.lengthSqr() < 0.01) {
                        perpendicular = desiredDirection.cross(new Vec3(0, 0, 0));
                    }

                    perpendicular = perpendicular.normalize();

                    double sineOffset = Math.sin(sineTime) * WAVE_STRENGTH;

                    Vec3 finalDirection = desiredDirection
                            .add(perpendicular.scale(sineOffset))
                            .normalize();

                    Vec3 desiredVelocity = finalDirection.scale(SPEED);

                    Vec3 newVelocity = this.getDeltaMovement()
                            .lerp(desiredVelocity, TURN_RATE);

                    this.setDeltaMovement(newVelocity);
                }

            } else {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
            }

            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS
                    && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitResult)) {
                this.hitTargetOrDeflectSelf(hitResult);
            }
        }

        this.checkInsideBlocks();

        Vec3 motion = this.getDeltaMovement();
        this.setPos(this.getX() + motion.x, this.getY() + motion.y, this.getZ() + motion.z);
        ProjectileUtil.rotateTowardsMovement(this, 0.5F);

        if (this.level().isClientSide) {
            this.level().addParticle(
                    HolyHellParticles.FIREBALL_TRAIL.get(),
                    this.getX(), this.getY(), this.getZ(),
                    0, 0, 0
            );
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();
        if (!(entity instanceof Monster)) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10F);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {

        if (!hasBounced) {
            this.setDeltaMovement(
                    this.getDeltaMovement()
                            .reverse()
                            .scale(0.7)
            );
            hasBounced = true;
        } else {
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particle = HolyHellParticles.FIREBALL_IMPACT.get();
            this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }
}