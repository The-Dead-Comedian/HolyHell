package net.deadcomedian.holyhell.entity.custom;

import net.deadcomedian.holyhell.entity.ai.HailingHereticAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class HailingHereticEntity extends Monster {


    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(HailingHereticEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public HailingHereticEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }



    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);

    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));


        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new HailingHereticAttackGoal(this, 1.0D, true));


        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

        public static AttributeSupplier.Builder createAttributes() {
            return Monster.createLivingAttributes()
                    .add(Attributes.MAX_HEALTH, 20D)
                    .add(Attributes.FOLLOW_RANGE, 24D)
                    .add(Attributes.MOVEMENT_SPEED, 0.25D)
                    .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                    .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                    .add(Attributes.ATTACK_DAMAGE, 2f);
        }






}
