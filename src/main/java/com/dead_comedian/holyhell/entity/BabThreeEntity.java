package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class BabThreeEntity extends TamableAnimal {


    ///////////////
    // VARIABLES //
    ///////////////

    private static final Ingredient TEMPT_ITEMS = Ingredient.of(HolyHellItems.HOLY_TEAR.get());
    public final AnimationState Lvl3IdleAnimationState = new AnimationState();
    private int Lvl3IdleAnimationTimeout = 0;
    public final AnimationState Lvl3AttackAnimationState = new AnimationState();
    public int Lvl3AttackAnimationTimeout = 0;

    ///////
    //NBT//
    ///////
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    //////////
    // MISC //
    //////////

    public BabThreeEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ATTACKING, false);

    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Tamed", this.isTame());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setTame(nbt.getBoolean("Tamed"), false);
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
        this.goalSelector.addGoal(3,  new TemptGoal(this, 1.4D, TEMPT_ITEMS, false));

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(5, new BabThreeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this, new Class[0]));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 35)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 2f)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.FOLLOW_RANGE, 10);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty()) {

            ItemStack stack = new ItemStack(HolyHellItems.BAB.get());
            CompoundTag tag = new CompoundTag();

            tag.putInt("level", 3);
            if (this.getOwnerUUID() != null) {
                tag.putUUID("owner", this.getOwnerUUID());
            }
            tag.putBoolean("tamed", this.isTame());

            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

            player.addItem(stack);


            this.discard();

        }
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(HolyHellItems.HOLY_TEAR.get()) && !this.isTame()) {
            setTame(true, false);
            this.tame(player);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            this.playSound(HolyHellSounds.BAB_TAME.get(), 1F, 1F);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {

        if (this.Lvl3IdleAnimationTimeout <= 0) {
            this.Lvl3IdleAnimationTimeout = this.random.nextInt(20) + 40;
            this.Lvl3IdleAnimationState.start(this.tickCount);
        } else {
            --this.Lvl3IdleAnimationTimeout;
        }
        if (this.isAggressive() && Lvl3AttackAnimationTimeout <= 0) {
            Lvl3AttackAnimationTimeout = 20;
            Lvl3AttackAnimationState.start(this.tickCount);

        } else {
            --this.Lvl3AttackAnimationTimeout;
        }

        if (!this.isAggressive()) {
            Lvl3AttackAnimationState.stop();
            this.setAggressive(false);
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


    public class BabThreeAttackGoal extends MeleeAttackGoal {


        private final BabThreeEntity entity;
        private int attackDelay = 20;
        private int ticksUntilNextAttack = 20;
        private boolean shouldCountTillNextAttack = false;


        public BabThreeAttackGoal(PathfinderMob mob, double speed, boolean pauseWhenMobIdle) {
            super(mob, speed, pauseWhenMobIdle);
            entity = ((BabThreeEntity) mob);
        }

        @Override
        public void start() {
            super.start();
            attackDelay = 10;
            ticksUntilNextAttack = 10;
        }


        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy) {
            if (canPerformAttack(pEnemy)) {
                shouldCountTillNextAttack = true;


                if (isTimeToStartAttackAnimation()) {
                    entity.setAggressive(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAggressive(false);

            }
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

    //  attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BabThreeEntity.class, EntityDataSerializers.BOOLEAN);

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
            BlockPos blockPos = this.getTarget().blockPosition();
            HolySpiritEntity holySpiritEntity = new HolySpiritEntity(HolyHellEntities.HOLY_SPIRIT.get(), this.level());
            this.level().addFreshEntity(holySpiritEntity);
            holySpiritEntity.setTarget(this.getTarget());
            holySpiritEntity.addDeltaMovement(this.getLookAngle());
            holySpiritEntity.moveTo(blockPos, holySpiritEntity.getYRot(), holySpiritEntity.getXRot());
            this.playSound(HolyHellSounds.BAB_3_ATTACK.get(),1F,1F);
        }
        setAggressive(true);
        return bl;
    }

    /////////
    //SOUND//
    /////////

    protected SoundEvent getStepSound() {
        return HolyHellSounds.BAB_LEG_WALK.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.7F, 1.0F);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return HolyHellSounds.BAB_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return HolyHellSounds.BAB_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return HolyHellSounds.BAB_DIE.get();
    }

    ///////////////
    // COLLISION //
    ///////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabThreeEntity;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean canCollideWith(Entity other) {
        return canCollide(this, other);
    }


}




