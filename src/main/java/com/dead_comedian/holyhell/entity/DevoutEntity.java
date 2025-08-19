//package com.dead_comedian.holyhell.entity;
//
//
//import com.dead_comedian.holyhell.entity.ai.DevoutAttackGoal;
//import com.dead_comedian.holyhell.registries.HolyHellItems;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.syncher.EntityDataAccessor;
//import net.minecraft.network.syncher.EntityDataSerializers;
//import net.minecraft.network.syncher.SynchedEntityData;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.*;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.ServerLevelAccessor;
//
//public class DevoutEntity extends Monster {
//
//
//    ///////////////
//    // VARIABLES //
//    ///////////////
//
//    public final AnimationState idleAnimationState = new AnimationState();
//    public final AnimationState rangeAttackAnimationState = new AnimationState();
//    public final AnimationState meleeAttackAnimationState = new AnimationState();
//
//    private int idleAnimationTimeout = 0;
//    public int rangeAttackAnimationTimeout = 0;
//    public int meleeAttackAnimationTimeout = 0;
//
//
//
//    private static final EntityDataAccessor<Integer> ATTACK_NO =
//            SynchedEntityData.defineId(DevoutEntity.class, EntityDataSerializers.INT);
//
//    public void setAttackNo(int attacking) {
//        this.entityData.set(ATTACK_NO, attacking);
//    }
//
//
//    public int getAttackNo() {
//        return this.entityData.get(ATTACK_NO);
//    }
//
//    //////////
//    // MISC //
//    //////////
//
//    public DevoutEntity(EntityType<? extends Monster> entityType, Level world) {
//        super(entityType, world);
//        this.xpReward = 20;
//    }
//
//
//    @javax.annotation.Nullable
//    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
//        SpawnGroupData spawngroupdata = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
//        RandomSource randomsource = pLevel.getRandom();
//        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
//        return spawngroupdata;
//    }
//
//    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
//        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(HolyHellItems.SACRIFICIAL_KATAR.get()));
//    }
//
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(ATTACKING, false);
//        this.entityData.define(ATTACK_NO, 0);
//    }
//
//
//    @Override
//    public void tick() {
//        super.tick();
//
//        if (this.level().isClientSide()) {
//            setupAnimationStates();
//        }
//    }
//
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 0.6, 1.5));
//        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
//        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
//        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
//        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
//        this.goalSelector.addGoal(7, new DevoutAttackGoal(this, 1.4, true));
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
//    }
//
//    public static AttributeSupplier.Builder createDevoutAttributes() {
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
//        System.out.println(this.getAttackNo());
//        if (this.getAttackNo() == 1 && isAggressive()) {
//            meleeAttackAnimationState.stop();
//
//            rangeAttackAnimationTimeout = 40;
//            rangeAttackAnimationState.startIfStopped(this.tickCount);
//
//
//        } else {
//            --this.rangeAttackAnimationTimeout;
//        }
//        if (this.getAttackNo() == 2 && isAggressive()) {
//
//            rangeAttackAnimationState.stop();
//
//            meleeAttackAnimationTimeout = 40;
//            meleeAttackAnimationState.startIfStopped(this.tickCount);
//
//
//        } else {
//            --this.meleeAttackAnimationTimeout;
//        }
//
//
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
//            SynchedEntityData.defineId(DevoutEntity.class, EntityDataSerializers.BOOLEAN);
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
//
//}