package com.dead_comedian.holyhell.entity.custom;


import com.dead_comedian.holyhell.entity.custom.spells.FallingSwordEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PalladinEntity extends HostileEntity {


    ///////////////
    // VARIABLES //
    ///////////////

    int counter = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    int cooldown = 0;

    //////////
    // MISC //
    //////////

    public PalladinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);

    }


    @Override
    public void tick() {
        super.tick();
        cooldown++;

        FuckThisImDoingMyOwnSystem(cooldown);
        if (cooldown == 250) {
            cooldown = 0;
        }
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new FleeEntityGoal(this, PlayerEntity.class, 6.0F, 0.6, 1.5));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createPalladinAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
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

        if (this.isAttacking()) {
            attackAnimationTimeout = 40;
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

    //  attacking
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(PalladinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    // summoning

    public void summonSwordRing(Entity entity) {
        int loop = 0;
        Iterable<BlockPos> a = BlockPos.iterate(-2, 3, -2, 2, 3, 2);
        for (BlockPos b : a) {

            FallingSwordEntity swordEntity = new FallingSwordEntity(HolyHellEntities.FALLING_SWORD, this.getWorld());
            this.getWorld().spawnEntity(swordEntity);
            swordEntity.refreshPositionAndAngles(entity.getBlockPos().add(b), swordEntity.getYaw(), swordEntity.getPitch());

            if (loop == 6 || loop == 7 || loop == 8 || loop == 11 || loop == 12 || loop == 13 || loop == 16 || loop == 17 || loop == 18) {
                swordEntity.discard();
            }
            loop++;
        }
    }

    public void FuckThisImDoingMyOwnSystem(int cooldown) {
        @Nullable
        LivingEntity targetEntity = PalladinEntity.this.getTarget();






        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {

            setAttacking(true);
        } else {
            if (cooldown == 40 && targetEntity != null && targetEntity.isAlive()) {
                setAttacking(false);
            }
        }
        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {
            summonSwordRing(this.getTarget());
        }
    }

}




