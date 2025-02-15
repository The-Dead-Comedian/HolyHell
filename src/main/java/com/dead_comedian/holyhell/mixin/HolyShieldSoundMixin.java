package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class HolyShieldSoundMixin {
    @Inject(
            method = "handleEntityEvent",
            at = @At(value = "HEAD"), cancellable = true)
    private void holyhell$HandleEntityEventCustom(byte pId, CallbackInfo ci) {
        if (pId == 29) {
            LivingEntity that = ((LivingEntity) (Object) this);
            if (that.getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get())) {
                that.playSound(HolyHellSound.HOLY_SHIELD_BLOCK.get(), 1F, 0.8F + that.level().random.nextFloat() * 0.4F);
                ci.cancel();
                return;
            }
            return;
        }
        return;
    }
}
