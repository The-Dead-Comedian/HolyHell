package com.dead_comedian.holyhell.entity;

import com.dead_comedian.holyhell.registries.HolyHellSounds;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class HereticEntity extends Monster {


    ///////////////
    // VARIABLES //
    /// ////////////


    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    int tick1;

    //////////
    // MISC //

    /// ///////
    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        if (pSource.getEntity() instanceof KamikazeEntity) {
            return true;
        } else {
            return super.isInvulnerableTo(pSource);
        }

    }

    public HereticEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 10;
    }


    @Override
    public void tick() {
        super.tick();


        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AttackGoal(this, 1f, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .add(Attributes.ARMOR, 1.7f)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.FOLLOW_RANGE, 10);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }


    ///////////////
    // ANIMATION //

    /// ////////////


    private void setupAnimationStates() {


        if (this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 45;
            attackAnimationState.startIfStopped(this.tickCount);


        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAggressive()) {
            attackAnimationState.stop();
        }


    }

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f);
    }


    ////////
    // AI //

    /// /////


    public static class AttackGoal extends MeleeAttackGoal {
        private final HereticEntity entity;
        private int attackDelay = 20;
        private int ticksUntilNextAttack = 20;
        private boolean shouldCountTillNextAttack = false;

        public AttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            entity = ((HereticEntity) pMob);
        }

        @Override
        public void start() {
            super.start();
            attackDelay = 20;
            ticksUntilNextAttack = 20;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy) {
            if (isEnemyWithinAttackDistance(pEnemy, entity.distanceToSqr(pEnemy))) {
                shouldCountTillNextAttack = true;

                if (isTimeToStartAttackAnimation()) {
                    entity.setAggressive(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());

                    if (!pEnemy.isBlocking()) {
                        this.mob.playSound(HolyHellSounds.STUN.get(),1F,1F);
                        pEnemy.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 255));
                        if (pEnemy.level() instanceof ServerLevel world) {
                            world.sendParticles(HolyhellParticles.STUN.get(),
                                    pEnemy.getX(),
                                    pEnemy.getEyeY() + 0.3F,
                                    pEnemy.getZ() - 0.5,
                                    1, 0, 0, 0, 0);

                            world.sendParticles(HolyhellParticles.STUN2.get(),
                                    pEnemy.getX(),
                                    pEnemy.getEyeY() + 0.3F,
                                    pEnemy.getZ() + 0.5,
                                    1, 0, 0, 0, 0);
                        }
                    }
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAggressive(false);
                entity.attackAnimationTimeout = 0;
            }
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
        }
        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + pAttackTarget.getBbWidth());
        }
        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected boolean isTimeToStartAttackAnimation() {
            return this.ticksUntilNextAttack <= attackDelay;
        }

        protected int getTicksUntilNextAttack() {
            return this.ticksUntilNextAttack;
        }


        protected void performAttack(LivingEntity pEnemy) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
        }

        @Override
        public void tick() {
            super.tick();
            if (shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            }
        }


        @Override
        public void stop() {
            entity.setAggressive(false);
            super.stop();
        }
    }


// attacking

private static final EntityDataAccessor<Boolean> ATTACKING =
        SynchedEntityData.defineId(HereticEntity.class, EntityDataSerializers.BOOLEAN);

public void setAggressive(boolean attacking) {
    this.entityData.set(ATTACKING, attacking);
}

@Override
public boolean isAggressive() {
    return this.entityData.get(ATTACKING);
}

@Override
public boolean doHurtTarget(Entity target) {
    boolean bl = super.doHurtTarget(target);
    this.playSound(HolyHellSounds.HERETIC_ATTACK.get(), 1F, 1F);
    setAggressive(true);
    return bl;
}

//////////
//SOUNDS//

/// ///////


@Override
protected SoundEvent getDeathSound() {
    return HolyHellSounds.HERETIC_DEATH.get();
}

@org.jetbrains.annotations.Nullable
@Override
protected SoundEvent getAmbientSound() {
    return HolyHellSounds.HERETIC_IDLE.get();
}

@Override
protected SoundEvent getHurtSound(DamageSource pDamageSource) {
    return HolyHellSounds.HERETIC_HURT.get();
}


}