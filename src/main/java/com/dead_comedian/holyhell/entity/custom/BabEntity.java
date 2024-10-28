package com.dead_comedian.holyhell.entity.custom;



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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class BabEntity extends HostileEntity {


    ///////////////
    // VARIABLES //
    ///////////////

    int level = 0;



    public final AnimationState Lvl1IdleAnimationState = new AnimationState();
    private int Lvl1IdleAnimationTimeout = 0;

    public final AnimationState Lvl2IdleAnimationState = new AnimationState();
    private int Lvl2IdleAnimationTimeout = 0;
    public final AnimationState Lvl2AttackAnimationState = new AnimationState();
    public int Lvl2AttackAnimationTimeout = 0;

    public final AnimationState Lvl3IdleAnimationState = new AnimationState();
    private int Lvl3IdleAnimationTimeout = 0;
    public final AnimationState Lvl3AttackAnimationState = new AnimationState();
    public int Lvl3AttackAnimationTimeout = 0;
    //////////
    // MISC //
    //////////

    public BabEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);

    }
    @Override
    public void tick() {
        super.tick();



        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(1 ,new FollowMobGoal(this,1D,1.0F, 20.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }
    public static DefaultAttributeContainer.Builder createAngelAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.8f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        
    }

    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.Lvl1IdleAnimationTimeout <= 0 ) {
            this.Lvl1IdleAnimationTimeout = this.random.nextInt(20) + 40;

            this.Lvl1IdleAnimationState.start(this.age);



        } else {
            --this.Lvl1IdleAnimationTimeout;
        }

        if(this.isAttacking() && Lvl2AttackAnimationTimeout <= 0) {
            Lvl2AttackAnimationTimeout = 40;
            Lvl2AttackAnimationState.startIfNotRunning(this.age);



        } else {
            --this.Lvl2AttackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            Lvl2AttackAnimationState.stop();
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
        private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

        public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }
        @Override
        public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }
        @Override
        public boolean tryAttack(Entity target){
        boolean bl = super.tryAttack(target);
        if(bl){
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            if(this.getMainHandStack().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F){
                target.setOnFireFor(2 * (int)f);
            }
        }
        setAttacking(true);
        return bl;
    }


    ///////////
    // LEVEL //
    ///////////


    @Override
    public boolean collidesWith(Entity other) {

        return super.collidesWith(other);
    }
}




