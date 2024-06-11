package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Clarityeffect extends StatusEffect {
    public static final Predicate<Entity> IS_PLAYER = entity -> (entity instanceof ServerPlayerEntity);
    public Clarityeffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.getWorld().isClient()) {
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();
            double xv = pLivingEntity.getVelocity().x;
            double yv = pLivingEntity.getVelocity().y;
            double zv = pLivingEntity.getVelocity().z;
            pLivingEntity.teleport(x, y + 0.005, z);
            pLivingEntity.setVelocity(xv, yv + 0.05, zv);
        }

        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }
    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier){
        entity.getWorld().playSound(null, entity.getBlockPos(), ModSounds.CLARITY_MUSIC, SoundCategory.RECORDS, 1f, 1f );
        //entity.getWorld().Client

    }
    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier){

        List<Entity> list_of_living_things_nearby = entity.getWorld().getOtherEntities(entity, entity.getBoundingBox().expand(15), IS_PLAYER);

        StopSoundS2CPacket stopSoundS2CPacket = new StopSoundS2CPacket(ModSounds.CLARITY_MUSIC.getId(), SoundCategory.RECORDS);
        if(entity instanceof ServerPlayerEntity){
            list_of_living_things_nearby.add(entity);
        }

        for (Entity clientBoi : list_of_living_things_nearby ) {
            if(clientBoi instanceof ServerPlayerEntity) {
                ServerPlayerEntity Boi = (ServerPlayerEntity) clientBoi;
                Boi.networkHandler.sendPacket(stopSoundS2CPacket);
            }}
    }


    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}
