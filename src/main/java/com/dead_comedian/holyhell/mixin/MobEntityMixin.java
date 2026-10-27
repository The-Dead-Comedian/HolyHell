package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import com.dead_comedian.holyhell.server.registries.HolyhellDataComps;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntity {
    public MobEntityMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Inject(method = "mobInteract", at = @At(value = "HEAD"), cancellable = true)
    private void holyhell$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        LivingEntity that = ((LivingEntity) (Object) this);
        if (that instanceof Monster monster) {
            if (player.getItemInHand(hand).get(HolyhellDataComps.GRAIL_LEVEL) != null && player.getItemInHand(hand).get(HolyhellDataComps.GRAIL_LEVEL) >= 3) {
                monster.setData(HolyHellAttachments.KNIGHTED, 1000);
                monster.setData(HolyHellAttachments.KNIGHTER, player.getStringUUID());
                player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(), 30);
                player.getItemInHand(hand).set(HolyhellDataComps.GRAIL_LEVEL, 0);
            }

        }
    }


}
