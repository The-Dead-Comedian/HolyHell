package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(Player.class)

public abstract class ReligiousRingsMixin extends LivingEntity {

    protected ReligiousRingsMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);

    }

    @ModifyVariable(method = "hurt", at = @At(value = "HEAD"))

    private float modifyDamage(float value, DamageSource source) {
        float a;

        if (this.hasEffect(HolyHellEffects.JESISTANCE)) {
            a = this.getEffect(HolyHellEffects.JESISTANCE).getAmplifier();

        } else {
            a = 0;
        }

        if (source.getEntity() != null) {

            return source.getEntity().getType().is(HolyhellTags.Entities.MAGIC_DEALING_MOBS) ||
                    source.is(HolyhellTags.DamageTypes.MAGIC_DAMAGE) ? value * (1 - (((float) 20 / 100) * a)) : value;
        }
        return value * (1 - (((float) 20 / 100) * a));

    }


}

