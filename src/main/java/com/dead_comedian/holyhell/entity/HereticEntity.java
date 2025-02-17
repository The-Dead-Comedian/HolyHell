package com.dead_comedian.holyhell.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HereticEntity extends Monster {


    ///////////////
    // VARIABLES //
    ///////////////



    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    int tick1;

    //////////
    // MISC //
    //////////


    public HereticEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 10;
    }

    public void safetyMeasure() {


        if (HereticAttackGoal.render) {

            tick1++;

            if (tick1 == 20) {
                tick1 = 0;
                HereticAttackGoal.render = false;


            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        safetyMeasure();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new HereticAttackGoal(this, 1f, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createHereticAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.ARMOR, 1.5f)
                .add(Attributes.ATTACK_DAMAGE, 8);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);

    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {


        if (this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 30;
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


    public class HereticAttackGoal extends MeleeAttackGoal {


        private final HereticEntity entity;
        private int attackDelay = 15;
        private int ticksUntilNextAttack = 15;
        private boolean shouldCountTillNextAttack = false;
        public static boolean render = false;

        public HereticAttackGoal(PathfinderMob mob, double speed, boolean pauseWhenMobIdle) {
            super(mob, speed, pauseWhenMobIdle);
            entity = ((HereticEntity) mob);
        }

        @Override
        public void start() {
            super.start();
            tick1 = 0;
            attackDelay = 15;
            ticksUntilNextAttack = 15;
        }


        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
                shouldCountTillNextAttack = true;


                if (isTimeToStartAttackAnimation()) {
                    entity.setAggressive(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    render = true;

                    if (!pEnemy.isBlocking()) {
                        pEnemy.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 99));
                    }else {
                        this.entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 99));
                    }

                    performAttack(pEnemy);
                }
            } else {
                render = false;

                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAggressive(false);
                entity.attackAnimationTimeout = 0;
            }
        }



        public static boolean shouldRender() {
            return render;
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
        }


        protected boolean isTimeToStartAttackAnimation() {
            return this.ticksUntilNextAttack <= attackDelay;
        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected void performAttack(LivingEntity pEnemy) {

            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
        }

        @Override
        public void tick() {
            super.tick();

            safetyMeasure();
            if (shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                if (ticksUntilNextAttack == 0) {
                    entity.setAggressive(false);
                }
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
        if (bl) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setSecondsOnFire(2 * (int) f);
            }
        }
        setAggressive(true);
        return bl;
    }


}