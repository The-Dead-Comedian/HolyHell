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
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
            if (i instanceof BabOneEntity && this.isTamed()) {
                if (this.collidesWith(i)) {

                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                    System.out.println("bam");
                    this.discard();
                    i.kill();
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
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(1, new FollowOwnerGoal(this, 1, 0, 30, true));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAngelAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f).add(EntityAttributes.GENERIC_ARMOR, 0.8f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
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


    ///////////////
    // COLISSION //
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
        Item item = itemStack.getItem();
        if (this.getWorld().isClient) {
            System.out.println(this.dataTracker.get(TAMED));
            System.out.println(this.getOwner());
            if (itemStack.isOf(HolyHellItems.HOLY_TEAR) && !this.isTamed()) {
                setTamed(true);
                this.setOwner(player);
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }

                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }

    public boolean getTamed() {
        return this.dataTracker.get(TAMED);
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


    /////////
    // NBT //
    /////////


    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setTamed(nbt.getBoolean("Tamed"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("Tamed", this.isTamed());
    }
}