package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)

public abstract class SwordCrossEntityMixin extends LivingEntity {
    protected SwordCrossEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"))
    private void attack(Entity target, CallbackInfo ci) {

        ItemStack itemStack = this.getStackInHand(this.getActiveHand());
        if(itemStack.isOf(HolyHellItems.HOLY_GRAIL)){
            BlockPos blockPos = target.getBlockPos();
            SwordCrossEntity angelEntity = new SwordCrossEntity(HolyHellEntities.SWORD_CROSS, this.getWorld());
            this.getWorld().spawnEntity(angelEntity);
            angelEntity.refreshPositionAndAngles(blockPos, angelEntity.getYaw(), angelEntity.getPitch());

        }


    }

}
