package com.dead_comedian.holyhell.server.block.entity;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.block.DiviningTableBlock;
import com.dead_comedian.holyhell.server.entity.BabOneEntity;
import com.dead_comedian.holyhell.server.helper.WaveSpawner;
import com.dead_comedian.holyhell.server.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellParticles;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarvedMarbleBlockEntity extends BlockEntity {
    public CarvedMarbleBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.CARVED_MARBLE_BLOCK_ENTITY.get(), pos, state);
    }


    public BlockState blockState;
    public void setStoredState(BlockState state) {
        this.blockState = state;
        this.setChanged();
    }

    public BlockState getStoredState() {
        return this.blockState;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        if (this.blockState != null) {
            tag.put("block", NbtUtils.writeBlockState(this.blockState));
        }
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        if (tag.contains("block")) {
            this.blockState = NbtUtils.readBlockState(registries.lookupOrThrow(Registries.BLOCK), tag.getCompound("block"));
        } else {
            this.blockState = null;
        }
    }

    @Override
    public net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener> getUpdatePacket() {
        return net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (this.blockState != null) {
            tag.put("block", NbtUtils.writeBlockState(this.blockState));
            HolyHell.LOGGER.info("[CarvedMarble] SAVE at {}: {}", this.worldPosition, this.blockState);
        } else {
            HolyHell.LOGGER.info("[CarvedMarble] SAVE at {}: null", this.worldPosition);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("block")) {
            this.blockState = NbtUtils.readBlockState(registries.lookupOrThrow(Registries.BLOCK), tag.getCompound("block"));
            HolyHell.LOGGER.info("[CarvedMarble] LOAD at {}: {}", this.worldPosition, this.blockState);
        } else {
            HolyHell.LOGGER.info("[CarvedMarble] LOAD at {}: no tag present", this.worldPosition);
        }
    }
}
