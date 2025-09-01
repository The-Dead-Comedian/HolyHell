package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;

import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.registries.*;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)

public abstract class PlayerEntityMixin extends LivingEntity {

    @Unique
    int holyhell$blockingCounter = 0;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }

    @Unique
    public int countArmorPieces(Player player, ArmorMaterial material) {
        int count = 0;

        // Iterate through all armor slots (helmet, chestplate, leggings, boots)
        for (ItemStack armorStack : player.getArmorSlots()) {
            if (!armorStack.isEmpty() && armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial().value() == material) {
                    count++;
                }
            }
        }

        return count;
    }


    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo ci) {

        if ((this.isBlocking() && this.getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get()) || this.getOffhandItem().is(HolyHellItems.HOLY_SHIELD.get()))) {

            holyhell$blockingCounter++;


        } else {
            holyhell$blockingCounter = 0;
        }

        //Globular Dome
        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(-0.1));
        for (Entity entity : entityBelow) {
            if (this.canCollideWith(entity) && entity instanceof GlobularDomeEntity) {
                double x = this.getX();
                double z = this.getZ();
                this.teleportRelative(x, entity.getY(), z);


            }


        }

        //Religious Rings
        if (this.hasEffect(HolyHellEffects.JESISTANCE)) {
            MobEffectInstance effectually = this.getEffect(HolyHellEffects.JESISTANCE);
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


    }

    @Inject(method = "die", at = @At(value = "HEAD"))
    private void die(DamageSource pCause, CallbackInfo ci) {

    }


    @Inject(method = "hurt", at = @At(value = "HEAD"), cancellable = true)
    private void modifyDamage(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {

        // Divine Prot
        if (this.hasEffect(HolyHellEffects.DIVINE_PROTECTION) && !damageSource.is(HolyhellTags.DamageTypes.DIVINE_PROTECTION_IGNORE)) {

            switch (countArmorPieces(((Player) (Object) this), HolyhellArmorMaterials.EVANGELIST.value())) {
                case 1:
                    this.addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN, 1200, 1));
                    this.level().playSound(null, ((Player) (Object) this).blockPosition(), HolyHellSound.DIVINE_PROTECTION.get(), SoundSource.PLAYERS);
                    break;
                case 2:
                    this.addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN, 1000, 2));
                    this.level().playSound(null, ((Player) (Object) this).blockPosition(), HolyHellSound.DIVINE_PROTECTION.get(), SoundSource.PLAYERS);
                    break;
                case 3:
                    this.addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN, 800, 3));
                    this.level().playSound(null, ((Player) (Object) this).blockPosition(), HolyHellSound.DIVINE_PROTECTION.get(), SoundSource.PLAYERS);
                    break;
                case 4:
                    this.addEffect(new MobEffectInstance(HolyHellEffects.DIVINE_PROTECTION_COOLDOWN, 600, 4));
                    this.level().playSound(null, ((Player) (Object) this).blockPosition(), HolyHellSound.DIVINE_PROTECTION.get(), SoundSource.PLAYERS);
                    break;

            }
            if (this.level() instanceof ServerLevel world) {
                world.sendParticles(HolyhellParticles.LIGHT_RING.get(), this.getX(), this.getEyeY(), this.getZ(), 1, 0, 0.1, 0, 0);

            }

            cir.cancel();

        }


        //holy shield
        if (this.isBlocking() && (this.getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get()) || this.getOffhandItem().is(HolyHellItems.HOLY_SHIELD.get()))) {
            {

                if (damageSource.getEntity() instanceof HereticEntity heretic && holyhell$blockingCounter <= 20) {

                    if (heretic.level() instanceof ServerLevel world) {
                        world.sendParticles(HolyhellParticles.STUN.get(), heretic.getX(), heretic.getEyeY() + 0.3F, heretic.getZ() - 0.5, 1, 0, 0.1, 0, 1);

                        world.sendParticles(HolyhellParticles.STUN2.get(), heretic.getX(), heretic.getEyeY() + 0.3F, heretic.getZ() + 0.5, 1, 0, 0.1, 0, 1);
                    }
                    heretic.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 255));
                    heretic.playSound(HolyHellSound.STUN.get(), 1F, 1F);
                }


                if (this.level() instanceof ServerLevel world) {
                    world.sendParticles(HolyhellParticles.SOUND_RING.get(), this.getX(), this.getEyeY(), this.getZ(), 1, 0, 0.1, 0, 0);
                }
            }
        }
    }


}