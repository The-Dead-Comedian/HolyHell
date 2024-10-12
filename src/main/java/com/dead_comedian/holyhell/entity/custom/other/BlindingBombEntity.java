package com.dead_comedian.holyhell.entity.custom.other;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlindingBombEntity extends ThrownItemEntity {


    public BlindingBombEntity(EntityType<? extends BlindingBombEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlindingBombEntity(World world, LivingEntity owner) {
        super(HolyHellEntities.BLINDING_BOMB, owner, world);
    }

    public BlindingBombEntity(World world, double x, double y, double z) {
        super(HolyHellEntities.BLINDING_BOMB, x, y, z, world);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        Box userHitbox = new Box(entity.getBlockPos()).expand(3);

        List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class, userHitbox);
        for(LivingEntity i : list){

            i.addStatusEffect(new StatusEffectInstance(HolyHellEffects.CONFUSION, 200 ,1));

        }
    }
    protected void explode(double power) {
        this.explode((DamageSource)null, power);
    }
    protected void explode(@Nullable DamageSource damageSource, double power) {
        if (!this.getWorld().isClient) {
            double d = Math.sqrt(power);
            if (d > 5.0) {
                d = 5.0;
            }

            this.getWorld().createExplosion(this, damageSource, (ExplosionBehavior)null, this.getX(), this.getY(), this.getZ(), (float)(4.0 + this.random.nextDouble() * 1.5 * d), false, World.ExplosionSourceType.TNT);
            this.discard();
        }

    }

    @Override
    public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            Entity entity = this;

            Box userHitbox = new Box(entity.getBlockPos()).expand(3);

            List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class, userHitbox);
            for(LivingEntity i : list){

                i.addStatusEffect(new StatusEffectInstance(HolyHellEffects.CONFUSION, 200 ,1));

            }
            this.discard();
        }
        this.explode(0);

    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }


}
