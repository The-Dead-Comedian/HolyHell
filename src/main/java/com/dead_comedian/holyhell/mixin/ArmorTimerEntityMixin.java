package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.item.custom.EvangelistArmorItem;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
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

public abstract class ArmorTimerEntityMixin extends LivingEntity {
    protected ArmorTimerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attack(Entity target, CallbackInfo ci) {

        for (ItemStack armorStack : ((PlayerEntity) (Object) this).getArmorItems()) {

            if ((armorStack.getItem() instanceof EvangelistArmorItem)) {
                ((EvangelistArmorItem) armorStack.getItem()).setTime(0);

            }
        }


    }

}
