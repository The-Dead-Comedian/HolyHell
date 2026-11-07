package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.entity.BabOneEntity;
import com.dead_comedian.holyhell.server.entity.ai.RevenantStates;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.dead_comedian.holyhell.server.registries.HolyHellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
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

public class JudgementSpearEntity extends Projectile {


    private static final EntityDataAccessor<Boolean> STAGNANT = SynchedEntityData.defineId(JudgementSpearEntity.class, EntityDataSerializers.BOOLEAN);

    public JudgementSpearEntity(EntityType<JudgementSpearEntity> judgementSpearEntityEntityType, Level level) {
        super(judgementSpearEntityEntityType, level);
    }

    public boolean getSagnant() {
        return this.entityData.get(STAGNANT);
    }

    public void setStagnant(boolean a) {
        this.entityData.set(STAGNANT, a);
    }

    @Nullable
    private Entity finalTarget;

    @Nullable
    private UUID targetId;

    private int stagnantTicks = 60;
    private int riseTicks = 15;
    private boolean shouldntDiscard = true;

    @Nullable
    public UUID getTargetId() {
        return targetId;
    }

    public void setTargetId(@Nullable UUID targetId) {
        this.targetId = targetId;
    }

    private float sineTime = 0f;
    private static final float SPEED = 1f;
    private static final float TURN_RATE = 0.15f;
    private static final float WAVE_STRENGTH = 0.4f;
    private static final float WAVE_FREQUENCY = 0.4f;
    private static final float OVERSHOOT_FACTOR = 1.2f;
    private AllSeerEntity owner;

    public JudgementSpearEntity(EntityType<JudgementSpearEntity> type, Level level, AllSeerEntity allSeerEntity) {
        super(type, level);
        owner = allSeerEntity;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (finalTarget != null) {
            tag.putUUID("Target", finalTarget.getUUID());
        }

        tag.putInt("StagnantTicks", stagnantTicks);
        tag.putInt("RiseTicks", riseTicks);

        tag.putFloat("SineTime", sineTime);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.hasUUID("Target")) {
            this.targetId = tag.getUUID("Target");
        }

        this.stagnantTicks = tag.getInt("StagnantTicks");
        this.riseTicks = tag.getInt("RiseTicks");

        this.sineTime = tag.getFloat("SineTime");
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

        builder.define(STAGNANT, true);
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
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
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
        if (!this.level().isClientSide) {
            if (stagnantTicks > 0) {
                stagnantTicks--;
                if (stagnantTicks == 0) {
                    this.setDeltaMovement(0, 1.2, 0);
                    this.hasImpulse = true;
                }
            } else if (riseTicks > 0) {
                riseTicks--;
            }
            this.setStagnant(stagnantTicks > 0);
        }

        if (stagnantTicks <= 0 && owner != null && shouldntDiscard) {

            int i = level().random.nextInt(1, 18 - owner.getBrain().getMemory(HolyHellMemoryModules.PHASE.get()).get() * 2);

            if (i != 2) {
                this.discard();
            } else {
                shouldntDiscard = false;
            }
        }

        ProjectileUtil.rotateTowardsMovement(this, 0.7F);

        if (!this.level().isClientSide && !this.getSagnant() && riseTicks == 0) {

            if (this.finalTarget == null && this.targetId != null) {
                this.finalTarget = ((ServerLevel) this.level()).getEntity(this.targetId);
                if (this.finalTarget == null) this.targetId = null;
            }

            if (this.finalTarget != null && this.finalTarget.isAlive()
                    && !(this.finalTarget instanceof Player p && p.isSpectator())) {

                Vec3 currentPos = this.position();
                Vec3 targetPos = this.finalTarget.position()
                        .add(0, this.finalTarget.getBbHeight() * 0.5, 0);

                Vec3 toTarget = targetPos.subtract(currentPos);
                if (toTarget.lengthSqr() > 1.0E-6) {
                    Vec3 forward = toTarget.normalize();
                    Vec3 overshootTarget = targetPos.add(forward.scale(OVERSHOOT_FACTOR));
                    Vec3 desiredDirection = overshootTarget.subtract(currentPos).normalize();

                    sineTime += WAVE_FREQUENCY;

                    Vec3 perpendicular = desiredDirection.cross(new Vec3(0, 1, 0));
                    if (perpendicular.lengthSqr() < 0.01) {
                        perpendicular = desiredDirection.cross(new Vec3(1, 0, 0));
                    }
                    perpendicular = perpendicular.normalize();

                    Vec3 finalDirection = desiredDirection
                            .add(perpendicular.scale(Math.sin(sineTime) * WAVE_STRENGTH))
                            .normalize();

                    Vec3 newVelocity = this.getDeltaMovement()
                            .lerp(finalDirection.scale(SPEED), TURN_RATE);
                    this.setDeltaMovement(newVelocity);
                    this.hasImpulse = true;
                }
            } else {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.5, 0));
            }
        }

        if (!this.level().isClientSide && !this.getSagnant()) {
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS
                    && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitResult)) {
                this.hitTargetOrDeflectSelf(hitResult);
            }
        }

        this.checkInsideBlocks();
        Vec3 dm = this.getDeltaMovement();
        this.setPos(this.getX() + dm.x, this.getY() + dm.y, this.getZ() + dm.z);
        ProjectileUtil.rotateTowardsMovement(this, 1);

        if (this.level().isClientSide) {
            this.level().addParticle(HolyHellParticles.FIREBALL_TRAIL.get(),
                    this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();
        if (!(entity instanceof AllSeerEntity) && !(entity instanceof JudgementSpearEntity)) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10F);
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