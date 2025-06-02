package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;

import com.dead_comedian.holyhell.item.custom.EvangelistArmorItem;
import com.dead_comedian.holyhell.registries.*;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)

public abstract class PlayerEntityMixin extends LivingEntity {


    @Shadow
    @Final
    private Abilities abilities;

    @Shadow
    public abstract Iterable<ItemStack> getArmorSlots();

    @Shadow
    public abstract Inventory getInventory();

    @Unique
    int holyhell$blockingCounter = 0;


    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }


    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo ci) {

//
//        this.abilities.mayfly = this.getInventory().getArmor(1).is(HolyHellItems.EVANGELIST_LEGGINGS.get());
//        if(!this.abilities.mayfly){
//            this.abilities.flying=false;
//        }

        if (((Player) (Object) this).isBlocking() && (((Player) (Object) this).getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get()) || ((Player) (Object) this).getOffhandItem().is(HolyHellItems.HOLY_SHIELD.get()))) {


            holyhell$blockingCounter++;
            System.out.println(holyhell$blockingCounter);


        } else {
            holyhell$blockingCounter = 0;
        }


        //Globular Dome
        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (this.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                double x = this.getX();
                double z = this.getZ();
                this.teleportToWithTicket(x, entity.getY(), z);


            }
            entityBelow.removeAll(entityBelow);

        }

        //Religious Rings
        if (this.hasEffect(HolyHellEffects.JESISTANCE.get())) {
            MobEffectInstance effectually = this.getEffect(HolyHellEffects.JESISTANCE.get());
            if (effectually.getDuration() >= 2000) {
                this.level().playSound((Player) null, this.blockPosition(), HolyHellSound.RINGS_INTRO.get(), SoundSource.PLAYERS, 0.2f, 1);
                return;
            }
            if (effectually.getDuration() > 3.5 * 20) {
                if (tickCount % 70 == 1) {
                    this.level().playSound((Player) null, this.blockPosition(), HolyHellSound.RINGS_HOLD.get(), SoundSource.PLAYERS, 0.2f, 1);
                }
                return;
            }
            if (effectually.getDuration() < 3.5 * 20 && effectually.getDuration() != 0) {
                if (tickCount % 70 == 1) {
                    this.level().playSound((Player) null, this.blockPosition(), HolyHellSound.RINGS_OUTRO.get(), SoundSource.PLAYERS, 0.2f, 1);

                }
            }
        }
    }

    @ModifyVariable(method = "hurt", at = @At(value = "HEAD"))
    private float modifyDamage(float value, DamageSource source) {

        //Jesistence
        if (source.getEntity() != null) {

            return source.getEntity().getType().is(HolyhellTags.Entities.MAGIC_DEALING_MOBS) || source.is(HolyhellTags.DamageTypes.MAGIC_DAMAGE) ? 0 : value;
        }

        //Globular Dome
        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (this.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                return 0;

            }

        }
        entityBelow.removeAll(entityBelow);
        return value;

    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attack(Entity target, CallbackInfo ci) {
        //Sword Cross
        ItemStack itemStack = this.getItemInHand(this.getUsedItemHand());
//        if (itemStack.is(HolyHellItems.HOLY_GRAIL.get())) {
//            this.level().playSound(this, this.blockPosition(), HolyHellSound.SWORD_SLASH.get(), SoundSource.PLAYERS, 0.5f, 1f);
//        }
        if (itemStack.is(HolyHellItems.SACRIFICIAL_KATAR.get())) {

            this.level().playSound(this, this.blockPosition(), HolyHellSound.SWORD_SLASH.get(), SoundSource.PLAYERS, 0.5f, 2f);

        }
        //Armor Timer
        for (ItemStack armorStack : ((Player) (Object) this).getArmorSlots()) {

            if ((armorStack.getItem() instanceof EvangelistArmorItem)) {
                ((EvangelistArmorItem) armorStack.getItem()).setTime(0);

            }
        }


    }

    //Shield
    @Unique
    public boolean holyhell$spawnParticle = false;

    @Inject(method = "hurt", at = @At(value = "HEAD"))
    private void modifyDamage(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        if (((Player) (Object) this).isBlocking() && (((Player) (Object) this).getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get()) || ((Player) (Object) this).getOffhandItem().is(HolyHellItems.HOLY_SHIELD.get()))) {
            {

                if (damageSource.getEntity() instanceof HereticEntity heretic && holyhell$blockingCounter <= 20) {

                    if (heretic.level() instanceof ServerLevel world) {
                        world.sendParticles(HolyhellParticles.STUN.get(), heretic.getX(), heretic.getEyeY() + 0.3F, heretic.getZ() - 0.5, 1, 0, 0.1, 0, 1);

                        world.sendParticles(HolyhellParticles.STUN2.get(), heretic.getX(), heretic.getEyeY() + 0.3F, heretic.getZ() + 0.5, 1, 0, 0.1, 0, 1);
                    }
                    heretic.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255));

                }


                if (((Player) (Object) this).level() instanceof ServerLevel world) {
                    world.sendParticles(HolyhellParticles.SOUND_RING.get(), ((Player) (Object) this).getX(), ((Player) (Object) this).getEyeY(), ((Player) (Object) this).getZ(), 1, 0, 0.1, 0, 0);
                }
            }
        }
    }


}