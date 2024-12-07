package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.other.GlobularDomeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)

public abstract class GlobularDomePosMixin extends LivingEntity {


    protected GlobularDomePosMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);

    }

    @Inject(method = "tick", at = @At(value = "HEAD"))

    private void tick(CallbackInfo ci) {
        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(-0.1));
        for (Entity entity : entityBelow) {
            if (this.collidesWith(entity) && entity instanceof GlobularDomeEntity) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                this.teleport(x, entity.getY(), z);


            }

        }


    }


}

