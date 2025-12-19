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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BabTwoEntity extends TamableAnimal {


    ///////////////
    // VARIABLES //
    /// ////////////

    private static final Ingredient TEMPT_ITEMS = Ingredient.of(HolyHellItems.HOLY_TEAR.get());
    private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(BabTwoEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState Lvl2IdleAnimationState = new AnimationState();
    public final AnimationState Lvl2AttackAnimationState = new AnimationState();
    public int Lvl2AttackAnimationTimeout = 0;

    //////////
    // MISC //

    /// ///////

    public BabTwoEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty()) {

            ItemStack stack = new ItemStack(HolyHellItems.BAB.get());
            CompoundTag tag = new CompoundTag();

            tag.putInt("level", 2);
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

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ATTACKING, false);
        builder.define(TAMED, false);
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

        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(0.1, 0.1, 0.1));
        for (Entity i : entityBelow) {
            if (i instanceof BabTwoEntity) {
                if (this.isTame() && (this.getOwner() == ((BabTwoEntity) i).getOwner() || !((BabTwoEntity) i).isTame())) {
                    if (this.canCollideWith(i)) {

                        BlockPos blockPos = this.blockPosition();
                        BabThreeEntity babThreeEntity = new BabThreeEntity(HolyHellEntities.BAB_THREE.get(), this.level());
                        this.level().addFreshEntity(babThreeEntity);
                        babThreeEntity.setTame(true, false);
                        babThreeEntity.tame((Player) this.getOwner());
                        babThreeEntity.moveTo(blockPos, babThreeEntity.getYRot(), babThreeEntity.getXRot());
                        this.discard();
                        i.discard();
                    }
                }
            }
        }
        entityBelow.removeAll(entityBelow);
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.4D, TEMPT_ITEMS, false));

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 0.8f)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.FOLLOW_RANGE, 10);
    }

    ///////////////
    // ANIMATION //

    /// ////////////


    private void setupAnimationStates() {

        if (this.isAggressive() && Lvl2AttackAnimationTimeout <= 0) {
            Lvl2AttackAnimationTimeout = 15;
            Lvl2AttackAnimationState.start(this.tickCount);

        } else {
            --this.Lvl2AttackAnimationTimeout;
        }

        if (!this.isAggressive()) {
            Lvl2AttackAnimationState.stop();
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
    /// /////


    //  attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BabTwoEntity.class, EntityDataSerializers.BOOLEAN);

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
        this.playSound(HolyHellSounds.BAB_2_ATTACK.get(), 1F, 1F);
        setAggressive(true);
        return bl;
    }


    ///////////
    // TAMED //

    /// ////////

    @Override
    public void setTame(boolean tame, boolean applyTamingSideEffects) {
        super.setTame(tame, applyTamingSideEffects);
        this.entityData.set(TAMED, tame);
    }


    ///////////////
    // COLLISION //

    /// ////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabTwoEntity;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean canCollideWith(Entity other) {
        return canCollide(this, other);
    }
    /////////
    //SOUND//

    /// //////


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


    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }
}




