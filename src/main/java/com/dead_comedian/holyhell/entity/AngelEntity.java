package com.dead_comedian.holyhell.entity;



import com.dead_comedian.holyhell.entity.non_living.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.EnumSet;

public class AngelEntity extends Monster implements RangedAttackMob {


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

    public AngelEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);

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
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ShootFireBallGoal());
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAngelAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 0.8f)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.tickCount);


        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.startIfStopped(this.tickCount);


        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAggressive()) {
            attackAnimationState.stop();
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
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.BOOLEAN);

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
        return bl;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {


        Vec3 look = this.getLookAngle();
        double d0 = target.getX() - this.getX();
        double d2 = target.getZ() - this.getZ();

        FireBallEntity fireBallEntity = new FireBallEntity(HolyHellEntities.FIREBALL.get(), this.getX(), this.getY() + 1, this.getZ(), this.level());
        fireBallEntity.shoot(d0, look.y, d2, 1.0F, 16);
        this.level().addFreshEntity(fireBallEntity);

    }


    ///////////////
    // SUMMONING //
    ///////////////

    public class ShootFireBallGoal extends Goal {
        private int counter;

        public ShootFireBallGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingEntity = AngelEntity.this.getTarget();
            if (livingEntity != null && livingEntity.isAlive()) {

                return true;
            } else {
                return false;
            }
        }

        public void start() {
            this.counter = 80;

        }

        public void stop() {

        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (AngelEntity.this.level().getDifficulty() != Difficulty.PEACEFUL) {
                --this.counter;
                LivingEntity livingEntity = AngelEntity.this.getTarget();
                if (livingEntity != null) {
                    AngelEntity.this.getLookControl().setLookAt(livingEntity, 180.0F, 180.0F);
                    double d = AngelEntity.this.distanceToSqr(livingEntity);
                    if (d < 50.0) {
                        if (this.counter >= 65) {
                            AngelEntity.this.performRangedAttack(AngelEntity.this.getTarget(), 0);
                        }

                        if (this.counter == 0) {
                            this.counter = 80;
                        }


                    } else {
                        AngelEntity.this.setTarget((LivingEntity) null);
                    }

                    super.tick();
                }
            }
        }
    }


}