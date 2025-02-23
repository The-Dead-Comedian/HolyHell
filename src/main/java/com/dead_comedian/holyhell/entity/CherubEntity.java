package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
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
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;


public class CherubEntity extends Monster implements FlyingAnimal {
    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;





    //////////
    // MISC //
    //////////

    public CherubEntity(EntityType<? extends Monster> entityType, Level world) {
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
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(1, new CherubWanderAroundGoal());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createCherubAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15f)
                .add(Attributes.FLYING_SPEED, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.FOLLOW_RANGE, 15.0);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if(this.level() instanceof ServerLevel){
            ((ServerLevel) this.level()).sendParticles(HolyhellParticles.SOUND_RING.get(),this.getX(),this.getY(),this.getZ(),1,0,0,0,0);
        }
        return super.hurt(pSource, pAmount);

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


    class CherubWanderAroundGoal extends Goal {
        CherubWanderAroundGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return CherubEntity.this.navigation.isDone() && CherubEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return CherubEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3d = this.getRandomLocation();
            if (vec3d != null) {
                CherubEntity.this.navigation.moveTo(CherubEntity.this.navigation.createPath(BlockPos.containing(vec3d), 1), 1.0);
            }

        }

        @Nullable
        private Vec3 getRandomLocation() {
            Vec3 vec3d2;
            vec3d2 = CherubEntity.this.getViewVector(0.0F);

            Vec3 vec3d3 = HoverRandomPos.getPos(CherubEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964F, 3, 1);
            return vec3d3 != null ? vec3d3 : AirAndWaterRandomPos.getPos(CherubEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }

    }
}