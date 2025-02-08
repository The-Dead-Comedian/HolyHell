package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class DomePart extends Entity {
    public final GlobularDomeEntity owner;
    public final String name;
    private final EntityDimensions partDimensions;

    public DomePart(GlobularDomeEntity owner, String name, float width, float height) {
        super(owner.getType(), owner.level());
        this.partDimensions = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
        this.owner = owner;
        this.name = name;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    @Nullable
    public ItemStack getPickResult() {
        return this.owner.getPickResult();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean is(Entity entity) {
        return this == entity || this.owner == entity;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.partDimensions;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}

