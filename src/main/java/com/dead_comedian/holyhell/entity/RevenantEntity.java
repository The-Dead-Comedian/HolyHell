package com.dead_comedian.holyhell.entity;


import com.dead_comedian.holyhell.block.CandleholderBlock;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;

public class RevenantEntity extends Monster {

    private int timeTillCatatonic = 0;

    public int getTimeTillCatatonic() {
        return timeTillCatatonic;
    }

    public void setTimeTillCatatonic(int timeTillCatatonic) {
        this.timeTillCatatonic = timeTillCatatonic;
    }

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

    public int catatonicRiseAnimationTimeout = 0;
    public int catatonicSitAnimationTimeout = 0;


    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);


        int[] holderPos;
        if (this.getHolderPos() != null) {
            holderPos = new int[]{
                    this.getHolderPos().getX(),
                    this.getHolderPos().getY(),
                    this.getHolderPos().getZ()};
        } else {
            holderPos = new int[]{
                    (int) this.getX(),
                    (int) this.getZ(),
                    (int) this.getY()
            };
        }

        nbt.putBoolean("armed", this.isArmed());
        nbt.putIntArray("holder_pos", holderPos);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setArmed(nbt.getBoolean("armed"));
        this.setHolderPos(new BlockPos(
                nbt.getIntArray("holder_pos")[0],
                nbt.getIntArray("holder_pos")[1],
                nbt.getIntArray("holder_pos")[2]));

    }

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
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f) {
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

            if (this.getTimeTillCatatonic() >= 20) {
                this.idleAnimationState.stop();
                this.catatonicSitAnimationState.startIfStopped(this.tickCount);
                if (this.catatonicSitAnimationTimeout == 1) {

                    this.level().playLocalSound(this.blockPosition(), HolyHellSound.REVENANT_RISE.get(), SoundSource.HOSTILE, 0.8F, this.getVoicePitch(), true);
                }
                this.setCatatonic(true);
            } else {
                this.setTimeTillCatatonic(this.getTimeTillCatatonic() + 1);
            }
        } else {
            this.setTimeTillCatatonic(0);
            this.setCatatonic(false);
            this.catatonicRiseAnimationState.startIfStopped(this.tickCount);
            if (this.catatonicRiseAnimationTimeout == 1) {
                this.level().playLocalSound(this.blockPosition(), HolyHellSound.REVENANT_RISE.get(), SoundSource.HOSTILE, 0.8F, this.getVoicePitch(), true);
            }
        }

        if (this.getTarget() instanceof Player player) {
            if (player.isCreative() && player.isSpectator()) {
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
        this.playSound(HolyHellSound.BAB_2_ATTACK.get(), 0.8F, 1F);
        setAggressive(true);
        return bl;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return this.isCatatonic() && !pSource.isCreativePlayer();
    }

    protected SoundEvent getStepSound() {
        return HolyHellSound.REVENANT_WALK.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.7F, this.getVoicePitch());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return HolyHellSound.METAL_HURT.get();
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
                    && this.revenantEntity.getTarget() != null
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
                if (distanceSq > 1.0) {
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
                if (distanceSq > 1.0) {
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
        public RevenantEntity revenantEntity;
        public AABB revenantAABB;
        public LivingEntity targetEntity;

        public RevenantRitualGoal(RevenantEntity entity) {
            this.revenantEntity = entity;
            this.nearbyTargets = this.revenantEntity.level()
                    .getEntitiesOfClass(LivingEntity.class,
                            new AABB(this.revenantEntity.getX() + 30,
                                    this.revenantEntity.getY() + 4,
                                    this.revenantEntity.getZ() + 30,
                                    this.revenantEntity.getX() - 30,
                                    this.revenantEntity.getY() - 4,
                                    this.revenantEntity.getZ() - 30), livingEntity -> livingEntity.getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS));
            this.revenantAABB = new AABB(this.revenantEntity.getX() + 3,
                    this.revenantEntity.getY() + 3,
                    this.revenantEntity.getZ() + 3,
                    this.revenantEntity.getX() - 3,
                    this.revenantEntity.getY() - 3,
                    this.revenantEntity.getZ() - 3);


        }

        @Override
        public boolean canUse() {
            return !this.revenantEntity.isArmed()
                    && this.revenantEntity.getTarget() == null;
        }

        @Override
        public void start() {
            this.getTarget();
            if (this.targetEntity != null) {
                this.revenantEntity.getNavigation().moveTo(this.targetEntity, 1);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.revenantEntity.isArmed()
                    && this.revenantEntity.getTarget() != null
                    && this.revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public void stop() {
            this.revenantEntity.setWololo(false);
            this.revenantEntity.setWololoAnimationTimeout(0);
            this.nearbyTargets.clear();
            super.stop();
        }

        @Override
        public void tick() {
            this.targetEntity = this.revenantEntity.getTarget();
            this.revenantAABB = new AABB(this.revenantEntity.getX() + 2,
                    this.revenantEntity.getY() + 2,
                    this.revenantEntity.getZ() + 2,
                    this.revenantEntity.getX() - 2,
                    this.revenantEntity.getY() - 2,
                    this.revenantEntity.getZ() - 2);
            this.nearbyTargets = this.revenantEntity.level()
                    .getEntitiesOfClass(LivingEntity.class,
                            new AABB(this.revenantEntity.getX() + 30,
                                    this.revenantEntity.getY() + 4,
                                    this.revenantEntity.getZ() + 30,
                                    this.revenantEntity.getX() - 30,
                                    this.revenantEntity.getY() - 4,
                                    this.revenantEntity.getZ() - 30));


            if (!this.revenantEntity.hasTarget()) {
                this.getTarget();
            }

            if (this.targetEntity != null) {
                this.revenantEntity.getNavigation().moveTo(this.targetEntity, 1);
                if (!this.revenantAABB.intersects(this.targetEntity.getBoundingBox())) {
                    this.revenantEntity.getNavigation().moveTo(this.targetEntity, 1);
                } else {
                    this.revenantEntity.getNavigation().stop();
                    if (this.targetEntity instanceof PathfinderMob) {
                        ((PathfinderMob) this.targetEntity).getNavigation().stop();
                    }

                    if (this.revenantEntity.getWololoAnimationTimeout() < 39) {
                        if (this.revenantEntity.getWololoAnimationTimeout() == 1) {
                            this.revenantEntity.level().playSound(this.revenantEntity, this.revenantEntity.blockPosition(), HolyHellSound.ULULU.get(), SoundSource.HOSTILE, 0.8f, this.revenantEntity.getVoicePitch());
                        }
                        this.revenantEntity.setWololoAnimationTimeout(revenantEntity.getWololoAnimationTimeout() + 1);
                    } else {
                        if (this.revenantEntity.level().getRandom().nextInt(0, 15) == 5) {
                            this.revenantEntity.level().playSound(this.revenantEntity, this.revenantEntity.blockPosition(), HolyHellSound.PERISH.get(), SoundSource.HOSTILE, 1.2f, this.revenantEntity.getVoicePitch());
                        }else if (this.revenantEntity.level().getRandom().nextInt(0, 30) == 21) {
                            this.revenantEntity.level().playSound(this.revenantEntity, this.revenantEntity.blockPosition(), HolyHellSound.DISSAPEAR.get(), SoundSource.HOSTILE, 1.2f, this.revenantEntity.getVoicePitch());
                        }else {
                            this.revenantEntity.level().playSound(this.revenantEntity, this.revenantEntity.blockPosition(), HolyHellSound.MOB_PASSES.get(), SoundSource.HOSTILE, 1.2f, this.revenantEntity.getVoicePitch());

                        }
                        this.targetEntity.discard();
                        this.revenantEntity.setWololoAnimationTimeout(0);
                        this.stop();
                    }

                    this.revenantEntity.getLookControl().setLookAt(
                            this.targetEntity.getX(),
                            this.targetEntity.getY(),
                            this.targetEntity.getZ()
                    );
                }
            }
            super.tick();
        }

        public void getTarget() {
            for (LivingEntity entity : this.nearbyTargets) {
                if (entity.getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS)) {
                    if (!entity.isCustomNameVisible() && !this.revenantEntity.hasTarget()) {
                        this.revenantEntity.setTarget(entity);

                    }
                }
            }
        }
    }

    public class RevenantArmedAttackGoal extends Goal {
        public RevenantEntity revenantEntity;
        public AABB revenantAABB;
        public AABB revenantThrustAABB;
        public LivingEntity targetEntity;

        public RevenantArmedAttackGoal(RevenantEntity entity) {
            revenantEntity = entity;
        }

        @Override
        public void start() {
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
            this.targetEntity = revenantEntity.getTarget();

            this.revenantThrustAABB = new AABB(this.revenantEntity.getX() + 3,
                    this.revenantEntity.getY() + 3,
                    this.revenantEntity.getZ() + 3,
                    this.revenantEntity.getX() - 3,
                    this.revenantEntity.getY() - 3,
                    this.revenantEntity.getZ() - 3);


            this.revenantAABB = new AABB(this.revenantEntity.getX() + 4,
                    this.revenantEntity.getY() + 4,
                    this.revenantEntity.getZ() + 4,
                    this.revenantEntity.getX() - 4,
                    this.revenantEntity.getY() - 4,
                    this.revenantEntity.getZ() - 4);


            if (revenantEntity.getBoundingBox().intersects(this.targetEntity.getBoundingBox())) {
                this.targetEntity.hurt(revenantEntity.level().damageSources().mobAttack(revenantEntity), 10);
            }


            revenantEntity.getNavigation().moveTo(this.targetEntity, 1);
            if (!this.revenantAABB.intersects(this.targetEntity.getBoundingBox())) {

                revenantEntity.getNavigation().moveTo(this.targetEntity, 1);
            } else {
                revenantEntity.getNavigation().stop();
                revenantEntity.getLookControl().setLookAt(this.targetEntity);
                if (this.revenantEntity.attackAnimationTimeout >= 17 && this.revenantEntity.attackAnimationTimeout <= 40) {
                    if (this.revenantThrustAABB.intersects(this.targetEntity.getBoundingBox())) {
                        this.revenantEntity.getLookControl().setLookAt(this.targetEntity);
                        this.revenantEntity.addDeltaMovement(new Vec3(
                                this.revenantEntity.getLookAngle().x * 2,
                                this.revenantEntity.getLookAngle().y * 2,
                                this.revenantEntity.getLookAngle().z * 2));
                        this.revenantEntity.level().playLocalSound(this.revenantEntity.blockPosition(), HolyHellSound.REVENANT_ATTACK.get(), SoundSource.HOSTILE, 0.8F, this.revenantEntity.getVoicePitch(), true);
                        if (!this.targetEntity.isBlocking()) {
                            this.targetEntity.hurt(this.revenantEntity.level().damageSources().mobAttack(this.revenantEntity), 15);
                        } else {
                            this.targetEntity.knockback(1.2, -this.targetEntity.getLookAngle().x, -this.targetEntity.getLookAngle().y);
                        }

                        this.revenantEntity.attackAnimationTimeout = 0;
                    }

                }

                revenantEntity.getLookControl().setLookAt(this.targetEntity);
                this.revenantEntity.addDeltaMovement(new Vec3(this.revenantEntity.getLookAngle().x * 2, this.revenantEntity.getLookAngle().y * 2, this.revenantEntity.getLookAngle().z * 2));


            }

            super.

                    tick();
        }
    }

    public class RevenantMeleeAttackGoal extends MeleeAttackGoal {
        private final RevenantEntity revenantEntity;
        private int attackDelay = 10;
        private int ticksUntilNextAttack = 10;
        private boolean shouldCountTillNextAttack = false;
        public AABB revenantAABB;

        public RevenantMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.revenantEntity = ((RevenantEntity) pMob);
        }

        @Override
        public boolean canUse() {
            return !this.revenantEntity.isArmed()
                    && this.revenantEntity.getTarget() != null
                    && !this.revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public boolean canContinueToUse() {
            return !this.revenantEntity.isArmed()
                    && this.revenantEntity.getTarget() != null
                    && !this.revenantEntity.getTarget().getType().is(HolyhellTags.Entities.REVENANT_TRANSCENDS);
        }

        @Override
        public void start() {
            super.start();
            this.attackDelay = 10;
            this.ticksUntilNextAttack = 10;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            this.revenantAABB = new AABB(this.revenantEntity.getX() + 2,
                    this.revenantEntity.getY() + 2,
                    this.revenantEntity.getZ() + 2,
                    this.revenantEntity.getX() - 2,
                    this.revenantEntity.getY() - 2,
                    this.revenantEntity.getZ() - 2);

            if (this.revenantAABB.intersects(pEnemy.getBoundingBox())) {
                this.shouldCountTillNextAttack = true;

                if (isTimeToStartAttackAnimation()) {
                    this.revenantEntity.setAttacking(true);
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                    performAttack(pEnemy);
                }
            } else {
                resetAttackCooldown();
                this.revenantEntity.getNavigation().moveTo(pEnemy, 1);
                this.shouldCountTillNextAttack = false;
                this.revenantEntity.setAttacking(false);
                this.revenantEntity.attackAnimationTimeout = 0;
            }
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
            if (this.shouldCountTillNextAttack) {
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            }
        }

        @Override
        public void stop() {
            this.revenantEntity.setAttacking(false);
            super.stop();
        }
    }
}