package com.dead_comedian.holyhell.entity.ai;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;

import java.util.function.Predicate;

public class ConfusionAggroGoal extends Goal {

    public TargetingConditions targetPredicate;
    public Mob entity;
    public ConfusionAggroGoal(Mob pMob) {
        super();
        this.entity=pMob;
        this.targetPredicate = TargetingConditions.forCombat().range(this.entity.getAttributeValue(Attributes.FOLLOW_RANGE)).selector((Predicate<LivingEntity>) targetPredicate);

    }

    @Override
    public boolean canUse() {
        return entity.hasEffect(HolyHellEffects.CONFUSION.get());
    }

    @Override
    public void tick() {
        entity.setTarget( this.entity.level().getNearestEntity(this.entity.level().getEntitiesOfClass(Monster.class, this.getSearchBox(this.entity.getAttributeValue(Attributes.FOLLOW_RANGE)), (livingEntity) -> true), this.targetPredicate, this.entity, this.entity.getX(), this.entity.getEyeY(), this.entity.getZ()));
    }
    protected AABB getSearchBox(double distance) {
        return this.entity.getBoundingBox().inflate(distance, 1.0, distance);
    }
    @Override
    public boolean canContinueToUse() {
        return entity.hasEffect(HolyHellEffects.CONFUSION.get());
    }
}
