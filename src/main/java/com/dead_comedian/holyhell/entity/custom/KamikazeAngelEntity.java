package com.dead_comedian.holyhell.entity.custom;



import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

public class KamikazeAngelEntity extends HostileEntity implements Flutterer {


    ///////////////
    // VARIABLES //
    ///////////////

    int safetyMargin = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public boolean isHit = false;


    public boolean getIsHit(){
        return isHit;
    }
    public void setIsHit(boolean boolea){
        isHit=boolea;
    }

    //////////
    // MISC //
    //////////

    public KamikazeAngelEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.setPathfindingPenalty(PathNodeType.LAVA, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
    }

    @Override
    public void tick() {
        super.tick();

        if (isAlive() && safetyMargin < 200) {
            safetyMargin++;
        }

        if (!isAlive()) {
            this.explode(1.5d);
        }


        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(1, new KamikazeAngelWanderAroundGoal());
        this.goalSelector.add(2, new KamikazeExplodeGoal(this, 1, true));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createHereticAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15.0);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);

    }


    ////////////////
    // NAVIGATION //
    ////////////////


    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world) {
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    public boolean isInAir() {
        return true;
    }

    // fall
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        fallDistance = 0;
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.age);


        } else {
            --this.idleAnimationTimeout;
        }


    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }


    ///////////////
    // EXPLOSION //
    ///////////////


    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }

    protected void explode(double power) {
        this.explode((DamageSource) null, power);
    }

    protected void explode(@Nullable DamageSource damageSource, double power) {
        if (!this.getWorld().isClient) {
            double d = Math.sqrt(power);
            if (d > 5.0) {
                d = 5.0;
            }

            this.getWorld().createExplosion(this, damageSource, (ExplosionBehavior) null, this.getX(), this.getY(), this.getZ(), (float) (4.0 + this.random.nextDouble() * 1.5 * d), false, World.ExplosionSourceType.TNT);
            this.discard();
        }

    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);

        if (safetyMargin == 200) {
            this.explode(1.5d);
        }
    }

    @Override
    public boolean isImmuneToExplosion() {
        return true;
    }

    ////////
    // AI //
    ////////


    class KamikazeAngelWanderAroundGoal extends Goal {
        KamikazeAngelWanderAroundGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return KamikazeAngelEntity.this.navigation.isIdle() && KamikazeAngelEntity.this.random.nextInt(10) == 0;
        }

        public boolean shouldContinue() {
            return KamikazeAngelEntity.this.navigation.isFollowingPath();
        }

        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                KamikazeAngelEntity.this.navigation.startMovingAlong(KamikazeAngelEntity.this.navigation.findPathTo(BlockPos.ofFloored(vec3d), 1), 1.0);
            }

        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d2;
            vec3d2 = KamikazeAngelEntity.this.getRotationVec(0.0F);

            Vec3d vec3d3 = AboveGroundTargeting.find(KamikazeAngelEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964F, 3, 1);
            return vec3d3 != null ? vec3d3 : NoPenaltySolidTargeting.find(KamikazeAngelEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }

    }


    class KamikazeExplodeGoal extends Goal {
        private final KamikazeAngelEntity entity;
        private int timeSinceAttack;
        private double speednt;
        private final EntityNavigation navigation;
        @Nullable
        protected LivingEntity targetEntity;
        protected TargetPredicate targetPredicate;


        public KamikazeExplodeGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {

            entity = ((KamikazeAngelEntity) mob);
            speednt = speed;
            this.navigation = mob.getNavigation();
            this.targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.entity.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE)).setPredicate((Predicate<LivingEntity>) targetPredicate);

        }



        @Override
        public boolean canStart() {
            return entity.getTarget() != null;
        }

        @Override
        public void start() {
            super.start();
            timeSinceAttack =0;
        }

        public void findClosestTarget(){
            this.targetEntity = this.entity.getWorld().getClosestEntity(this.entity.getWorld().getEntitiesByClass(HostileEntity.class, this.getSearchBox(this.entity.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE)), (livingEntity) -> true), this.targetPredicate, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
        }
        protected Box getSearchBox(double distance) {
            return this.entity.getBoundingBox().expand(distance, 1.0, distance);
        }
        public boolean startMovingTo(Entity entity, double speed) {
            Path path = this.navigation.findPathTo( this.targetEntity.getBlockX() + random.nextInt(1),this.targetEntity.getBlockY() + 2,this.targetEntity.getBlockZ() + random.nextInt(1),1);
            return path != null && this.navigation.startMovingAlong(path, speed);
        }

        @Override
        public void tick() {
            super.tick();
            this.findClosestTarget();
            if(timeSinceAttack < 50   && this.targetEntity!=null){

                this.startMovingTo(this.targetEntity, this.speednt * 0.8);
                timeSinceAttack++;

            }else{
                this.navigation.startMovingTo(this.entity.getTarget(), this.speednt*1.5);
                if(this.entity.getIsHit()){
                    setIsHit(false);
                    this.timeSinceAttack=0;
                }
            }
        }
    }


