
package com.dead_comedian.holyhell.entity.ai;

import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;


public class HereticAttackGoal extends MeleeAttackGoal {


    private final HailingHereticEntity entity;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 20;
    private boolean shouldCountTillNextAttack = false;
    public static boolean render = false;
    public HereticAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = ((HailingHereticEntity) mob);
    }

    @Override
    public void start() {
        super.start();

        attackDelay = 20;
        ticksUntilNextAttack = 20;
    }


    @Override
    protected void attack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;



            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                pEnemy.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS , 20 , 99));
                render = true;

                performAttack(pEnemy);
            }
        } else {
            render = false;

            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }
    public static void safetyMeasure(){
        int tick1 = 0;
        while(true){
            if(render){

            tick1++;
            System.out.println(tick1);
            if (tick1 == 20  ){
                tick1 = 0;
                render = false;

            }
        }
        }
    }
public static boolean shouldRender(){
        return render;
}
    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getSquaredMaxAttackDistance(pEnemy);
    }


    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.getTickCount(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected void performAttack(LivingEntity pEnemy) {

        this.resetAttackCooldown();
        this.mob.swingHand(Hand.MAIN_HAND);
        this.mob.tryAttack(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            if(ticksUntilNextAttack == 0) {
                entity.setAttacking(false);
            }
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
