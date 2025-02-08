package com.dead_comedian.holyhell.mixin;


import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;


@Mixin(PlayerEntity.class)

public abstract class HolyShieldMixin extends LivingEntity {

    @Shadow
    protected abstract void spawnParticles(ParticleEffect parameters);

    protected HolyShieldMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    public boolean spawnParitcle = false;

    @Inject(method = "applyDamage", at = @At(value = "HEAD"))

    private void modifyDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (((PlayerEntity) (Object) this).isBlocking() && (
                ((PlayerEntity) (Object) this).getMainHandStack().isOf(HolyHellItems.HOLY_SHIELD) ||
                        ((PlayerEntity) (Object) this).getOffHandStack().isOf(HolyHellItems.HOLY_SHIELD))) {


            Box userHitbox = new Box(((PlayerEntity) (Object) this).getBlockPos()).expand(3);
            List<Entity> list = ((PlayerEntity) (Object) this).getWorld().getNonSpectatingEntities(Entity.class, userHitbox);
            for (Entity i : list) {
                if (i != ((PlayerEntity) (Object) this)) {
                    knockbackNearbyEntities(((PlayerEntity) (Object) this).getWorld(), ((PlayerEntity) (Object) this), i);
                    spawnParitcle=true;
                }
            }
        }
    }

    @Unique
    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(2013, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(3.5), getKnockbackPredicate(player, attacked)).forEach((entity) -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            double d = getKnockback(player, entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > 0.0) {
                entity.addVelocity(vec3d2.x * 0.1, 0.1, vec3d2.z * 0.1);
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity;
                    serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                }
            }

        });
    }


    @Unique
    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return (entity) -> {
            boolean var10000;
            boolean bl;
            boolean bl2;
            boolean bl3;
            label62:
            {
                bl = !entity.isSpectator();
                bl2 = entity != player && entity != attacked;
                bl3 = !player.isTeammate(entity);
                if (entity instanceof TameableEntity tameableEntity) {
                    if (tameableEntity.isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid())) {
                        var10000 = true;
                        break label62;
                    }
                }

                var10000 = false;
            }

            boolean bl4;
            label55:
            {
                bl4 = !var10000;
                if (entity instanceof ArmorStandEntity armorStandEntity) {
                    if (armorStandEntity.isMarker()) {
                        var10000 = false;
                        break label55;
                    }
                }

                var10000 = true;
            }

            boolean bl5 = var10000;
            boolean bl6 = attacked.squaredDistanceTo(entity) <= Math.pow(3.5, 2.0);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    @Unique
    private static double getKnockback(PlayerEntity player, LivingEntity attacked, Vec3d distance) {
        player.getWorld().addParticle(HolyhellParticles.LIGHT_RING, player.getX(),player.getY(),player.getZ(),1,1,1);
        System.out.println("x "+ player.getX() +"/n y:"+ player.getZ()+ "/n z:"+ player.getZ());
        return ((3.5 - distance.length()) * 0.699999988079071 * (double) (player.fallDistance > 5.0F ? 2 : 1) * (1.0 - attacked.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)) * 10  );

    }
}

