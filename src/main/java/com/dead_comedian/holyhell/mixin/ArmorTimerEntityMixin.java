package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.item.custom.EvangelistArmorItem;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
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

public abstract class ArmorTimerEntityMixin extends LivingEntity {
    protected ArmorTimerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attack(Entity target, CallbackInfo ci) {

        for (ItemStack armorStack : ((Player) (Object) this).getArmorSlots()) {

            if ((armorStack.getItem() instanceof EvangelistArmorItem)) {
                ((EvangelistArmorItem) armorStack.getItem()).setTime(0);

            }
        }


    }

}
