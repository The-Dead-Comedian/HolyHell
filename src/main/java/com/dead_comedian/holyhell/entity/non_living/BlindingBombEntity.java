package com.dead_comedian.holyhell.entity.non_living;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.world.entity.projectile.Snowball;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class BlindingBombEntity extends ThrowableItemProjectile {




    public BlindingBombEntity(Level world, LivingEntity owner) {
        super(HolyHellEntities.BLINDING_BOMB.get(), owner, world);
    }

    public BlindingBombEntity(Level world, double x, double y, double z) {
        super(HolyHellEntities.BLINDING_BOMB.get(), x, y, z, world);
    }

    public BlindingBombEntity(EntityType<BlindingBombEntity> blindingBombEntityEntityType, Level level) {
        super(blindingBombEntityEntityType,level);
    }


    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        AABB userHitbox = new AABB(entity.blockPosition()).inflate(3);

        List<LivingEntity> list = entity.level().getEntitiesOfClass(LivingEntity.class, userHitbox);
        for(LivingEntity i : list){

            i.addEffect(new MobEffectInstance(HolyHellEffects.CONFUSION.get(), 200 ,1));

        }
        list.clear();
    }
    protected void explode(double power) {
        this.explode((DamageSource)null, power);
    }
    protected void explode(@Nullable DamageSource damageSource, double power) {
        if (!this.level().isClientSide) {
            double d = Math.sqrt(power);
            if (d > 5.0) {
                d = 5.0;
            }

            this.level().explode(this, damageSource, (ExplosionDamageCalculator)null, this.getX(), this.getY(), this.getZ(), (float)(4.0 + this.random.nextDouble() * 1.5 * d), false, Level.ExplosionInteraction.TNT);
            this.discard();
        }

    }

    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }




    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            Entity entity = this;

            AABB userHitbox = new AABB(entity.blockPosition()).inflate(3);

            List<LivingEntity> list = entity.level().getEntitiesOfClass(LivingEntity.class, userHitbox);
            for(LivingEntity i : list){

                i.addEffect(new MobEffectInstance(HolyHellEffects.CONFUSION.get(), 200 ,1));

            }
            list.clear();
            this.discard();
        }
        this.explode(-1);

    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }
}