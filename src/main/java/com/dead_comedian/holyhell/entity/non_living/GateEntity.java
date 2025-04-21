package com.dead_comedian.holyhell.entity.non_living;

import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GateEntity extends Entity {
    private int ticksLeft;

    public GateEntity(EntityType<?> type, Level world) {
        super(type, world);

        this.ticksLeft = 100;


    }

    @Override
    protected void defineSynchedData() {

    }


    @Override
    public void tick() {
        super.tick();
        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }


}