package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class KamikazeEntity extends Monster implements FlyingAnimal {


    ///////////////
    // VARIABLES //
    ///////////////

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public boolean isHit = false;


    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean boolea) {
        isHit = boolea;
    }

    //////////
    // MISC //
    //////////

    public KamikazeEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }

    @Override
    public void tick() {
        super.tick();

        if (!isAlive()) {
            this.explode(1d);
            this.level().addParticle(HolyhellParticles.KAMIKAZE_EXPLOSION.get(), this.getX(), this.getY(), this.getZ(), 0.1, 0.1, 0.1);
            this.discard();
        }


        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(1, new KamikazeAngelWanderAroundGoal());
        this.goalSelector.addGoal(2, new KamikazeExplodeGoal(this, 1, true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createKamikazeAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 5f)
                .add(Attributes.FLYING_SPEED, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 15.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }


    ////////////////
    // NAVIGATION //
    ////////////////


    @Override
    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world) {
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(false);
        birdNavigation.setCanPassDoors(true);
        return birdNavigation;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    // fall
    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        fallDistance = 0;
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


    }

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f);
    }


    ///////////////
    // EXPLOSION //
    ///////////////


    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }


    protected void explode(double power) {

        if (!this.level().isClientSide) {


            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)( 3 * power), Level.ExplosionInteraction.MOB);
            this.discard();

        }

    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        this.kill();

    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    ////////
    // AI //
    ////////


    class KamikazeAngelWanderAroundGoal extends Goal {
        KamikazeAngelWanderAroundGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return KamikazeEntity.this.navigation.isDone() && KamikazeEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return KamikazeEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3d = this.getRandomLocation();
            if (vec3d != null) {
                KamikazeEntity.this.navigation.moveTo(KamikazeEntity.this.navigation.createPath(BlockPos.containing(vec3d), 1), 1.0);
            }

        }

        @Nullable
        private Vec3 getRandomLocation() {
            Vec3 vec3d2;
            vec3d2 = KamikazeEntity.this.getViewVector(0.0F);

            Vec3 vec3d3 = HoverRandomPos.getPos(KamikazeEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964F, 3, 1);
            return vec3d3 != null ? vec3d3 : AirAndWaterRandomPos.getPos(KamikazeEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }

    }


    class KamikazeExplodeGoal extends Goal {
        private final KamikazeEntity entity;
        private int timeSinceAttack;
        private double speednt;
        private final PathNavigation navigation;
        @Nullable
        protected LivingEntity targetHostileEntity;
        protected TargetingConditions targetPredicate;


        public KamikazeExplodeGoal(PathfinderMob mob, double speed, boolean pauseWhenMobIdle) {

            entity = ((KamikazeEntity) mob);
            speednt = speed;
            this.navigation = mob.getNavigation();
            this.targetPredicate = TargetingConditions.forCombat().range(this.entity.getAttributeValue(Attributes.FOLLOW_RANGE)).selector((Predicate<LivingEntity>) targetPredicate);

        }


        @Override
        public boolean canUse() {
            return entity.getTarget() != null;
        }

        @Override
        public void start() {
            super.start();
            timeSinceAttack = 0;
        }

        public void findClosestTarget() {
            this.targetHostileEntity = this.entity.level().getNearestEntity(this.entity.level().getEntitiesOfClass(Monster.class, this.getSearchBox(this.entity.getAttributeValue(Attributes.FOLLOW_RANGE)), (livingEntity) -> true), this.targetPredicate, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ());
        }

        protected AABB getSearchBox(double distance) {
            return this.entity.getBoundingBox().inflate(distance, 1.0, distance);
        }

        public boolean startMovingTo(Entity entity, double speed) {
            Path path = this.navigation.createPath(this.targetHostileEntity.getBlockX() + random.nextInt(1), this.targetHostileEntity.getBlockY() + 2, this.targetHostileEntity.getBlockZ() + random.nextInt(1), 1);
            return path != null && this.navigation.moveTo(path, speed);
        }

        @Override
        public void tick() {
            super.tick();
            this.findClosestTarget();
            if (timeSinceAttack < 50 && this.targetHostileEntity != null && !(targetHostileEntity instanceof KamikazeEntity)) {

                this.startMovingTo(this.targetHostileEntity, this.speednt * 0.8);
                timeSinceAttack++;

            } else {
                this.navigation.moveTo(this.entity.getTarget(), this.speednt * 2);
                if (this.entity.getIsHit()) {
                    setIsHit(false);
                    this.timeSinceAttack = 0;
                }
            }
        }
    }


    // attacking

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(KamikazeEntity.class, EntityDataSerializers.BOOLEAN);

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
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (!(damageSource.getDirectEntity() instanceof AbstractArrow) && damageSource.is(DamageTypes.GENERIC_KILL)) {
            if (damageSource.getDirectEntity() != null) {
                this.addDeltaMovement(damageSource.getDirectEntity().getLookAngle());
            }
            setIsHit(true);
            return false;
        } else {
            return super.isInvulnerableTo(damageSource);
        }
    }
}