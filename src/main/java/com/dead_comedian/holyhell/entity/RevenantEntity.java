package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.block.CandleholderBlock;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class RevenantEntity extends Monster {

    public BlockPos holderPosition;

    public void setHolderPos(BlockPos holderPos) {
        holderPosition = holderPos;
    }

    public BlockPos getHolderPos() {
        return holderPosition;
    }

    ///  ///////////////////////////////

    private static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.BOOLEAN);

    public void setHasTarget(boolean hasTarget) {
        this.entityData.set(HAS_TARGET, hasTarget);
    }

    public boolean hasTarget() {
        return this.entityData.get(HAS_TARGET);
    }

    ///  ///////////////////////////////

    private static final EntityDataAccessor<Boolean> ARMED = SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.BOOLEAN);

    public void setArmed(boolean armed) {
        this.entityData.set(ARMED, armed);
    }

    public boolean isArmed() {
        return this.entityData.get(ARMED);
    }

    ///  ///////////////////////////////

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.BOOLEAN);

    public void setAggressive(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAggressive() {
        return this.entityData.get(ATTACKING);
    }


    /// ///////////////////////////////

    private static final EntityDataAccessor<Boolean> CATATONIC =
            SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.BOOLEAN);

    public void setCatatonic(boolean catatonic) {
        this.entityData.set(CATATONIC, catatonic);
    }

    public boolean isCatatonic() {
        return this.entityData.get(CATATONIC);
    }

    /// ///////////////////////////////

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public final AnimationState catatonicAnimationState = new AnimationState();
    public final AnimationState catatonicRiseAnimationState = new AnimationState();
    public final AnimationState catatonicSitAnimationState = new AnimationState();

    public int catatonicRiseAnimationTimeout = 0;
    public int catatonicSitAnimationTimeout = 0;


    /// ////////

    // MISC //

    /// ///////

    public RevenantEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(3, new RevenantGetWeaponGoal(this, 1, 50, HolyHellBlocks.CANDLE_HOLDER.get()));
        this.goalSelector.addGoal(3, new RevenantPlaceWeaponGoal(this,1));

        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(ARMED, false);
        this.entityData.define(CATATONIC, false);
        this.entityData.define(HAS_TARGET, false);

    }

    public static AttributeSupplier.Builder createRevenantAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {

        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide) {
            this.entityData.set(HAS_TARGET, this.getTarget() != null);
        }
        if (!isArmed() && !this.hasTarget()) {
            this.idleAnimationState.stop();
            this.catatonicSitAnimationState.startIfStopped(this.tickCount);
            this.setCatatonic(true);
        } else {
            this.catatonicRiseAnimationState.startIfStopped(this.tickCount);
            this.setCatatonic(false);

        }

        if (this.isCatatonic()) {
            this.navigation.stop();
        }

        if (this.level().isClientSide) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {

        if (!this.isCatatonic()) {
            this.catatonicAnimationState.stop();
            if (this.getDeltaMovement().x == 0 && this.getDeltaMovement().z == 0) {

                if (this.idleAnimationTimeout <= 0) {
                    this.idleAnimationTimeout = 53;
                    this.idleAnimationState.startIfStopped(this.tickCount);
                } else {
                    --this.idleAnimationTimeout;
                }
            } else {
                this.idleAnimationTimeout = 53;
                this.idleAnimationState.stop();
            }
        } else {
            this.idleAnimationState.stop();
            if (this.catatonicSitAnimationTimeout >= 19) {

                this.catatonicAnimationState.startIfStopped(this.tickCount);
            }
        }

        if (this.catatonicSitAnimationState.isStarted()) {
            this.catatonicSitAnimationTimeout++;
        } else {
            this.catatonicSitAnimationTimeout = 0;
        }
        if (this.catatonicSitAnimationTimeout >= 20) {
            this.catatonicSitAnimationState.stop();
        }

        if (this.catatonicRiseAnimationState.isStarted()) {
            this.catatonicRiseAnimationTimeout++;
        } else {
            this.catatonicRiseAnimationTimeout = 0;
        }
        if (this.catatonicRiseAnimationTimeout >= 20) {
            this.catatonicRiseAnimationState.stop();
        }


        // IGNORE THESE, THIS WAS A TEST
//        if (this.attackAnimationTimeout <= 27 && !this.isArmed() && this.isAggressive()) {
//            this.idleAnimationState.stop();
//            this.attackAnimationTimeout = this.random.nextInt(27) + 54;
//            this.attackAnimationState.start(this.tickCount);
//        } else if (this.attackAnimationTimeout <= 23 && this.isArmed() && this.isAggressive()) {
//            this.idleAnimationState.stop();
//            this.attackAnimationTimeout = this.random.nextInt(23) + 46;
//            this.attackAnimationState.start(this.tickCount);
//        } else {
//            --this.attackAnimationTimeout;
//        }
//
//
//        if (!this.isAggressive()) {
//            attackAnimationState.stop();
//        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
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
        this.playSound(HolyHellSound.BAB_2_ATTACK.get(), 1F, 1F);
        setAggressive(true);
        return bl;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return this.isCatatonic() && !pSource.isCreativePlayer();
    }


    public class RevenantGetWeaponGoal extends MoveToBlockGoal {
        private final RevenantEntity revenantEntity;
        private final double speed;
        private final int searchRadius;

        private final Block targetBlock;


        public RevenantGetWeaponGoal(RevenantEntity revenantEntity, double speed, int searchRadius, Block targetBlock) {
            super(revenantEntity, speed, searchRadius);
            this.revenantEntity = revenantEntity;
            this.speed = speed;
            this.searchRadius = searchRadius;
            this.targetBlock = targetBlock;

        }

        @Override
        public boolean canUse() {
            if (!revenantEntity.isCatatonic()) {

                Level level = revenantEntity.level();
                BlockPos mobPos = revenantEntity.blockPosition();

                for (BlockPos pos : BlockPos.betweenClosed(
                        mobPos.offset(-searchRadius, -2, -searchRadius),
                        mobPos.offset(searchRadius, 2, searchRadius))) {
                    if (level.getBlockState(pos).is(targetBlock)) {
                        revenantEntity.setHolderPos(pos.immutable());

                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return revenantEntity.getHolderPos() != null
                    && revenantEntity.level().getBlockState(revenantEntity.getHolderPos()).is(targetBlock)
                    && !revenantEntity.isArmed()
                    && !revenantEntity.isCatatonic();
        }

        @Override
        public void start() {
            if (revenantEntity.getHolderPos() != null) {
                revenantEntity.getNavigation().moveTo(
                        revenantEntity.getHolderPos().getX(),
                        revenantEntity.getHolderPos().getY(),
                        revenantEntity.getHolderPos().getZ(),
                        speed
                );
            }
        }

        @Override
        public void tick() {
            if (revenantEntity.getHolderPos() == null) return;

            double distanceSq = revenantEntity.distanceToSqr(Vec3.atCenterOf(revenantEntity.getHolderPos()));
            if (!revenantEntity.isArmed() && revenantEntity.hasTarget()) {
                if (distanceSq > 2.0) {
                    revenantEntity.getNavigation().moveTo(
                            revenantEntity.getHolderPos().getX(),
                            revenantEntity.getHolderPos().getY(),
                            revenantEntity.getHolderPos().getZ(),
                            speed
                    );
                } else {

                    revenantEntity.setArmed(true);
                    revenantEntity.level().destroyBlock(revenantEntity.getHolderPos(), false);
                    revenantEntity.level().destroyBlock(revenantEntity.getHolderPos().above(), false);
                    revenantEntity.level().destroyBlock(revenantEntity.getHolderPos().above(2), false);
                    revenantEntity.getLookControl().setLookAt(
                            revenantEntity.getHolderPos().getX(),
                            revenantEntity.getHolderPos().getY(),
                            revenantEntity.getHolderPos().getZ()
                    );
                }
            }
        }

        @Override
        public void stop() {
            revenantEntity.getNavigation().stop();
        }


        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(HolyHellBlocks.CANDLE_HOLDER.get());
        }

    }

    public class RevenantPlaceWeaponGoal extends Goal {
        private final RevenantEntity revenantEntity;
        private final double speed;

        public RevenantPlaceWeaponGoal(RevenantEntity revenantEntity, double speed) {
            super();
            this.revenantEntity = revenantEntity;
            this.speed = speed;
        }

        @Override
        public boolean canUse() {
            return isArmed();
        }

        @Override
        public boolean canContinueToUse() {
            return revenantEntity.isArmed() && revenantEntity.hasTarget();
        }


        @Override
        public void start() {
            if (revenantEntity.getHolderPos() != null) {
                revenantEntity.getNavigation().moveTo(
                        revenantEntity.getHolderPos().getX(),
                        revenantEntity.getHolderPos().getY(),
                        revenantEntity.getHolderPos().getZ(),
                        speed
                );
            }
        }

        @Override
        public void tick() {
            double distanceSq = revenantEntity.distanceToSqr(Vec3.atCenterOf(revenantEntity.getHolderPos()));
            if (revenantEntity.isArmed() && !revenantEntity.hasTarget()) {
                if (distanceSq > 2.0) {
                    revenantEntity.getNavigation().moveTo(
                            revenantEntity.getHolderPos().getX(),
                            revenantEntity.getHolderPos().getY(),
                            revenantEntity.getHolderPos().getZ(),
                            speed
                    );
                } else {

                    revenantEntity.setArmed(false);
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,0),3);
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos().above(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,1),3);
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos().above(2), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,2),3);
                    revenantEntity.getLookControl().setLookAt(
                            revenantEntity.getHolderPos().getX(),
                            revenantEntity.getHolderPos().getY(),
                            revenantEntity.getHolderPos().getZ()
                    );
                }
            }
            if (revenantEntity.getHolderPos()==null){
                revenantEntity.setArmed(false);
                revenantEntity.level().setBlock(revenantEntity.blockPosition(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,0),3);
                revenantEntity.level().setBlock(revenantEntity.blockPosition().above(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,1),3);
                revenantEntity.level().setBlock(revenantEntity.blockPosition().above(2), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE,2),3);

            }
        }

        @Override
        public void stop() {
            revenantEntity.getNavigation().stop();
        }
    }


}