package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class BabTwoEntity extends TamableAnimal {


    ///////////////
    // VARIABLES //
    ///////////////


    private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(BabTwoEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState Lvl2IdleAnimationState = new AnimationState();
    public final AnimationState Lvl2AttackAnimationState = new AnimationState();
    public int Lvl2AttackAnimationTimeout = 0;

    //////////
    // MISC //
    //////////

    public BabTwoEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
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
        this.entityData.define(ATTACKING, false);
        this.entityData.define(TAMED, false);
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
                        babThreeEntity.setTame(true);
                        babThreeEntity.tame((Player) this.getOwner());
                        babThreeEntity.moveTo(blockPos, babThreeEntity.getYRot(), babThreeEntity.getXRot());
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
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));

    }

    public static AttributeSupplier.Builder createBabTwoAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 0.8f)
                .add(Attributes.ATTACK_DAMAGE, 12.0);
    }

    ///////////////
    // ANIMATION //
    ///////////////


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
    ////////


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
        if (bl) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                target.setSecondsOnFire(2 * (int) f);
            }
        }
        setAggressive(true);
        System.out.println(ATTACKING);
        return bl;
    }





    ///////////
    // TAMED //
    ///////////

    @Override
    public void setTame(boolean tamed) {
        this.entityData.set(TAMED, tamed);
        super.setTame(tamed);
    }



    ///////////////
    // COLLISION //
    ///////////////

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




}




