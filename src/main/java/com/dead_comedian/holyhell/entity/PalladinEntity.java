//package com.dead_comedian.holyhell.entity;
//
//
//
//import com.dead_comedian.holyhell.entity.non_living.FallingSwordEntity;
//import com.dead_comedian.holyhell.registries.HolyHellEntities;
//import net.minecraft.core.BlockPos;
//import net.minecraft.network.syncher.EntityDataAccessor;
//import net.minecraft.network.syncher.EntityDataSerializers;
//import net.minecraft.network.syncher.SynchedEntityData;
//import net.minecraft.world.entity.AnimationState;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.Pose;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
//import net.minecraft.world.entity.ai.goal.FloatGoal;
//import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
//import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
//import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import org.jetbrains.annotations.Nullable;
//
//public class PalladinEntity extends Monster {
//
//
//    ///////////////
//    // VARIABLES //
//    ///////////////
//
//    public final AnimationState idleAnimationState = new AnimationState();
//    public final AnimationState attackAnimationState = new AnimationState();
//
//    private int idleAnimationTimeout = 0;
//    public int attackAnimationTimeout = 0;
//    int cooldown = 0;
//
//    //////////
//    // MISC //
//    //////////
//
//    public PalladinEntity(EntityType<? extends Monster> entityType, Level world) {
//        super(entityType, world);
//        this.xpReward = 20;
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(ATTACKING, false);
//
//    }
//
//
//    @Override
//    public void tick() {
//        super.tick();
//        cooldown++;
//
//        FuckThisImDoingMyOwnSystem(cooldown);
//        if (cooldown == 100) {
//            cooldown = 0;
//        }
//        if (this.level().isClientSide()) {
//            setupAnimationStates();
//        }
//    }
//
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, Player.class, 6.0F, 0.6, 1.5));
//        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
//        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
//        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
//        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
//    }
//
//    public static AttributeSupplier.Builder createPalladinAttributes() {
//        return Mob.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, 50)
//                .add(Attributes.MOVEMENT_SPEED, 0.2f)
//                .add(Attributes.ARMOR, 0.5f)
//                .add(Attributes.ATTACK_DAMAGE, 2);
//    }
//
//    ///////////////
//    // ANIMATION //
//    ///////////////
//
//
//    private void setupAnimationStates() {
//        if (this.idleAnimationTimeout <= 0) {
//            this.idleAnimationTimeout = this.random.nextInt(20) + 40;
//
//            this.idleAnimationState.start(this.tickCount);
//
//
//        } else {
//            --this.idleAnimationTimeout;
//        }
//
//        if (this.isAggressive()) {
//            attackAnimationTimeout = 40;
//            attackAnimationState.startIfStopped(this.tickCount);
//
//
//        } else {
//            --this.attackAnimationTimeout;
//        }
//
//        if (!this.isAggressive()) {
//            attackAnimationState.stop();
//        }
//
//
//    }
//
//    @Override
//    protected void updateWalkAnimation(float posDelta) {
//        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
//        this.walkAnimation.update(f, 0.2f);
//    }
//
//
//    ////////
//    // AI //
//    ////////
//
//    //  attacking
//    private static final EntityDataAccessor<Boolean> ATTACKING =
//            SynchedEntityData.defineId(PalladinEntity.class, EntityDataSerializers.BOOLEAN);
//
//    public void setAggressive(boolean attacking) {
//        this.entityData.set(ATTACKING, attacking);
//    }
//
//    @Override
//    public boolean isAggressive() {
//        return this.entityData.get(ATTACKING);
//    }
//
//    // summoning
//
//    public void summonSwordRing(Entity entity) {
//        int loop = 0;
//        Iterable<BlockPos> a = BlockPos.betweenClosed(-2, 3, -2, 2, 3, 2);
//        for (BlockPos b : a) {
//
//            FallingSwordEntity swordEntity = new FallingSwordEntity(HolyHellEntities.FALLING_SWORD.get(), this.level());
//            this.level().addFreshEntity(swordEntity);
//            swordEntity.moveTo(entity.blockPosition().offset(b), swordEntity.getYRot(), swordEntity.getXRot());
//
//            if (loop == 6 || loop == 7 || loop == 8 || loop == 11 || loop == 12 || loop == 13 || loop == 16 || loop == 17 || loop == 18) {
//                swordEntity.discard();
//            }
//            loop++;
//        }
//    }
//
//    public void FuckThisImDoingMyOwnSystem(int cooldown) {
//        @Nullable
//        LivingEntity targetEntity = PalladinEntity.this.getTarget();
//
//
//
//
//
//
//        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {
//
//            setAggressive(true);
//        } else {
//            if (cooldown == 40 && targetEntity != null && targetEntity.isAlive()) {
//                setAggressive(false);
//            }
//        }
//        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {
//            summonSwordRing(this.getTarget());
//        }
//    }
//
//}