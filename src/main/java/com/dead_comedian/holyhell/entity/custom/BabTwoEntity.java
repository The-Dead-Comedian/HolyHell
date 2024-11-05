package com.dead_comedian.holyhell.entity.custom;


import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.List;

public class BabTwoEntity extends HostileEntity {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState Lvl2IdleAnimationState = new AnimationState();
    private int Lvl2IdleAnimationTimeout = 0;
    public final AnimationState Lvl2AttackAnimationState = new AnimationState();
    public int Lvl2AttackAnimationTimeout = 0;

    //////////
    // MISC //
    //////////

    public BabTwoEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
        this.dataTracker.startTracking(LEVEL, 0);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.1, 0.1, 0.1));
        for (Entity i : entityBelow) {
            if (i instanceof BabTwoEntity) {
                if (((BabTwoEntity) i).getLevel() == 0 && this.getLevel() <= 2) {
                    if (this.collidesWith(i)) {
                        this.setLevel(this.getLevel() + 1);
                        System.out.println("a");
                        i.discard();
                    }
                }
            }
        }
        collidesWith(this);
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(1, new FollowMobGoal(this, 1D, 1.0F, 20.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAngelAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.8f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {

        if (this.isAttacking() && Lvl2AttackAnimationTimeout <= 0) {
            Lvl2AttackAnimationTimeout = 40;
            Lvl2AttackAnimationState.startIfNotRunning(this.age);


        } else {
            --this.Lvl2AttackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            Lvl2AttackAnimationState.stop();
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


    //  attacking
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BabTwoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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

    /////////
    // NBT //
    /////////

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setLevel(nbt.getInt("Level"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("Level", this.getLevel());
    }


    ///////////////
    // COLISSION //
    ///////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabTwoEntity;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    public boolean collidesWith(Entity other) {
        return canCollide(this, other);
    }

    ///////////
    // LEVEL //
    ///////////

    private static final TrackedData<Integer> LEVEL =
            DataTracker.registerData(BabTwoEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public void setLevel(int level) {
        this.dataTracker.set(LEVEL, level);
    }

    public int getLevel() {
        return this.dataTracker.get(LEVEL);
    }
}




