package com.dead_comedian.holyhell.effect;

import java.util.List;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class ConfusionEffect extends MobEffect {
    protected final RandomSource random = RandomSource.create();

    public ConfusionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Monster hostileEntity) {
            if (hostileEntity.getTarget() != null) {
                changeTarget(pLivingEntity);
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        changeTarget(entity);
    }


    public void changeTarget(LivingEntity entity) {

        if (entity.hasEffect(this)) {
            AABB userHitbox = new AABB(entity.blockPosition()).inflate(50);

            List<LivingEntity> list = entity.level().getEntitiesOfClass(LivingEntity.class, userHitbox);
            int a = random.nextInt(list.size());
            for (LivingEntity i : list) {


                if (entity != null && list != null) {
                    if (entity instanceof Monster hostileEntity && list.get(a) != entity && list.get(a) != hostileEntity.getTarget()) {
                        hostileEntity.targetSelector.getAvailableGoals().removeIf(ActiveTargetGoal -> hostileEntity.hasEffect(this));
                        hostileEntity.setTarget(null);

                        hostileEntity.setTarget(list.get(a));
                    }
                }
            }
        }

    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {

        if (entity instanceof Monster hostileEntity) {
            hostileEntity.targetSelector.addGoal(1, new NearestAttackableTargetGoal(hostileEntity, Player.class, true));
        }
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }


}