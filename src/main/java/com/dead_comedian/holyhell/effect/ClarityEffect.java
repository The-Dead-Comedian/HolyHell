package com.dead_comedian.holyhell.effect;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ClarityEffect extends MobEffect {
    public static final Predicate<Entity> IS_PLAYER = entity -> (entity instanceof ServerPlayer);

    public ClarityEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }



    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();
            double xv = pLivingEntity.getDeltaMovement().x;
            double yv = pLivingEntity.getDeltaMovement().y;
            double zv = pLivingEntity.getDeltaMovement().z;
            pLivingEntity.teleportRelative(x, y + 0.005, z);
            pLivingEntity.setDeltaMovement(xv, yv + 0.05, zv);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
        return false;
    }

    @Override
    public void onMobRemoved(LivingEntity livingEntity, int amplifier, Entity.RemovalReason reason) {
        super.onMobRemoved(livingEntity, amplifier, reason);

        List<Entity> list_of_living_things_nearby = livingEntity.level().getEntities(livingEntity, livingEntity.getBoundingBox().inflate(15), IS_PLAYER);

        if (livingEntity instanceof ServerPlayer) {
            list_of_living_things_nearby.add(livingEntity);
        }

        for (Entity clientBoi : list_of_living_things_nearby) {
            if (clientBoi instanceof ServerPlayer) {
                ServerPlayer Boi = (ServerPlayer) clientBoi;
            }
        }
    }


}