////    class KamikazeAngelAttackGoal extends MeleeAttackGoal {
//        private final KamikazeAngelEntity entity;
//        private int attackDelay = 200;
//        private int ticksUntilNextAttack = 200;
//        private boolean shouldCountTillNextAttack = false;
//
//        public KamikazeAngelAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
//            super(mob, speed, pauseWhenMobIdle);
//            entity = ((KamikazeAngelEntity) mob);
//        }
//
//        @Override
//        public void start() {
//            super.start();
//
//            attackDelay = 200;
//            ticksUntilNextAttack = 200;
//        }
//
//
//        @Override
//        protected void attack(LivingEntity pEnemy, double pDistToEnemySqr) {
//
//            if (collidesWith(pEnemy)) {
//                BlockPos blockPos = entity.getBlockPos();
//
//            }
//            if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
//                shouldCountTillNextAttack = true;
//
//                if (isTimeToStartAttackAnimation()) {
//                    entity.setAttacking(true);
//                }
//
//                if (isTimeToAttack()) {
//                    this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
//                    performAttack(pEnemy);
//                }
//            } else {
//                resetAttackCooldown();
//                shouldCountTillNextAttack = false;
//                entity.setAttacking(false);
//
//            }
//        }
//
//        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
//            return pDistToEnemySqr <= this.getSquaredMaxAttackDistance(pEnemy);
//        }
//
//
//        protected boolean isTimeToStartAttackAnimation() {
//            return this.ticksUntilNextAttack <= attackDelay;
//        }
//
//        protected void resetAttackCooldown() {
//            this.ticksUntilNextAttack = this.getTickCount(attackDelay * 2);
//        }
//
//        protected boolean isTimeToAttack() {
//            return this.ticksUntilNextAttack <= 0;
//        }
//
//        protected void performAttack(LivingEntity pEnemy) {
//
//            this.resetAttackCooldown();
//            this.mob.swingHand(Hand.MAIN_HAND);
//            this.mob.tryAttack(pEnemy);
//        }
//
//        @Override
//        public void tick() {
//            super.tick();
//            if (shouldCountTillNextAttack) {
//                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
//                if (ticksUntilNextAttack == 0) {
//                    entity.setAttacking(false);
//                }
//            }
//        }
//
//        @Override
//        public void stop() {
//            entity.setAttacking(false);
//            super.stop();
//        }
//    }

    // attacking

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(KamikazeAngelEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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


    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (!(damageSource.getSource() instanceof PersistentProjectileEntity) ) {
            if(damageSource.getSource()!=null){
            this.addVelocity(damageSource.getSource().getRotationVector());}
            setIsHit(true);
            return true;
        } else {
            return super.isInvulnerableTo(damageSource);
        }
    }
}




