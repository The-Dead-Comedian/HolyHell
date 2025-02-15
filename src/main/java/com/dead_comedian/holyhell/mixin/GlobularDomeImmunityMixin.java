package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin(Player.class)

public abstract class GlobularDomeImmunityMixin extends LivingEntity {


    protected GlobularDomeImmunityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }

    @ModifyVariable(method = "hurt", at = @At(value = "HEAD"))

    private float modifyDamage(float value, DamageSource source) {
        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (this.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                return 0;

            }

        }

        return value;

    }


}