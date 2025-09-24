package com.dead_comedian.holyhell.entity;



import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;


public class CherubEntity extends Monster implements FlyingAnimal {
    ///////////////
    // VARIABLES //
    ///////////////

    public boolean hasSpawnedBab;
    public int flutterLoop = 24;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    double capacity = 20;
    int wave_level = 0;
    int current = 0;


    /*
     * 0 = Kamikaze
     * 1 = Angel
     * 2 = Heretic
     * 3 = Devout (paladin for now)(none existent for now)
     * */
    int[] mobSpawnIndex = new int[]{5, 3, 4};


    //////////
    // MISC //
    //////////

    public CherubEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.hasSpawnedBab = false;

        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
        this.setPathfindingMalus(PathType.FENCE, -1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        flutterLoop--;
        if (flutterLoop >= 23) {
            this.playSound(HolyHellSounds.CHERUB_FLUTTER.get(), 1F, 1F);
        }
        if (flutterLoop <= 0) {
            flutterLoop = 24;
        }


        if (wave_level == 1) {


            LivingEntity lastAttacker = this.getLastAttacker();
            double spawnY = 0;
            if (lastAttacker != null) {
                spawnY = lastAttacker.getY() - 4;
            }
            int retries = 0;


            int radius = 12;
            boolean b;
            capacity = 20 * 1.3;


            do {

                double angle = random.nextDouble() * 2 * Math.PI;
                double spawnX = radius * Math.cos(angle);
                double spawnZ = radius * Math.sin(angle);

                int mobIndex = random.nextInt(3);


                BlockPos centerPos = this.blockPosition();
                BlockPos spawnPos = new BlockPos((int) (centerPos.getX() + spawnX), (int) spawnY, (int) (centerPos.getZ() + spawnZ));

                if (!hasSpawnedBab) {
                    BabOneEntity babOneEntity = new BabOneEntity(HolyHellEntities.BAB_ONE.get(), this.level());
                    this.level().addFreshEntity(babOneEntity);
                    babOneEntity.moveTo(spawnPos, babOneEntity.getYRot(), babOneEntity.getXRot());

                    hasSpawnedBab = true;
                }

                if (!this.level().getBlockState(spawnPos).isAir() || this.level().getBlockState(spawnPos.below()).isAir()) {
                    while (retries < 10) {
                        spawnY++;
                        spawnPos = new BlockPos((int) spawnX, (int) spawnY, (int) spawnZ);
                        retries++;
                    }
                }

                if (!this.level().getBlockState(spawnPos).isAir()) {
                    while (retries == 10) {
                        radius--;
                        spawnPos = new BlockPos((int) spawnX, (int) spawnY, (int) spawnZ);
                        retries++;
                    }
                }



                switch (mobIndex) {
                    case 0:
                        KamikazeEntity kamikazeEntity = new KamikazeEntity(HolyHellEntities.KAMIKAZE.get(), this.level());
                        this.level().addFreshEntity(kamikazeEntity);
                        kamikazeEntity.moveTo(spawnPos, kamikazeEntity.getYRot(), kamikazeEntity.getXRot());
                        kamikazeEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                    case 1:
                        AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL.get(), this.level());
                        this.level().addFreshEntity(angelEntity);
                        angelEntity.moveTo(spawnPos, angelEntity.getYRot(), angelEntity.getXRot());
                        angelEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                    case 2:
                        HereticEntity hereticEntity = new HereticEntity(HolyHellEntities.HERETIC.get(), this.level());
                        this.level().addFreshEntity(hereticEntity);
                        hereticEntity.moveTo(spawnPos, hereticEntity.getYRot(), hereticEntity.getXRot());
                        hereticEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                }
                b = capacity * 1.3 > current;

            } while (b);


            if (current > capacity) {
                current = 0;
                wave_level = 0;
                this.playSound(HolyHellSounds.BELL_RING.get(),1F,1F);
                this.discard();
            }
        }
        if (wave_level == 2) {


            LivingEntity lastAttacker = this.getLastAttacker();
            double y = 0;
            if (lastAttacker != null) {
                y = lastAttacker.getY();
            }
            int retries = 0;


            int radius = 7;
            boolean b;
            capacity = 20 * 1.4;

            do {

                double angle = random.nextDouble() * 2 * Math.PI;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);

                int mobIndex = random.nextInt(3);


                BlockPos centerPos = this.blockPosition();
                BlockPos spawnPos = new BlockPos((int) (centerPos.getX() + x), (int) y, (int) (centerPos.getZ() + z));

                if (!hasSpawnedBab) {
                    BabOneEntity babOneEntity = new BabOneEntity(HolyHellEntities.BAB_ONE.get(), this.level());
                    this.level().addFreshEntity(babOneEntity);
                    babOneEntity.moveTo(spawnPos, babOneEntity.getYRot(), babOneEntity.getXRot());

                    hasSpawnedBab = true;
                }

                if (!this.level().getBlockState(spawnPos).isAir()) {
                    while (retries < 10) {
                        y++;
                        spawnPos = new BlockPos((int) x, (int) y, (int) z);
                        retries++;
                    }
                }

                if (!this.level().getBlockState(spawnPos).isAir()) {
                    while (retries == 10) {
                        radius--;
                        spawnPos = new BlockPos((int) x, (int) y, (int) z);
                        retries++;
                    }
                }

                switch (mobIndex) {
                    case 0:
                        KamikazeEntity kamikazeEntity = new KamikazeEntity(HolyHellEntities.KAMIKAZE.get(), this.level());
                        this.level().addFreshEntity(kamikazeEntity);
                        kamikazeEntity.moveTo(spawnPos, kamikazeEntity.getYRot(), kamikazeEntity.getXRot());
                        kamikazeEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                    case 1:
                        AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL.get(), this.level());
                        this.level().addFreshEntity(angelEntity);
                        angelEntity.moveTo(spawnPos, angelEntity.getYRot(), angelEntity.getXRot());
                        angelEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                    case 2:
                        HereticEntity hereticEntity = new HereticEntity(HolyHellEntities.HERETIC.get(), this.level());
                        this.level().addFreshEntity(hereticEntity);
                        hereticEntity.moveTo(spawnPos, hereticEntity.getYRot(), hereticEntity.getXRot());
                        hereticEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20));
                        current = current + mobSpawnIndex[mobIndex];
                        break;
                }


                b = capacity * 1.3 > current;

            } while (b);


            if (current > capacity) {
                current = 0;
                wave_level = 0;
                this.playSound(HolyHellSounds.BELL_RING.get(),1F,1F);
                this.discard();
            }
        }
        if (wave_level == 3) {


            LivingEntity lastAttacker = this.getLastAttacker();
            double y = 0;
            if (lastAttacker != null) {
                y = lastAttacker.getY();
            }
            int retries = 0;


            int radius = 7;
            boolean b;
            capacity = 20 * 3;


            do {

                double angle = random.nextDouble() * 2 * Math.PI;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);

                int a = random.nextInt(4);

                BlockPos centerPos = this.blockPosition();
                BlockPos spawnPos = new BlockPos((int) (centerPos.getX() + x), (int) y, (int) (centerPos.getZ() + z));
                if (!hasSpawnedBab) {
                    BabOneEntity babOneEntity = new BabOneEntity(HolyHellEntities.BAB_ONE.get(), this.level());
                    this.level().addFreshEntity(babOneEntity);
                    babOneEntity.moveTo(spawnPos, babOneEntity.getYRot(), babOneEntity.getXRot());

                    hasSpawnedBab = true;
                }
                if (!this.level().getBlockState(spawnPos).isAir()) {
                    while (retries < 10) {
                        y++;
                        spawnPos = new BlockPos((int) x, (int) y, (int) z);
                        retries++;
                    }
                }

                if (!this.level().getBlockState(spawnPos).isAir()) {
                    while (retries == 10) {
                        radius--;
                        spawnPos = new BlockPos((int) x, (int) y, (int) z);
                        retries++;
                    }
                }

                    if (a == 0) {

                    KamikazeEntity kamikazeEntity = new KamikazeEntity(HolyHellEntities.KAMIKAZE.get(), this.level());
                    this.level().addFreshEntity(kamikazeEntity);
                    kamikazeEntity.moveTo(spawnPos, kamikazeEntity.getYRot(), kamikazeEntity.getXRot());
                    current = current + mobSpawnIndex[a];
                }

                b = capacity * 1.3 > current;

            } while (b);


            if (current > capacity) {
                current = 0;
                wave_level = 0;
                this.playSound(HolyHellSounds.BELL_RING.get(),1F,1F);
                this.discard();
            }
        }


    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4f));
        this.goalSelector.addGoal(1, new CherubWanderAroundGoal());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes().add(Attributes.MAX_HEALTH, 15f).add(Attributes.FLYING_SPEED, 1).add(Attributes.MOVEMENT_SPEED, 0.7).add(Attributes.FOLLOW_RANGE, 15.0);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel) this.level()).sendParticles(HolyhellParticles.SOUND_RING.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
        }

        if (pAmount <= 9) {
            wave_level = 1;
        } else if (pAmount <= 15) {
            wave_level = 2;
        } else if (pAmount > 15) {
            wave_level = 3;
        }


        return super.hurt(pSource, pAmount);

    }


    ////////////////
    // NAVIGATION //
    ////////////////


    @Override
    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world) {
            public boolean isStableDestination(BlockPos pos) {
                return !this.level.getBlockState(pos.below()).isAir();
            }
        };
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(false);
        birdNavigation.setCanPassDoors(true);
        return birdNavigation;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    // fall
    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        fallDistance = 0;
    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(25) + 50;

            this.idleAnimationState.start(this.tickCount);


        } else {
            --this.idleAnimationTimeout;
        }


    }

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f);
    }


    class CherubWanderAroundGoal extends Goal {
        CherubWanderAroundGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return CherubEntity.this.navigation.isDone() && CherubEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return CherubEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3d = this.getRandomLocation();
            if (vec3d != null) {
                CherubEntity.this.navigation.moveTo(CherubEntity.this.navigation.createPath(BlockPos.containing(vec3d), 1), 1.0);
            }

        }

        @Nullable
        private Vec3 getRandomLocation() {
            Vec3 vec3d2;
            vec3d2 = CherubEntity.this.getViewVector(0.0F);

            Vec3 vec3d3 = HoverRandomPos.getPos(CherubEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964F, 3, 1);
            return vec3d3 != null ? vec3d3 : AirAndWaterRandomPos.getPos(CherubEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }

    }
}