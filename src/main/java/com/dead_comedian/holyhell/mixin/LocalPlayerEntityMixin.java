package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.registries.*;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LocalPlayer.class)

public abstract class LocalPlayerEntityMixin extends LivingEntity {

    protected LocalPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }


    @Inject(method = "move", at = @At(value = "HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {

        //Globular Dome
        LocalPlayer player = ((LocalPlayer) (Object) this);
        List<Entity> entityBelow = (player.level().getEntities(player,
                player.getBoundingBox().inflate(-0.1)));
        for (Entity entity : entityBelow) {
            if (player.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                ci.cancel();
            }
        }
    }


}