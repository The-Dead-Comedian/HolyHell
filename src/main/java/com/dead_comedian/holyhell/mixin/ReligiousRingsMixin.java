package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import com.dead_comedian.holyhell.entity.custom.other.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import javax.swing.text.html.parser.Entity;
import java.util.Objects;

@Mixin(PlayerEntity.class)

public abstract class ReligiousRingsMixin extends LivingEntity {


    @Shadow
    @Final
    private PlayerInventory inventory;

    protected ReligiousRingsMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);

    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "HEAD"))

    private float modifyDamage(float value, DamageSource source) {
        float a;

        if (this.hasStatusEffect(HolyHellEffects.JESISTANCE)) {
            a = this.getStatusEffect(HolyHellEffects.JESISTANCE).getAmplifier();

        } else {
            a = 0;
        }

        if (source.getAttacker() != null) {

            return source.getAttacker().getType().isIn(HolyhellTags.Entities.MAGIC_DEALING_MOBS) ||
                    source.isIn(HolyhellTags.DamageTypes.MAGIC_DAMAGE) ? value * (1 - (((float) 20 / 100) * a)) : value;
        }
        return value * (1 - (((float) 20 / 100) * a));

    }


}

