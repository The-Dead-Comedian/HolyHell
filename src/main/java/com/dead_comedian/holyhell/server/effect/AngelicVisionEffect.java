package com.dead_comedian.holyhell.server.effect;


import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;


public class AngelicVisionEffect extends MobEffect {

    public AngelicVisionEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {

        return super.shouldApplyEffectTickThisTick(duration, amplifier);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        if(livingEntity instanceof Player){
            livingEntity.setData(HolyHellAttachments.ANGEL_VISION_TRANSITION,false);
        }
        super.onEffectAdded(livingEntity, amplifier);
    }
}