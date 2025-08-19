package com.dead_comedian.holyhell.item.custom;


import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;


public class SacrificialKatarItem extends SwordItem {


    public SacrificialKatarItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        double d0 = (double) (-Mth.sin(pPlayer.getYRot() * ((float) Math.PI / 180F)));
        double d1 = (double) Mth.cos(pPlayer.getYRot() * ((float) Math.PI / 180F));

        if (!pPlayer.hasEffect(HolyHellEffects.BLOODLUST)) {
            if (pPlayer.level() instanceof ServerLevel) {
                ((ServerLevel) pPlayer.level()).sendParticles(HolyhellParticles.SWEEP_ATTACK.get(), pPlayer.getX() + d0, pPlayer.getY(0.5D), pPlayer.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
            }

            pPlayer.addEffect(new MobEffectInstance(HolyHellEffects.BLOODLUST, 2000, 0));
            pPlayer.level().playSound(pPlayer, pPlayer.blockPosition(), HolyHellSound.SACRIFICE.get(), SoundSource.PLAYERS, 1f, 1f);

            this.damageItem(pPlayer.getItemInHand(pUsedHand), 1, pPlayer, player -> {
            });
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }

        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }
}