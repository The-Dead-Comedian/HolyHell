package com.dead_comedian.holyhell.mixin;


import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)

public abstract class PlayerEntityMixin {
    @Inject(method = "applyDamage", at = @At(value = "RETURN"))
    private void applyDamage(DamageSource source, float amount, CallbackInfo ci) {

        amount = ((PlayerEntity) (Object) this).modifyAppliedDamage(source, amount);


    }

}
