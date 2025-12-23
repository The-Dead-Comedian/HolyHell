package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.block.CoffinBlock;
import com.dead_comedian.holyhell.data.PlayerCoffinStatus;
import com.dead_comedian.holyhell.data.StoredInventory;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import com.dead_comedian.holyhell.screen.CoffinMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CoffinBlockEntity extends RandomizableContainerBlockEntity {

    @Nullable
    private UUID storedPlayer;
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(59);

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
            boolean b = value != 0;
            switch (index) {
                case 0 -> topRender = b;
                case 1 -> leftRender = b;
                case 2 -> midRender = b;
                case 3 -> rightRender = b;
                case 4 -> bottomRender = b;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    public CoffinBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.COFFIN_BLOCK_ENTITY.get(), pos, state);
    }

    public ItemStackHandler getInventory() {
        return itemStackHandler;
    }

    public ContainerData getData() {
        return data;
    }


    public void setStoredPlayer(@Nullable UUID uuid) {
        storedPlayer = uuid;
        setChanged();
    }

    @Nullable
    public UUID getStoredPlayer() {
        return storedPlayer;
    }

    private void updateBlockState(BlockState state, BooleanProperty property, boolean value) {
        if (level != null) {
            level.setBlock(worldPosition, state.setValue(property, value), 3);
        }
    }


    @Override
    public int getContainerSize() {
        return 59;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        if (storedPlayer != null && level instanceof ServerLevel) {
            loadStoredPlayerInventory(storedPlayer);


        }

        level.playSound(null, worldPosition,
                HolyHellSounds.COFFIN_LID.get(),
                SoundSource.BLOCKS, 1.0F, 1.0F
        );

        return new CoffinMenu(id, inv, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Coffin");
    }

    @Override
    protected Component getDefaultName() {
        return getDisplayName();
    }


    public void tick(Level level, BlockPos pos, BlockState state) {


        data.set(0, itemStackHandler.getStackInSlot(54).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(1, itemStackHandler.getStackInSlot(55).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(2, itemStackHandler.getStackInSlot(56).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(3, itemStackHandler.getStackInSlot(57).is(Items.GOLD_INGOT) ? 1 : 0);
        data.set(4, itemStackHandler.getStackInSlot(58).is(Items.GOLD_INGOT) ? 1 : 0);

        boolean active = topRender && leftRender && midRender && rightRender && bottomRender;

        if (!level.isClientSide && level instanceof ServerLevel server && storedPlayer != null) {
            updateBlockState(state, CoffinBlock.ACTIVATED, active);
            setChanged();

            PlayerCoffinStatus status =
                    server.getPlayerByUUID(storedPlayer)
                            .getData(HolyHellAttachments.COFFIN_STATUS);

            status.update(active, pos);
        }
    }


    public void postDeathHook() {

        for (int i = 0; i < 5; i++) {
            ItemStack gold = itemStackHandler.getStackInSlot(54 + i);
            if (gold.is(Items.GOLD_INGOT)) {
                gold.shrink(1);
            }
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    public void loadStoredPlayerInventory(UUID playerId) {
        if (!(level instanceof ServerLevel server)) return;

        StoredInventory data = server.getPlayerByUUID(playerId).getData(HolyHellAttachments.STORED_INVENTORY);


        if (data == null) return;

        for (int i = 0; i < 36; i++) {
            itemStackHandler.insertItem(i, data.items[i], false);
        }

        for (int i = 0; i < 4; i++) {
            itemStackHandler.insertItem(36 + i, data.armor[i], false);
        }

    }

    @Override
    protected void saveAdditional(CompoundTag tag,HolderLookup.Provider registries) {
        super.saveAdditional(tag,registries);
        tag.put("Inventory", itemStackHandler.serializeNBT(registries));
    }



    @Override

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag,registries);
        itemStackHandler.deserializeNBT(registries,tag.getCompound("Inventory"));
    }

    private NonNullList<ItemStack> items = NonNullList.withSize(59, ItemStack.EMPTY);

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        items = stacks;
    }
}
