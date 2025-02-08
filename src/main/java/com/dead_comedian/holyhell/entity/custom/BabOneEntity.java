package com.dead_comedian.holyhell.entity.custom;


import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.EntityGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BabOneEntity extends TamableAnimal {


    ///////////////
    // VARIABLES //
    ///////////////
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TAMED, false);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(0.1, 0.1, 0.1));
        for (Entity i : entityBelow) {
            if (i instanceof BabOneEntity && i != this) {
                if (this.isTame() && (this.getOwner() == ((BabOneEntity) i).getOwner() || !((BabOneEntity) i).isTame())) {
                    if (this.canCollideWith(i)) {

                        BlockPos blockPos = this.blockPosition();
                        BabTwoEntity babTwoEntity = new BabTwoEntity(HolyHellEntities.BAB_TWO, this.level());
                        this.level().addFreshEntity(babTwoEntity);
                        babTwoEntity.setTame(true);
                        babTwoEntity.tame((Player) this.getOwner());
                        babTwoEntity.moveTo(blockPos, babTwoEntity.getYRot(), babTwoEntity.getXRot());
                        this.discard();
                        i.discard();
                    }
                }
            }
        }
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Monster.class, 6, 1, 1));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAngelAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.2f).add(Attributes.ARMOR, 0.4f);
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


    ////////
    // AI //
    ////////


    //  attacking
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(BabOneEntity.class, EntityDataSerializers.BOOLEAN);

    public void setAggressive(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAggressive() {
        return this.entityData.get(ATTACKING);
    }


    ///////////////
    // COLLISION //
    ///////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabOneEntity || other instanceof BabTwoEntity && entity.isAlive();
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isAlive();
    }

    public boolean canCollideWith(Entity other) {
        return canCollide(this, other);
    }


    ///////////
    // TAMED //
    ///////////


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.STICK) && !this.isTame()) {
            this.discard();

            player.addItem(HolyHellItems.KEBAB.getDefaultInstance());
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(HolyHellItems.HOLY_TEAR) && !this.isTame()) {
            setTame(true);
            this.tame(player);
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }


    @Override
    public void setTame(boolean tamed) {
        this.entityData.set(TAMED, tamed);
        super.setTame(tamed);
    }



}



