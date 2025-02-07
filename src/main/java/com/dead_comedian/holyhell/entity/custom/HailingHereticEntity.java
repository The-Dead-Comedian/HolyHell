package com.dead_comedian.holyhell.entity.custom;

import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HailingHereticEntity extends HostileEntity {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    int tick1;

    //////////
    // MISC //
    //////////


    public HailingHereticEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 10;
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
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
            this.goalSelector.add(1, new HereticAttackGoal(this, 1f, true));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createHereticAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 25)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ARMOR, 1.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);

    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {


        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 30;
            attackAnimationState.startIfNotRunning(this.age);


        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }


    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }


    ////////
    // AI //
    ////////


    public class HereticAttackGoal extends MeleeAttackGoal {


        private final HailingHereticEntity entity;
        private int attackDelay = 15;
        private int ticksUntilNextAttack = 15;
        private boolean shouldCountTillNextAttack = false;
        public static boolean render = false;

        public HereticAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
            super(mob, speed, pauseWhenMobIdle);
            entity = ((HailingHereticEntity) mob);
        }

        @Override
        public void start() {
            super.start();
            tick1 = 0;
            attackDelay = 15;
            ticksUntilNextAttack = 15;
        }


        @Override
        protected void attack(LivingEntity pEnemy, double pDistToEnemySqr) {
            if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
                shouldCountTillNextAttack = true;


                if (isTimeToStartAttackAnimation()) {
                    entity.setAttacking(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    render = true;

                    if (!pEnemy.isBlocking()) {
                        pEnemy.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 99));
                    }else {
                        this.entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 99));
                    }

                    performAttack(pEnemy);
                }
            } else {
                render = false;

                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAttacking(false);
                entity.attackAnimationTimeout = 0;
            }
        }



        public static boolean shouldRender() {
            return render;
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getSquaredMaxAttackDistance(pEnemy);
        }


        protected boolean isTimeToStartAttackAnimation() {
            return this.ticksUntilNextAttack <= attackDelay;
        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.getTickCount(attackDelay * 2);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected void performAttack(LivingEntity pEnemy) {

            this.resetAttackCooldown();
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.tryAttack(pEnemy);
        }

        @Override
        public void tick() {
            super.tick();

            safetyMeasure();
            if (shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                if (ticksUntilNextAttack == 0) {
                    entity.setAttacking(false);
                }
            }
        }

        @Override
        public void stop() {
            entity.setAttacking(false);
            super.stop();
        }
    }


    // attacking

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(HailingHereticEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl) {
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            if (this.getMainHandStack().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setOnFireFor(2 * (int) f);
            }
        }
        setAttacking(true);
        return bl;
    }


}