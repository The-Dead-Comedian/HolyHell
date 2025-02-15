package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin(Player.class)

public abstract class GlobularDomePosMixin extends LivingEntity {


    protected GlobularDomePosMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }

    @Inject(method = "tick", at = @At(value = "HEAD"))

    private void tick(CallbackInfo ci) {
        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (this.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                this.teleportToWithTicket(x, entity.getY(), z);


            }

        }


    }


}