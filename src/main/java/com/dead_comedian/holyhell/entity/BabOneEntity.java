package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.registries.HolyHellCriteriaTriggers;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BabOneEntity extends TamableAnimal {


    ///////////////
    // VARIABLES //
    ///////////////

    private static final Ingredient TEMPT_ITEMS = Ingredient.of(HolyHellItems.HOLY_TEAR.get());
    private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(BabOneEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState Lvl1IdleAnimationState = new AnimationState();
    private int Lvl1IdleAnimationTimeout = 0;

    //////////
    // MISC //
    //////////

    public BabOneEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
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
            if (i instanceof BabOneEntity) {
                if (this.isTame() && (this.getOwner() == ((BabOneEntity) i).getOwner() || !((BabOneEntity) i).isTame())) {
                    if (this.canCollideWith(i)) {

                        if (this.getOwner() instanceof ServerPlayer) {
                            HolyHellCriteriaTriggers.BAB_MERGE.get().trigger(((ServerPlayer) (Object) this.getOwner()));
                        }

                        BlockPos blockPos = this.blockPosition();
                        BabTwoEntity babTwoEntity = new BabTwoEntity(HolyHellEntities.BAB_TWO.get(), this.level());
                        this.level().addFreshEntity(babTwoEntity);
                        babTwoEntity.setTame(true, false);
                        babTwoEntity.tame((Player) this.getOwner());
                        babTwoEntity.moveTo(blockPos, babTwoEntity.getYRot(), babTwoEntity.getXRot());
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
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes().add(Attributes.MAX_HEALTH, 15).add(Attributes.MOVEMENT_SPEED, 0.2f).add(Attributes.ARMOR, 0.8f).add(Attributes.ATTACK_DAMAGE, 12.0).add(Attributes.FOLLOW_RANGE, 10);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.STICK) && !this.isTame()) {
            this.discard();
            if (player instanceof ServerPlayer) {
                HolyHellCriteriaTriggers.KEBAB.get().trigger((ServerPlayer) player);
            }
            player.addItem(HolyHellItems.KEBAB.get().getDefaultInstance());
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(HolyHellItems.HOLY_TEAR.get()) && !this.isTame()) {
            setTame(true, false);
            this.tame(player);
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }
            this.playSound(HolyHellSound.BAB_TAME.get(), 1F, 1F);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {

        if (this.Lvl1IdleAnimationTimeout <= 0) {
            this.Lvl1IdleAnimationTimeout = this.random.nextInt(20) + 40;
            this.Lvl1IdleAnimationState.start(this.tickCount);
        } else {
            --this.Lvl1IdleAnimationTimeout;
        }
    }


    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f);
    }


    ///////////
    // TAMED //
    ///////////

    @Override
    public void setTame(boolean tame, boolean applyTamingSideEffects) {
        super.setTame(tame, applyTamingSideEffects);
        this.entityData.set(TAMED, tame);
    }


    ///////////////
    // COLLISION //
    ///////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabOneEntity;
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
    /////////


    protected SoundEvent getStepSound() {
        return HolyHellSound.BAB_LEG_WALK.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.7F, 1.0F);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return HolyHellSound.BAB_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return HolyHellSound.BAB_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return HolyHellSound.BAB_DIE.get();
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }
}




