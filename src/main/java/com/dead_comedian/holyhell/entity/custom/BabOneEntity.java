package com.dead_comedian.holyhell.entity.custom;


import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BabOneEntity extends TameableEntity {


    ///////////////
    // VARIABLES //
    ///////////////
    private static final TrackedData<Boolean> TAMED = DataTracker.registerData(BabOneEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


    public final AnimationState Lvl1IdleAnimationState = new AnimationState();
    private int Lvl1IdleAnimationTimeout = 0;
    //////////
    // MISC //
    //////////

    public BabOneEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TAMED, false);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.1, 0.1, 0.1));
        for (Entity i : entityBelow) {
            if (i instanceof BabOneEntity && i != this) {
                if (this.isTamed() && (this.getOwner() == ((BabOneEntity) i).getOwner() || !((BabOneEntity) i).isTamed())) {
                    if (this.collidesWith(i)) {

                        BlockPos blockPos = this.getBlockPos();
                        BabTwoEntity babTwoEntity = new BabTwoEntity(HolyHellEntities.BAB_TWO, this.getWorld());
                        this.getWorld().spawnEntity(babTwoEntity);
                        babTwoEntity.setTamed(true);
                        babTwoEntity.setOwner((PlayerEntity) this.getOwner());
                        babTwoEntity.refreshPositionAndAngles(blockPos, babTwoEntity.getYaw(), babTwoEntity.getPitch());
                        this.discard();
                        i.discard();
                    }
                }
            }
        }
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, HostileEntity.class, 6, 1, 1));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAngelAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f).add(EntityAttributes.GENERIC_ARMOR, 0.4f);
    }

    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {

        if (this.Lvl1IdleAnimationTimeout <= 0) {
            this.Lvl1IdleAnimationTimeout = this.random.nextInt(20) + 40;
            this.Lvl1IdleAnimationState.start(this.age);
        } else {
            --this.Lvl1IdleAnimationTimeout;
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
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(BabOneEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }


    ///////////////
    // COLLISION //
    ///////////////

    public static boolean canCollide(Entity entity, Entity other) {
        return other instanceof BabOneEntity || other instanceof BabTwoEntity && entity.isAlive();
    }

    @Override
    public boolean isCollidable() {
        return this.isAlive();
    }

    public boolean collidesWith(Entity other) {
        return canCollide(this, other);
    }


    ///////////
    // TAMED //
    ///////////


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {

        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.STICK) && !this.isTamed()) {
            this.discard();

            player.giveItemStack(HolyHellItems.KEBAB.getDefaultStack());
            if (!player.isCreative()) {
                itemStack.decrement(1);
            }

            return ActionResult.SUCCESS;
        }

        if (itemStack.isOf(HolyHellItems.HOLY_TEAR) && !this.isTamed()) {
            setTamed(true);
            this.setOwner(player);
            if (!player.isCreative()) {
                itemStack.decrement(1);
            }

            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }


    @Override
    public void setTamed(boolean tamed) {
        this.dataTracker.set(TAMED, tamed);
        super.setTamed(tamed);
    }


    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }
}



