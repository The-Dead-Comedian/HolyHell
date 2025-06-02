//package com.dead_comedian.holyhell.entity.ai;
//
//import com.dead_comedian.holyhell.entity.DevoutEntity;
//import com.dead_comedian.holyhell.entity.non_living.FallingSwordEntity;
//import com.dead_comedian.holyhell.registries.HolyHellEntities;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.goal.Goal;
//
//
//public class DevoutAttackGoal extends Goal {
//
//    public DevoutEntity entity;
//    public int attackNo = 0;
//    int cooldown = 0;
//
//    private final double speedModifier;
//    private final boolean followingTargetEvenIfNotSeen;
//    private double pathedTargetX;
//    private double pathedTargetY;
//    private double pathedTargetZ;
//    private int ticksUntilNextPathRecalculation;
//    private int ticksUntilNextAttack;
//    private int failedPathFindingPenalty = 0;
//    private boolean canPenalize = false;
//
//
//
//
//    public DevoutAttackGoal(DevoutEntity pMob ,double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
//        super();
//        this.speedModifier = pSpeedModifier;
//        this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
//        this.entity = pMob;
//    }
//
//    @Override
//    public boolean canUse() {
//        return entity.getTarget() != null;
//    }
//
//
//    // attackNo = 1  = Summon rings
//    // attackNo = 2  = Melee Attack
//    // attackNo = 3  = heal
//
//
//    @Override
//    public void tick() {
//        super.tick();
//        chooseAttack();
//
//        if (attackNo == 1) {
//            cooldown++;
//
//
//            if (cooldown == 100) {
//                cooldown = 0;
//            }
//            FuckThisImDoingMyOwnSystem(cooldown);
//        } else if (attackNo == 2) {
//            this.entity.getLookControl().setLookAt(entity.getTarget(), 30.0F, 30.0F);
//            double d0 = this.entity.getPerceivedTargetDistanceSquareForMeleeAttack(entity.getTarget());
//            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
//            if ((this.followingTargetEvenIfNotSeen || this.entity.getSensing().hasLineOfSight(entity.getTarget())) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || entity.getTarget().distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.entity.getRandom().nextFloat() < 0.05F)) {
//                this.pathedTargetX = entity.getTarget().getX();
//                this.pathedTargetY = entity.getTarget().getY();
//                this.pathedTargetZ = entity.getTarget().getZ();
//                this.ticksUntilNextPathRecalculation = 4 + this.entity.getRandom().nextInt(7);
//                if (this.canPenalize) {
//                    this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
//                    if (this.entity.getNavigation().getPath() != null) {
//                        net.minecraft.world.level.pathfinder.Node finalPathPoint = this.entity.getNavigation().getPath().getEndNode();
//                        if (finalPathPoint != null && entity.getTarget().distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                            failedPathFindingPenalty = 0;
//                        else
//                            failedPathFindingPenalty += 10;
//                    } else {
//                        failedPathFindingPenalty += 10;
//                    }
//                }
//                if (d0 > 1024.0D) {
//                    this.ticksUntilNextPathRecalculation += 10;
//                } else if (d0 > 256.0D) {
//                    this.ticksUntilNextPathRecalculation += 5;
//                }
//
//                if (!this.entity.getNavigation().moveTo(entity.getTarget(), this.speedModifier)) {
//                    this.ticksUntilNextPathRecalculation += 15;
//                }
//
//                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
//            }
//
//            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
//            this.checkAndPerformAttack(entity.getTarget(), d0);    }
//
//
//
//    }
//
//
//    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
//        double d0 = this.getAttackReachSqr(pEnemy);
//        if (pDistToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
//            this.resetAttackCooldown();
//            this.entity.swing(InteractionHand.MAIN_HAND);
//            this.entity.doHurtTarget(pEnemy);
//        }
//
//    }
//
//    protected void resetAttackCooldown() {
//        this.ticksUntilNextAttack = this.adjustedTickDelay(20);
//    }
//
//    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
//        return (double) (this.entity.getBbWidth() * 2.0F * this.entity.getBbWidth() * 2.0F + pAttackTarget.getBbWidth());
//    }
//
//
//    public void chooseAttack() {
//        if (entity.getHealth() > entity.getMaxHealth() / 2) {
//            if (entity.distanceToSqr(entity.getTarget()) >= 10) {
//                attackNo = 1;
//            } else {
//                attackNo = 2;
//            }
//        } else if (entity.getHealth() <= entity.getMaxHealth() / 2) {
//            attackNo = 3;
//        }
//        entity.setAttackNo(attackNo);
//    }
//
//    public void summonSwordRing(Entity entity) {
//        int loop = 0;
//        Iterable<BlockPos> a = BlockPos.betweenClosed(-2, 3, -2, 2, 3, 2);
//        for (BlockPos b : a) {
//
//            FallingSwordEntity swordEntity = new FallingSwordEntity(HolyHellEntities.FALLING_SWORD.get(), entity.level());
//            entity.level().addFreshEntity(swordEntity);
//            swordEntity.moveTo(entity.blockPosition().offset(b), swordEntity.getYRot(), swordEntity.getXRot());
//
//            if (loop == 6 || loop == 7 || loop == 8 || loop == 11 || loop == 12 || loop == 13 || loop == 16 || loop == 17 || loop == 18) {
//                swordEntity.discard();
//            }
//            loop++;
//        }
//    }
//
//    public void FuckThisImDoingMyOwnSystem(int cooldown) {
//       LivingEntity targetEntity = entity.getTarget();
//
//
//        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {
//
//            entity.setAggressive(true);
//        } else {
//            if (cooldown == 40 && targetEntity != null && targetEntity.isAlive()) {
//                entity.setAggressive(false);
//            }
//        }
//        if (cooldown == 20 && targetEntity != null && targetEntity.isAlive()) {
//            summonSwordRing(entity.getTarget());
//        }
//    }
//
//}
