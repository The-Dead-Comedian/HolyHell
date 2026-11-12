package com.dead_comedian.holyhell.server.entity.non_living;

import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellMemoryModules;
import com.dead_comedian.holyhell.server.registries.HolyHellParticles;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class LightArrowEntity extends Projectile {


    private static final EntityDataAccessor<Boolean> STAGNANT = SynchedEntityData.defineId(LightArrowEntity.class, EntityDataSerializers.BOOLEAN);

    public LightArrowEntity(EntityType<LightArrowEntity> judgementSpearEntityEntityType, Level level) {
        super(judgementSpearEntityEntityType, level);
    }

    public LightArrowEntity(EntityType<LightArrowEntity> judgementSpearEntityEntityType, Level level, Player target, double angle, int index) {
        super(judgementSpearEntityEntityType, level);

        this.index = index;
        this.angle = angle;
        this.setTargetId(target.getUUID());
    }

    public boolean getSagnant() {
        return this.entityData.get(STAGNANT);
    }

    public void setStagnant(boolean a) {
        this.entityData.set(STAGNANT, a);
    }


    private double angle = 0;
    private int index = 0;

    public int getIndex() {
        return index;
    }

    @Nullable
    private UUID targetId;

    private int stagnantTicks = 60;


    @Nullable
    public UUID getTargetId() {
        return targetId;
    }

    public void setTargetId(@Nullable UUID targetId) {
        this.targetId = targetId;
    }


    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (getTargetId() != null) {
            tag.putUUID("Target", getTargetId());
        }
        tag.putInt("StagnantTicks", stagnantTicks);


    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.hasUUID("Target")) {
            this.targetId = tag.getUUID("Target");
        }

        this.stagnantTicks = tag.getInt("StagnantTicks");
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

        builder.define(STAGNANT, true);
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        double d0 = packet.getXa();
        double d1 = packet.getYa();
        double d2 = packet.getZa();
        this.setDeltaMovement(d0, d1, d2);
    }

    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, float explosionPower) {
        return false;
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!this.level().isClientSide) {
            this.playSound(SoundEvents.SHULKER_BULLET_HURT, 1.0F, 1.0F);
            ((ServerLevel) this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2, 0.2, 0.2, 0.0);
            this.destroy();
        }

        return true;
    }

    private void destroy() {
        this.discard();
        this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }

    @Override
    public void tick() {

        super.tick();

        if (!this.level().isClientSide) {
            if (stagnantTicks > 0) {
                stagnantTicks--;
                if (stagnantTicks == 0) {
                    this.setDeltaMovement(new Vec3(-Math.cos(angle) * 2, -0.2, -Math.sin(angle) * 2));
                    this.hasImpulse = true;
                }


            }
            this.setStagnant(stagnantTicks > 0);
        }

        if (getTargetId() != null) {
            Player player = this.level().getPlayerByUUID(targetId);
            if (player != null) {

                double x = player.getX() + Math.cos(angle) * 2;
                double z = player.getZ() + Math.sin(angle) * 2;
                this.moveTo(x, player.getEyeY(), z, 0, 0);

                this.setYRot((float) (90f + Math.toDegrees(angle)));

            }

        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();
        if (!(entity instanceof AllSeerEntity) && !(entity instanceof LightArrowEntity)) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 10F);
            this.discard();
        }
    }


    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particle = HolyHellParticles.FIREBALL_IMPACT.get();
            this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }
}