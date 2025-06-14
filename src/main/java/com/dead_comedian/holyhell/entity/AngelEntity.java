package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.entity.non_living.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AngelEntity extends Monster implements RangedAttackMob {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    //////////
    // MISC //
    //////////

    public AngelEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }


    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        if (pSource.getEntity() instanceof KamikazeEntity) {
            return true;
        } else {
            return super.isInvulnerableTo(pSource);
        }

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


        this.goalSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAngelAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 && this.getPose() == Pose.STANDING) {
            this.idleAnimationTimeout = this.random.nextInt(30) + 60;

            this.idleAnimationState.start(this.tickCount);

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

        this.level().playSound(this, this.blockPosition(), HolyHellSound.ANGEL_SHOOT.get(), SoundSource.HOSTILE, 1f, 1f);

        this.level().addFreshEntity(fireBallEntity);
    }

    ///////////
    // SOUND //
    ///////////


    protected SoundEvent getStepSound() {
        return HolyHellSound.ANGEL_FLUTTER.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return HolyHellSound.ANGEL_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return HolyHellSound.ANGEL_HURT.get();
    }

    ///////////////
    // SUMMONING //
    ///////////////


    public class RangedAttackGoal extends Goal {
        private final Mob mob;
        private final AngelEntity rangedAttackMob;
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

        private final float maxDistanceToTarget;

        private int circleTimer = 0;
        private int switchDirectionTimer = 0;
        private final int switchDirectionInterval;


        public RangedAttackGoal(AngelEntity pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius, float circlingDistance, float minDistanceToTarget, float maxDistanceToTarget, int switchDirectionInterval) {
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
                this.maxDistanceToTarget = maxDistanceToTarget;
                this.switchDirectionInterval = switchDirectionInterval;


            }
        }

        public boolean canUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            } else {
                return false;
            }
        }


        public boolean canContinueToUse() {

            return this.canUse() || this.target.isAlive() && !this.mob.getNavigation().isDone();
        }


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
            if (--this.attackTime == 0 && target != null) {
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


            this.circleTimer++;


            if (this.switchDirectionInterval >= 0) {
                this.switchDirectionTimer++;
                if (this.switchDirectionTimer >= this.switchDirectionInterval) {

                    this.switchDirectionTimer = 0;
                }
            }

            Vec3 targetPos = this.target.position();
            Vec3 mobPos = this.mob.position();

            Vec3 toMob = mobPos.subtract(targetPos);
            double distanceToTarget = toMob.length();

            Vec3 targetLookVec = new Vec3(
                    Math.sin(-this.target.getYRot() * ((float) Math.PI / 180F)) * Math.cos(this.target.getXRot() * ((float) Math.PI / 180F)),
                    0,
                    Math.cos(-this.target.getYRot() * ((float) Math.PI / 180F)) * Math.cos(this.target.getXRot() * ((float) Math.PI / 180F))
            ).normalize();

            Vec3 behindPos = targetPos.subtract(targetLookVec.scale(this.circlingDistance));

            Vec3 movePos;


            if (distanceToTarget > this.maxDistanceToTarget) {
                movePos = targetPos.add(
                        toMob.normalize()
                                .scale(this.circlingDistance)
                                .subtract(targetLookVec.scale(this.circlingDistance * 0.5))
                );
            } else {
                Vec3 perpendicular = new Vec3(toMob.z, 0, -toMob.x).normalize();
                float circleOffsetAngle = (float) Math.PI * (this.circleTimer % 60) / 30.0f;
                float offsetFactor = (float) Math.sin(circleOffsetAngle) * this.circlingDistance * 0.5f;
                movePos = behindPos.add(perpendicular.scale(offsetFactor));
            }
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            this.mob.getNavigation().moveTo(movePos.x, movePos.y, movePos.z, this.speedModifier);
        }
    }

}