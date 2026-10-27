package com.dead_comedian.holyhell.server.entity;


import com.dead_comedian.holyhell.server.entity.ai.AngelAi;
import com.dead_comedian.holyhell.server.entity.non_living.AngelProjectileEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AngelEntity extends Monster implements RangedAttackMob {


    ///////////////
    // VARIABLES //
    ///////////////


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    //////////
    // MISC //
    //////////

    public AngelEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }


    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        if (pSource.getEntity() instanceof KamikazeEntity) {
            return true;
        } else {
            return super.isInvulnerableTo(pSource);
        }
    }


    @Override
    public void tick() {
        super.tick();


        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 25);
    }


    ///////////////
    // ANIMATION //

    /// ////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 && this.getPose() == Pose.STANDING) {
            this.idleAnimationTimeout = this.random.nextInt(30) + 60;

            this.idleAnimationState.start(this.tickCount);

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

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

        double radius = 1.5;
        double sideShootStrength = 0.9;

        Vec3 forward = this.getLookAngle();
        Vec3 flatForward = new Vec3(forward.x, 0, forward.z);

        if (flatForward.lengthSqr() < 0.001) {
            flatForward = new Vec3(0, 0, 1);
        }

        flatForward = flatForward.normalize();

        Vec3 right = new Vec3(-flatForward.z, 0, flatForward.x).normalize();

        Vec3 leftOffset = right.scale(-radius);
        Vec3 leftSpawn = this.position()
                .add(leftOffset)
                .add(0, this.getBbHeight() * 0.6, 0);

        AngelProjectileEntity leftProjectile =
                new AngelProjectileEntity(this.level(), this, target);

        leftProjectile.setPos(leftSpawn);
        leftProjectile.setDeltaMovement(right.scale(-sideShootStrength));

        this.level().addFreshEntity(leftProjectile);
    }


    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    @Override
    protected void customServerAiStep() {
        this.level().getProfiler().push("angelBrain");
        ((Brain<AngelEntity>) this.brain).tick((ServerLevel) this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("angelActivityUpdate");
        AngelAi.updateActivity(this);
        this.level().getProfiler().pop();

        super.customServerAiStep();
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return AngelAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Override
    protected Brain.Provider<AngelEntity> brainProvider() {
        return Brain.provider(AngelAi.MEMORY_MODULES, AngelAi.SENSORS);
    }

    @Override
    public Brain<AngelEntity> getBrain() {
        return (Brain<AngelEntity>) super.getBrain();
    }


    ///////////
    // SOUND //

    /// ////////


    protected SoundEvent getStepSound() {
        return HolyHellSounds.ANGEL_FLUTTER.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return HolyHellSounds.ANGEL_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return HolyHellSounds.ANGEL_HURT.get();
    }

}