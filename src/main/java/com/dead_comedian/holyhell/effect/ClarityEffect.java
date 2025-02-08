package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.registries.HolyHellSounds;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ClarityEffect extends MobEffect {
    public static final Predicate<Entity> IS_PLAYER = entity -> (entity instanceof ServerPlayer);
    public ClarityEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            double x = pLivingEntity.getX();
            double y = pLivingEntity.getY();
            double z = pLivingEntity.getZ();
            double xv = pLivingEntity.getDeltaMovement().x;
            double yv = pLivingEntity.getDeltaMovement().y;
            double zv = pLivingEntity.getDeltaMovement().z;
            pLivingEntity.teleportToWithTicket(x, y + 0.005, z);
            pLivingEntity.setDeltaMovement(xv, yv + 0.05, zv);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier){
        entity.level().playSound(null, entity.blockPosition(), HolyHellSounds.CLARITY_MUSIC, SoundSource.RECORDS, 1f, 1f );
    }
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier){

        List<Entity> list_of_living_things_nearby = entity.level().getEntities(entity, entity.getBoundingBox().inflate(15), IS_PLAYER);

        ClientboundStopSoundPacket stopSoundS2CPacket = new ClientboundStopSoundPacket(HolyHellSounds.CLARITY_MUSIC.getLocation(), SoundSource.RECORDS);
        if(entity instanceof ServerPlayer){
            list_of_living_things_nearby.add(entity);
        }

        for (Entity clientBoi : list_of_living_things_nearby ) {
            if(clientBoi instanceof ServerPlayer) {
                ServerPlayer Boi = (ServerPlayer) clientBoi;
                Boi.connection.send(stopSoundS2CPacket);
            }}
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
