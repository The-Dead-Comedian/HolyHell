package com.dead_comedian.holyhell.entity.custom;



import com.dead_comedian.holyhell.entity.custom.other.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.EnumSet;

public class AngelEntity extends HostileEntity {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;


    //////////
    // MISC //
    //////////

    public AngelEntity(EntityType<? extends HostileEntity> entityType, World world) {
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

        this.goalSelector.add(1, new ShootBulletGoal());

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
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


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 ) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.age);



        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.startIfNotRunning(this.age);



        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
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
            DataTracker.registerData(AngelEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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


    ///////////////
    // SUMMONING //
    ///////////////

    public class ShootBulletGoal extends Goal {
        private int counter;

        public ShootBulletGoal() {
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = AngelEntity.this.getTarget();
            if (livingEntity != null && livingEntity.isAlive()) {

                return true;
            } else {
                return false;
            }
        }

        public void start() {
            this.counter = 20;

        }

        public void stop() {

        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            if (AngelEntity.this.getWorld().getDifficulty() != Difficulty.PEACEFUL) {
                --this.counter;
                LivingEntity livingEntity = AngelEntity.this.getTarget();
                if (livingEntity != null) {
                    AngelEntity.this.getLookControl().lookAt(livingEntity, 180.0F, 180.0F);
                    double d = AngelEntity.this.squaredDistanceTo(livingEntity);
                    if (d < 50.0) {
                            if (this.counter <= 0) {
                                this.counter = 20 + AngelEntity.this.random.nextInt(10) * 20 / 2;
                                FireBallEntity fireballEntity = new FireBallEntity( HolyHellEntities.FIREBALL ,AngelEntity.this.getX(), AngelEntity.this.getY() + 1, AngelEntity.this.getZ(), AngelEntity.this.getWorld());



                                AngelEntity.this.getWorld().spawnEntity(fireballEntity);
                                fireballEntity.setVelocity(AngelEntity.this, AngelEntity.this.getPitch(), AngelEntity.this.getYaw(), 1.0F, 1.5F, 1.0F);

                            }
                    } else {
                        AngelEntity.this.setTarget((LivingEntity)null);
                    }

                    super.tick();
                }
            }
        }
    }



    }




