package com.dead_comedian.holyhell.mixin;


import com.dead_comedian.holyhell.registries.HolyHellItems;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Player.class)

public abstract class HolyShieldMixin extends LivingEntity {



    protected HolyShieldMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Unique
    public boolean spawnParitcle = false;

    @Inject(method = "hurt", at = @At(value = "HEAD"))

    private void modifyDamage(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        if (((Player) (Object) this).isBlocking() && (
                ((Player) (Object) this).getMainHandItem().is(HolyHellItems.HOLY_SHIELD) ||
                        ((Player) (Object) this).getOffhandItem().is(HolyHellItems.HOLY_SHIELD))) {


            AABB userHitbox = new AABB(((Player) (Object) this).blockPosition()).inflate(3);
            List<Entity> list = ((Player) (Object) this).level().getEntitiesOfClass(Entity.class, userHitbox);
            for (Entity i : list) {
                if (i != ((Player) (Object) this)) {
                    knockbackNearbyEntities(((Player) (Object) this).level(), ((Player) (Object) this), i);
                    spawnParitcle=true;
                }
            }
        }
    }

    @Unique
    private static void knockbackNearbyEntities(Level world, Player player, Entity attacked) {
        world.levelEvent(2013, attacked.getOnPos(), 750);
        world.getEntitiesOfClass(LivingEntity.class, attacked.getBoundingBox().inflate(3.5), getKnockbackPredicate(player, attacked)).forEach((entity) -> {
            Vec3 vec3d = entity.position().subtract(attacked.position());
            double d = getKnockback(player, entity, vec3d);
            Vec3 vec3d2 = vec3d.normalize().scale(d);
            if (d > 0.0) {
                entity.push(vec3d2.x * 0.1, 0.1, vec3d2.z * 0.1);
                if (entity instanceof ServerPlayer) {
                    ServerPlayer serverPlayerEntity = (ServerPlayer) entity;
                    serverPlayerEntity.connection.send(new ClientboundSetEntityMotionPacket(serverPlayerEntity));
                }
            }

        });
    }


    @Unique
    private static Predicate<LivingEntity> getKnockbackPredicate(Player player, Entity attacked) {
        return (entity) -> {
            boolean var10000;
            boolean bl;
            boolean bl2;
            boolean bl3;
            label62:
            {
                bl = !entity.isSpectator();
                bl2 = entity != player && entity != attacked;
                bl3 = !player.isAlliedTo(entity);
                if (entity instanceof TamableAnimal tameableEntity) {
                    if (tameableEntity.isTame() && player.getUUID().equals(tameableEntity.getOwnerUUID())) {
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
                if (entity instanceof ArmorStand armorStandEntity) {
                    if (armorStandEntity.isMarker()) {
                        var10000 = false;
                        break label55;
                    }
                }

                var10000 = true;
            }

            boolean bl5 = var10000;
            boolean bl6 = attacked.distanceToSqr(entity) <= Math.pow(3.5, 2.0);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    @Unique
    private static double getKnockback(Player player, LivingEntity attacked, Vec3 distance) {
        player.level().addParticle(HolyhellParticles.LIGHT_RING, player.getX(),player.getY(),player.getZ(),1,1,1);
        System.out.println("x "+ player.getX() +"/n y:"+ player.getZ()+ "/n z:"+ player.getZ());
        return ((3.5 - distance.length()) * 0.699999988079071 * (double) (player.fallDistance > 5.0F ? 2 : 1) * (1.0 - attacked.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)) * 10  );

    }
}

