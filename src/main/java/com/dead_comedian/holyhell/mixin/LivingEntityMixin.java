package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    @Shadow
    public abstract void push(Entity pEntity);

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Inject(method="tick",at=@At(value = "HEAD"))
    private void bishbashbosh(CallbackInfo ci){
        if (((LivingEntity) (Object) this).hasEffect(HolyHellEffects.BLOODLUST) &&
                ((LivingEntity) (Object) this).hasEffect(MobEffects.REGENERATION)) {
            ((LivingEntity) (Object) this).removeEffect(MobEffects.REGENERATION);
        }
        if (((LivingEntity) (Object) this).hasEffect(HolyHellEffects.BLOODLUST) &&
                ((LivingEntity) (Object) this).hasEffect(MobEffects.HEALTH_BOOST)) {
            ((LivingEntity) (Object) this).removeEffect(MobEffects.HEALTH_BOOST);
        }
        if (((LivingEntity) (Object) this).hasEffect(HolyHellEffects.BLOODLUST) &&
                ((LivingEntity) (Object) this).hasEffect(MobEffects.HEAL)) {
            ((LivingEntity) (Object) this   ).removeEffect(MobEffects.HEAL);

        }
    }

    //Holy Shield Sound
    @Inject(
            method = "handleEntityEvent",
            at = @At(value = "HEAD"), cancellable = true)
    private void holyhell$HandleEntityEventCustom(byte pId, CallbackInfo ci) {
        if (pId == 29) {
            LivingEntity that = ((LivingEntity) (Object) this);
            if (that.getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get())) {
                that.playSound(HolyHellSound.HOLY_SHIELD_BLOCK.get(), 1F, 0.8F + that.level().random.nextFloat() * 0.4F);
                ci.cancel();
                return;
            }
            return;
        }
        return;
    }
}
