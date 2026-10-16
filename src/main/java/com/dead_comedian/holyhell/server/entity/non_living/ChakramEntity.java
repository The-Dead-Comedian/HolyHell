package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ChakramEntity extends AbstractArrow {

    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ChakramEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> REBOUND_LEVEL = SynchedEntityData.defineId(ChakramEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> BOUNCES = SynchedEntityData.defineId(ChakramEntity.class, EntityDataSerializers.INT);
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;


    public ChakramEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_FOIL, false);
        builder.define(REBOUND_LEVEL, 0);
        builder.define(BOUNCES, 0);
    }


    public ChakramEntity(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
        super(HolyHellEntities.CHAKRAM.get(), shooter, level, pickupItemStack, (ItemStack) null);
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    public boolean isFoil() {
        return (Boolean) this.entityData.get(ID_FOIL);
    }

    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }

    }

    public void playerTouch(Player entity) {
        if (this.ownedBy(entity) || this.getOwner() == null) {
            super.playerTouch(entity);
        }

    }

    public void tick() {

        Entity entity = this.getOwner();

        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015d, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.2d;
                this.setDeltaMovement(this.getDeltaMovement().scale(1).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }


    @Override
    protected double getDefaultGravity() {
        return super.getDefaultGravity();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() ? !(entity instanceof ServerPlayer) || !entity.isSpectator() : false;
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return this.entityData.get(BOUNCES) >= this.entityData.get(REBOUND_LEVEL) * 2 ? (this.dealtDamage ? null : super.findHitEntity(startVec, endVec)) : super.findHitEntity(startVec, endVec);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = 8.0F;
        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, (Entity) (entity1 == null ? this : entity1));
        Level var7 = this.level();
        if (var7 instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, f);
        }

        this.dealtDamage = true;
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            var7 = this.level();
            if (var7 instanceof ServerLevel) {
                ServerLevel serverlevel1 = (ServerLevel) var7;
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, entity, damagesource, this.getWeaponItem());
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity;
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        Vec3 vel = this.getDeltaMovement(); // capture BEFORE super overwrites it

        super.onHitBlock(result);
        this.inGround = false;
        this.shakeTime = 0;
        this.setNoPhysics(false);

        if (this.entityData.get(BOUNCES) >= this.entityData.get(REBOUND_LEVEL) * 2) {
            this.dealtDamage = true;
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        Vec3 pos = result.getLocation();
        Direction face = result.getDirection();

        for (int i = 0; i < 4; i++) {
            Vec3 n = new Vec3(face.getStepX(), face.getStepY(), face.getStepZ());
            vel = vel.subtract(n.scale(2 * vel.dot(n)));
            pos = pos.add(n.scale(0.05));

            BlockHitResult next = this.level().clip(new ClipContext(
                    pos, pos.add(vel), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (next.getType() == HitResult.Type.MISS) break;

            pos = next.getLocation();
            face = next.getDirection();
        }

        this.setPos(pos);
        this.setDeltaMovement(vel);
        this.hasImpulse = true;

        if (!this.level().isClientSide) {
            this.entityData.set(BOUNCES, this.entityData.get(BOUNCES) + 1);
            this.level().broadcastEntityEvent(this, (byte) 5);
        }
    }

    protected void hitBlockEnchantmentEffects(ServerLevel level, BlockHitResult hitResult, ItemStack stack) {
        Vec3 vec3 = hitResult.getBlockPos().clampLocationWithin(hitResult.getLocation());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingentity) {
            var10002 = livingentity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(level, stack, var10002, this, (EquipmentSlot) null, vec3, level.getBlockState(hitResult.getBlockPos()), (p_348680_) -> this.kill());
    }

    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    public ItemStack getWeaponItem() {
        return new ItemStack(HolyHellItems.CHAKRAM.get(), 1);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.dealtDamage = compound.getBoolean("DealtDamage");
        this.entityData.set(REBOUND_LEVEL, compound.getInt("rebound"));
        this.entityData.set(BOUNCES, compound.getInt("bounces"));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("DealtDamage", this.dealtDamage);
        compound.putInt("bounces", this.entityData.get(BOUNCES));
    }


    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(HolyHellItems.CHAKRAM.get());
    }
}
