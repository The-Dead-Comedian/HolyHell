package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.block.CandleholderBlock;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RevenantEntity extends Monster {


    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.BOOLEAN);

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    ///  ///////////////////////////////
    public boolean wololo;

    public void setWololo(boolean b) {
        wololo = b;
    }

    public boolean getWololo() {
        return wololo;
    }

    ///  ///////////////////////////////

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
    private static final EntityDataAccessor<Integer> WOLOLO_ANIMATION_TIMEOUT =
            SynchedEntityData.defineId(RevenantEntity.class, EntityDataSerializers.INT);

    public void setWololoAnimationTimeout(int wololoAnimationTimeout) {
        this.entityData.set(WOLOLO_ANIMATION_TIMEOUT, wololoAnimationTimeout);
    }

    public Integer getWololoAnimationTimeout() {
        return this.entityData.get(WOLOLO_ANIMATION_TIMEOUT);
    }

    /// ///////////////////////////////

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState catatonicAnimationState = new AnimationState();
    public final AnimationState catatonicRiseAnimationState = new AnimationState();
    public final AnimationState catatonicSitAnimationState = new AnimationState();

    public final AnimationState wololoAnimationState = new AnimationState();


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

        this.goalSelector.addGoal(1, new RevenantGetWeaponGoal(this, 1, 50, HolyHellBlocks.CANDLE_HOLDER.get()));
        this.goalSelector.addGoal(2, new RevenantArmedAttackGoal(this));
        this.goalSelector.addGoal(2, new RevenantMeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(3, new RevenantPlaceWeaponGoal(this, 1));
        this.goalSelector.addGoal(4, new RevenantRitualGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1D) {
            @Override
            public boolean canUse() {
                return !((RevenantEntity) (Object) this.mob).isCatatonic() && super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f){
            @Override
            public boolean canUse() {
                return !((RevenantEntity) (Object) this.mob).isCatatonic() && super.canUse();
            }
        });
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(ARMED, false);
        this.entityData.define(CATATONIC, false);
        this.entityData.define(HAS_TARGET, false);
        this.entityData.define(WOLOLO_ANIMATION_TIMEOUT, 0);
    }

    public static AttributeSupplier.Builder createRevenantAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    public void tick() {
        super.tick();

        this.setWololo((this.getWololoAnimationTimeout() < 39 && this.getWololoAnimationTimeout() > 0));


        if (!level().isClientSide) {
            this.setHasTarget(this.getTarget() != null);
        }
        if (!isArmed() && !this.hasTarget()) {
            this.idleAnimationState.stop();
            this.catatonicSitAnimationState.startIfStopped(this.tickCount);
            this.setCatatonic(true);
        } else {
            this.catatonicRiseAnimationState.startIfStopped(this.tickCount);
            this.setCatatonic(false);

        }

        if (this.getTarget() instanceof Player player) {
            if (player.isCreative()) {
                this.setTarget(null);
            }
        }
        if (this.getTarget() != null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }
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
            if (this.catatonicSitAnimationTimeout >= 19 && !getWololo()) {
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
            this.getNavigation().stop();
            this.catatonicRiseAnimationTimeout++;
        } else {
            this.catatonicRiseAnimationTimeout = 0;
        }
        if (this.catatonicRiseAnimationTimeout >= 20) {
            this.catatonicRiseAnimationState.stop();
        }

        if (!this.isArmed()) {
            if (this.isAttacking() && attackAnimationTimeout <= 0) {
                idleAnimationState.stop();
                attackAnimationTimeout = 20; // Length in ticks of your animation
                attackAnimationState.start(this.tickCount);
            } else {
                --this.attackAnimationTimeout;
            }

            if (!this.isAttacking()) {
                attackAnimationState.stop();
            }
        }

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
            if (revenantEntity.getTarget() != null && !revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS)) {

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
                    && revenantEntity.hasTarget()
                    && !revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
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
            return isArmed()
                    && ((revenantEntity.getTarget() == null
                    || revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS)));
        }

        @Override
        public boolean canContinueToUse() {
            return this.canUse();
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
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 0), 3);
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos().above(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 1), 3);
                    revenantEntity.level().setBlock(revenantEntity.getHolderPos().above(2), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 2), 3);
                    revenantEntity.getLookControl().setLookAt(
                            revenantEntity.getHolderPos().getX(),
                            revenantEntity.getHolderPos().getY(),
                            revenantEntity.getHolderPos().getZ()
                    );
                }
            }
            if (revenantEntity.getHolderPos() == null) {
                revenantEntity.setArmed(false);
                revenantEntity.level().setBlock(revenantEntity.blockPosition(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 0), 3);
                revenantEntity.level().setBlock(revenantEntity.blockPosition().above(), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 1), 3);
                revenantEntity.level().setBlock(revenantEntity.blockPosition().above(2), HolyHellBlocks.CANDLE_HOLDER.get().defaultBlockState().setValue(CandleholderBlock.PIECE, 2), 3);

            }
        }

        @Override
        public void stop() {
            revenantEntity.getNavigation().stop();
        }
    }

    public class RevenantRitualGoal extends Goal {

        public List<LivingEntity> nearbyTargets;
        RevenantEntity revenantEntity;

        public RevenantRitualGoal(RevenantEntity entity) {
            revenantEntity = entity;
            nearbyTargets = revenantEntity.level()
                    .getEntitiesOfClass(LivingEntity.class,
                            new AABB(revenantEntity.getX() + 30,
                                    revenantEntity.getY() + 4,
                                    revenantEntity.getZ() + 30,
                                    revenantEntity.getX() - 30,
                                    revenantEntity.getY() - 4,
                                    revenantEntity.getZ() - 30), livingEntity -> livingEntity.getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS));
        }

        @Override
        public boolean canUse() {
            return !revenantEntity.isArmed()
                    && revenantEntity.getTarget() == null;
        }

        @Override
        public void start() {
            this.getTarget();
            if (revenantEntity.getTarget() != null) {
                revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !revenantEntity.isArmed()
                    && revenantEntity.getTarget() != null
                    && revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public void stop() {
            revenantEntity.setWololo(false);
            revenantEntity.setWololoAnimationTimeout(0);
            super.stop();
        }

        @Override
        public void tick() {
            nearbyTargets = revenantEntity.level()
                    .getEntitiesOfClass(LivingEntity.class,
                            new AABB(revenantEntity.getX() + 30,
                                    revenantEntity.getY() + 4,
                                    revenantEntity.getZ() + 30,
                                    revenantEntity.getX() - 30,
                                    revenantEntity.getY() - 4,
                                    revenantEntity.getZ() - 30));


            if (!this.revenantEntity.hasTarget()) {
                this.getTarget();
            }

            if (this.revenantEntity.getTarget() != null) {
                double distanceSq = revenantEntity.distanceToSqr(Vec3.atCenterOf(revenantEntity.getTarget().getOnPos()));

                revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
                if (distanceSq > 2.0) {
                    revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
                } else {
                    revenantEntity.getNavigation().stop();
                    if (revenantEntity.getTarget() instanceof PathfinderMob) {
                        ((PathfinderMob) revenantEntity.getTarget()).getNavigation().stop();
                    }

                    if (revenantEntity.getWololoAnimationTimeout() < 39) {

                        revenantEntity.setWololoAnimationTimeout(revenantEntity.getWololoAnimationTimeout() + 1);
                    } else {

                        revenantEntity.getTarget().discard();
                        revenantEntity.setWololoAnimationTimeout(0);
                        this.stop();
                    }

                    revenantEntity.getLookControl().setLookAt(
                            revenantEntity.getTarget().getX(),
                            revenantEntity.getTarget().getY(),
                            revenantEntity.getTarget().getZ()
                    );
                }
            }
            super.tick();
        }

        public void getTarget() {
            for (LivingEntity entity : nearbyTargets) {
                if (entity.getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS)) {
                    if (!entity.isCustomNameVisible() && !revenantEntity.hasTarget()) {
                        revenantEntity.setTarget(entity);

                    }
                    if (revenantEntity.hasTarget()) {
                        nearbyTargets.removeAll(nearbyTargets);
                    }
                }
            }
        }
    }

    public class RevenantArmedAttackGoal extends Goal {
        public RevenantEntity revenantEntity;

        public int anticipationTimeout = 0;

        public RevenantArmedAttackGoal(RevenantEntity entity) {
            revenantEntity = entity;
        }

        @Override
        public void start() {
            this.revenantEntity.getTarget();
            if (revenantEntity.getTarget() != null) {
                revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
            }
        }

        @Override
        public boolean canUse() {
            return revenantEntity.isArmed()
                    && revenantEntity.getTarget() != null
                    && !revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);

        }

        @Override
        public boolean canContinueToUse() {
            return revenantEntity.isArmed()
                    && revenantEntity.getTarget() != null
                    && !revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public void tick() {


            if (revenantEntity.getBoundingBox().intersects(this.revenantEntity.getTarget().getBoundingBox())) {
                revenantEntity.getTarget().hurt(revenantEntity.level().damageSources().mobAttack(revenantEntity), 10);
            }

            double distanceSq = revenantEntity.distanceTo(revenantEntity.getTarget());

            revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
            if (distanceSq > 2.0) {

                revenantEntity.getNavigation().moveTo(revenantEntity.getTarget(), 1);
            } else {

                revenantEntity.getLookControl().setLookAt(this.revenantEntity.getTarget());
                if (anticipationTimeout > 10) {

                    revenantEntity.getNavigation().stop();
                    revenantEntity.getLookControl().setLookAt(this.revenantEntity.getTarget());
                    revenantEntity.addDeltaMovement(revenantEntity.getLookAngle());

                    anticipationTimeout = 0;
                } else {

                    anticipationTimeout++;
                }
            }

            super.tick();
        }
    }

    public class RevenantMeleeAttackGoal extends MeleeAttackGoal {
        private final RevenantEntity entity;
        private int attackDelay = 10;
        private int ticksUntilNextAttack = 10;
        private boolean shouldCountTillNextAttack = false;

        public RevenantMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            entity = ((RevenantEntity) pMob);
        }

        @Override
        public boolean canUse() {
            return !entity.isArmed()
                    && entity.getTarget() != null
                    && !entity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public boolean canContinueToUse() {
            return !entity.isArmed()
                    && entity.getTarget() != null
                    && !entity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public void start() {
            super.start();
            attackDelay = 10;
            ticksUntilNextAttack = 10;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
                shouldCountTillNextAttack = true;

                if (isTimeToStartAttackAnimation()) {
                    entity.setAttacking(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                shouldCountTillNextAttack = false;
                entity.setAttacking(false);
                entity.attackAnimationTimeout = 0;
            }
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected boolean isTimeToStartAttackAnimation() {
            return this.ticksUntilNextAttack <= attackDelay;
        }

        protected int getTicksUntilNextAttack() {
            return this.ticksUntilNextAttack;
        }


        protected void performAttack(LivingEntity pEnemy) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
        }

        @Override
        public void tick() {
            super.tick();
            if (shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            }
        }


        @Override
        public void stop() {
            entity.setAttacking(false);
            super.stop();
        }
    }
}