package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.entity.non_living.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AngelEntity extends Monster implements RangedAttackMob {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;


    //////////
    // MISC //
    //////////

    public AngelEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);

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


        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1, 20, 15.0F,7,10,20,900));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAngelAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 0.8f)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.tickCount);


        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
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
    ////////


    //  attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.BOOLEAN);

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
        if (bl) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setSecondsOnFire(2 * (int) f);
            }
        }
        setAggressive(true);
        return bl;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {


        Vec3 look = this.getLookAngle();
        double d0 = target.getX() - this.getX();
        double d2 = target.getZ() - this.getZ();

        FireBallEntity fireBallEntity = new FireBallEntity(HolyHellEntities.FIREBALL.get(), this.getX(), this.getY() + 1, this.getZ(), this.level());
        fireBallEntity.shoot(d0, look.y, d2, 2.0F, 1);
        this.level().addFreshEntity(fireBallEntity);

    }


    ///////////////
    // SUMMONING //
    ///////////////


    public class RangedAttackGoal extends Goal {
        private final Mob mob;
        private final RangedAttackMob rangedAttackMob;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final int attackIntervalMin;
        private final int attackIntervalMax;
        private final float attackRadius;
        private final float attackRadiusSqr;


        private final float circlingDistance;
        private final float minDistanceToTarget;
        private final float maxDistanceToTarget;

        private int circleTimer = 0;
        private int switchDirectionTimer = 0;
        private final int switchDirectionInterval;


        public RangedAttackGoal(RangedAttackMob pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius, float circlingDistance, float minDistanceToTarget, float maxDistanceToTarget, int switchDirectionInterval) {
            if (!(pRangedAttackMob instanceof LivingEntity)) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.rangedAttackMob = pRangedAttackMob;
                this.mob = (Mob) pRangedAttackMob;
                this.speedModifier = pSpeedModifier;
                this.attackIntervalMin = pAttackInterval;
                this.attackIntervalMax = pAttackInterval;
                this.attackRadius = pAttackRadius;
                this.attackRadiusSqr = pAttackRadius * pAttackRadius;
                this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
                this.circlingDistance = circlingDistance;
                this.minDistanceToTarget = minDistanceToTarget;
                this.maxDistanceToTarget = maxDistanceToTarget;
                this.switchDirectionInterval = switchDirectionInterval;


            }
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {

            return this.canUse() || this.target.isAlive() && !this.mob.getNavigation().isDone();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void start() {
            this.circleTimer = 0;
            this.switchDirectionTimer = 0;
            super.start();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }

            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            if (--this.attackTime == 0) {
                if (!flag) {
                    return;
                }

                float f = (float) Math.sqrt(d0) / this.attackRadius;
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                this.rangedAttackMob.performRangedAttack(this.target, f1);
                this.attackTime = Mth.floor(f * (float) (this.attackIntervalMax - this.attackIntervalMin) + (float) this.attackIntervalMin);
            } else if (this.attackTime < 0) {
                this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double) this.attackRadius, (double) this.attackIntervalMin, (double) this.attackIntervalMax));
            }


            //Movement


            this.circleTimer++;


            if (this.switchDirectionInterval >= 0) {
                this.switchDirectionTimer++;
                if (this.switchDirectionTimer >= this.switchDirectionInterval) {

                    this.switchDirectionTimer = 0;
                }
            }

            // Get position information
            Vec3 targetPos = this.target.position();
            Vec3 mobPos = this.mob.position();

            // Calculate vector from target to mob
            Vec3 toMob = mobPos.subtract(targetPos);
            double distanceToTarget = toMob.length();

            // Get target's looking direction (as a 2D vector in the X-Z plane)
            Vec3 targetLookVec = new Vec3(
                    Math.sin(-this.target.getYRot() * ((float) Math.PI / 180F)) * Math.cos(this.target.getXRot() * ((float) Math.PI / 180F)),
                    0,
                    Math.cos(-this.target.getYRot() * ((float) Math.PI / 180F)) * Math.cos(this.target.getXRot() * ((float) Math.PI / 180F))
            ).normalize();

            // Calculate behind position (directly behind the target)
            Vec3 behindPos = targetPos.subtract(targetLookVec.scale(this.circlingDistance));

            // Calculate movement position based on current state
            Vec3 movePos;

//            if (distanceToTarget < this.minDistanceToTarget) {
//                // Too close, move directly away from target
//             movePos = mobPos.add(toMob.normalize().scale(2.0));
//            }
            if (distanceToTarget > this.maxDistanceToTarget) {
                // Too far, move closer but still try to get behind
                movePos = targetPos.add(
                        toMob.normalize()
                                .scale(this.circlingDistance)
                                .subtract(targetLookVec.scale(this.circlingDistance * 0.5))
                );
            } else {
                // Within good range, circle around to get behind

                // Get perpendicular vector for circle movement (in X-Z plane)
                Vec3 perpendicular = new Vec3(toMob.z, 0, -toMob.x).normalize();

                // Calculate offset from behind position based on angle
                float circleOffsetAngle = (float) Math.PI * (this.circleTimer % 60) / 30.0f;
                float offsetFactor = (float) Math.sin(circleOffsetAngle) * this.circlingDistance * 0.5f;

                // Calculate circle point
                movePos = behindPos.add(perpendicular.scale(offsetFactor));
            }

            // Look at target
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            // Move to calculated position
            this.mob.getNavigation().moveTo(movePos.x, movePos.y, movePos.z, this.speedModifier);
        }
    }

}