package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)

public abstract class SwordCrossEntityMixin extends LivingEntity {
    protected SwordCrossEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;crit(Lnet/minecraft/world/entity/Entity;)V"))
    private void attack(Entity target, CallbackInfo ci) {

        ItemStack itemStack = this.getItemInHand(this.getUsedItemHand());
        if(itemStack.is(HolyHellItems.HOLY_GRAIL)){
            BlockPos blockPos = target.blockPosition();
            SwordCrossEntity angelEntity = new SwordCrossEntity(HolyHellEntities.SWORD_CROSS, this.level());
            this.level().addFreshEntity(angelEntity);
            angelEntity.moveTo(blockPos, angelEntity.getYRot(), angelEntity.getXRot());

        }


    }

}
