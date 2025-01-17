package com.dead_comedian.holyhell.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;

import java.util.List;
import java.util.function.Predicate;

public class ConfusionEffect extends StatusEffect {
    public static final Predicate<Entity> IS_PLAYER = entity -> (entity instanceof ServerPlayerEntity);
    protected final Random random = Random.create();

    public ConfusionEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof HostileEntity hostileEntity) {
            if (hostileEntity.getTarget() != null) {
                changeTarget(pLivingEntity);
            }
        }

        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        changeTarget(entity);
    }


    public void changeTarget(LivingEntity entity) {

        if (entity.hasStatusEffect(this)) {
            Box userHitbox = new Box(entity.getBlockPos()).expand(50);

            List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class, userHitbox);
            int a = random.nextInt(list.size());
            for (LivingEntity i : list) {


                if (entity != null && list != null) {
                    if (entity instanceof HostileEntity hostileEntity && list.get(a) != entity && list.get(a) != hostileEntity.getTarget()) {
                        hostileEntity.targetSelector.getGoals().removeIf(ActiveTargetGoal -> hostileEntity.hasStatusEffect(this));
                        hostileEntity.setTarget(null);

                        hostileEntity.setTarget(list.get(a));
                    }
                }
            }
        }

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if (entity instanceof HostileEntity hostileEntity) {
            hostileEntity.targetSelector.add(1, new ActiveTargetGoal(hostileEntity, PlayerEntity.class, true));
        }
    }


    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }


}
