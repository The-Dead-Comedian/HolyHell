package com.dead_comedian.holyhell.effect;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.ai.ConfusionAggroGoal;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;


public class ConfusionEffect extends MobEffect {
    protected final RandomSource random = RandomSource.create();

    public ConfusionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);
        Minecraft.getInstance().player.level().playSound(null, livingEntity.blockPosition(), HolyHellSounds.FLASHBANG.get(), SoundSource.PLAYERS, 1, 1);

        if (Minecraft.getInstance().player != null) {
            if (livingEntity.level().getServer() != null) {
                ServerLevel serverLevel = livingEntity.level().getServer().overworld();
                for (ServerPlayer serverPlayer : serverLevel.players()) {
                    if (serverPlayer.getStringUUID().equals(Minecraft.getInstance().player.getStringUUID())) {
                        System.out.println("goog");
                        if (livingEntity.is(serverPlayer)) {
                            serverPlayer.playSound(HolyHellSounds.FLASHBANG.get());
                            serverPlayer.setData(HolyHellAttachments.FLASHBANG, true);
                            System.out.println("pluh");
                        }

                    }
                }
            }


        }
        if (livingEntity instanceof Mob) {
            ((Mob) livingEntity).goalSelector.addGoal(2, new ConfusionAggroGoal((Mob) livingEntity));
        }
    }

}