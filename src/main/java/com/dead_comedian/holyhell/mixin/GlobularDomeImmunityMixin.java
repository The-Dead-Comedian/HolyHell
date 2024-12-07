package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.GlobularDomeEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(PlayerEntity.class)

public abstract class GlobularDomeImmunityMixin extends LivingEntity {


    protected GlobularDomeImmunityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);

    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "HEAD"))

    private float modifyDamage(float value, DamageSource source) {
        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(-0.1));
        for (Entity entity : entityBelow) {
            if (this.collidesWith(entity) && entity instanceof GlobularDomeEntity) {
                System.out.println(0);
                return 0;

            }

        }
        System.out.println(value);
        return value;

    }


}

