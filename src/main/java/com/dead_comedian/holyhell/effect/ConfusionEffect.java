package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.registries.HolyHellSounds;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


import net.minecraft.entity.mob.HostileEntity;


import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.function.Predicate;

public class ConfusionEffect extends StatusEffect {
    public static final Predicate<Entity> IS_PLAYER = entity -> (entity instanceof ServerPlayerEntity);

    public ConfusionEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof HostileEntity) {
            LivingEntity target = ((HostileEntity) pLivingEntity).getTarget();
            if (target == null) {
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
            for (LivingEntity i : list) {
                if (entity instanceof HostileEntity) {

                    ((HostileEntity) entity).setTarget(null);

                    ((HostileEntity) entity).setTarget(i);

                }
            }
        }

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        List<Entity> list_of_living_things_nearby = entity.getWorld().getOtherEntities(entity, entity.getBoundingBox().expand(15), IS_PLAYER);

        StopSoundS2CPacket stopSoundS2CPacket = new StopSoundS2CPacket(HolyHellSounds.CLARITY_MUSIC.getId(), SoundCategory.RECORDS);
        if (entity instanceof ServerPlayerEntity) {
            list_of_living_things_nearby.add(entity);
        }

        for (Entity clientBoi : list_of_living_things_nearby) {
            if (clientBoi instanceof ServerPlayerEntity Boi) {
                Boi.networkHandler.sendPacket(stopSoundS2CPacket);
            }
        }
    }


    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}
