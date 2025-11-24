package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.block.CoffinBlock;
import com.dead_comedian.holyhell.data.StoredInventoryData;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.screen.CoffinMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.UUID;


public class CoffinBlockEntity extends RandomizableContainerBlockEntity {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(59);
    private NonNullList<ItemStack> items = NonNullList.withSize(59, ItemStack.EMPTY);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    @Nullable
    private UUID storedPlayer = null;


    public void setStoredPlayer(UUID uuid) {
        this.storedPlayer = uuid;
        setChanged();
    }

    public @Nullable UUID getStoredPlayer() {
        return storedPlayer;
    }

    private boolean topRender;
    private boolean leftRender;
    private boolean midRender;
    private boolean rightRender;
    private boolean bottomRender;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> topRender ? 1 : 0;
                case 1 -> leftRender ? 1 : 0;
                case 2 -> midRender ? 1 : 0;
                case 3 -> rightRender ? 1 : 0;
                case 4 -> bottomRender ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            boolean bool = value != 0;
            switch (index) {
                case 0 -> topRender = bool;
                case 1 -> leftRender = bool;
                case 2 -> midRender = bool;
                case 3 -> rightRender = bool;
                case 4 -> bottomRender = bool;
            }
        }

        @Override
        public int getCount() {
            return 5; // number of synced data slots
        }
    };

    public ContainerData getData() {
        return data;
    }

    public CoffinBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.COFFIN_BLOCK_ENTITY.get(), pos, state);
    }

    void updateBlockState(BlockState pState, BooleanProperty property, boolean pOpen) {
        this.level.setBlock(this.getBlockPos(), pState.setValue(property, Boolean.valueOf(pOpen)), 3);
    }

    @Override
    public int getContainerSize() {
        return 59;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        if (this.getStoredPlayer() != null) {
            this.loadStoredPlayerInventory(this.getStoredPlayer());
            if (this.getBlockState().getValue(CoffinBlock.ACTIVATED)) {
                updateBlockState(this.getBlockState(), CoffinBlock.ACTIVATED, false);
            }
        }
        this.level.playSound((Player) null, this.getBlockPos(), HolyHellSound.COFFIN_LID.get(), SoundSource.BLOCKS, 1, 1f);
        return new CoffinMenu(pContainerId, pInventory, this, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Coffin");
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        if (storedPlayer != null) pTag.putUUID("StoredPlayer", storedPlayer);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.hasUUID("StoredPlayer")) storedPlayer = pTag.getUUID("StoredPlayer");
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        lazyItemHandler.invalidate();
        super.invalidateCaps();
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {

        data.set(0, itemStackHandler.getStackInSlot(54).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(1, itemStackHandler.getStackInSlot(55).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(2, itemStackHandler.getStackInSlot(56).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(3, itemStackHandler.getStackInSlot(57).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(4, itemStackHandler.getStackInSlot(58).is(Items.GOLD_INGOT) ? 1 : 0);

        boolean activated = topRender && leftRender && midRender && rightRender && bottomRender;

        updateBlockState(pState, CoffinBlock.ACTIVATED, activated);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItemStacks) {
        items = pItemStacks;
    }


    public void loadStoredPlayerInventory(UUID playerId) {
        if (level == null || level.isClientSide()) return;

        StoredInventoryData data = StoredInventoryData.get((ServerLevel) level);
        StoredInventoryData.PlayerInventoryRecord record = data.getPlayerInventory(playerId);

        if (record == null) return;

        for (int i = 0; i < 36; i++) {
            itemStackHandler.setStackInSlot(i, record.items[i]);
        }

        for (int i = 0; i < 4; i++) {
            itemStackHandler.setStackInSlot(36 + i, record.armor[i]);
        }
    }
}
