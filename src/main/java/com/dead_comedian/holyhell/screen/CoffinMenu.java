package com.dead_comedian.holyhell.screen;

import com.dead_comedian.holyhell.block.CoffinBlock;
import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.data.PlayerCoffinStatus;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellScreens;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class CoffinMenu extends AbstractContainerMenu {

    public final CoffinBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public static final int MAIN_ROWS = 6;
    public static final int MAIN_COLUMNS = 9;
    public static final int MAIN_SLOT_COUNT = MAIN_ROWS * MAIN_COLUMNS; // 54

    private static final int TE_INVENTORY_SLOT_COUNT = 59;
    private static final int VANILLA_SLOT_COUNT = 36;

    public CoffinMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, (CoffinBlockEntity) inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }
    public CoffinMenu(int id, Inventory inv, CoffinBlockEntity blockEntity) {
        super(HolyHellScreens.COFFIN_MENU.get(), id);

        this.blockEntity = blockEntity;
        this.data = blockEntity.getData();
        this.level = inv.player.level();

        ItemStackHandler handler = blockEntity.getInventory();

            addMainStorageSlots(handler);
            addExtraSlots(handler);

            addPlayerInventory(inv);
            addPlayerHotbar(inv);

            addDataSlots(this.data);

    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        if (!player.level().isClientSide && player.level() instanceof ServerLevel serverLevel) {
            if (!blockEntity.getBlockState().getValue(CoffinBlock.ACTIVATED)) {
                blockEntity.setStoredPlayer(null);
            } else {

                PlayerCoffinStatus status = player.getData(HolyHellAttachments.COFFIN_STATUS);

                status.update(true, blockEntity.getBlockPos());
            }
        }

        level.playLocalSound(
                player.blockPosition(),
                HolyHellSounds.COFFIN_LID.get(),
                SoundSource.BLOCKS,
                1.0F,
                1.0F,
                false
        );
    }

    private void addMainStorageSlots(IItemHandler handler) {
        for (int row = 0; row < MAIN_ROWS; ++row) {
            for (int col = 0; col < MAIN_COLUMNS; ++col) {
                addSlot(new SlotItemHandler(
                        handler,
                        col + row * MAIN_COLUMNS,
                        8 + col * 18,
                        row * 18 - 18
                ));
            }
        }
    }

    private void addExtraSlots(IItemHandler handler) {
        int startIndex = MAIN_SLOT_COUNT;
        int startX = 176;
        int startY = 18;

        addSlot(new SlotItemHandler(handler, startIndex,     startX + 40, startY - 38));
        addSlot(new SlotItemHandler(handler, startIndex + 1, startX + 5,  startY - 3));
        addSlot(new SlotItemHandler(handler, startIndex + 2, startX + 40, startY - 3));
        addSlot(new SlotItemHandler(handler, startIndex + 3, startX + 75, startY - 3));
        addSlot(new SlotItemHandler(handler, startIndex + 4, startX + 40, startY + 53));
    }



    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();

            if (index < TE_INVENTORY_SLOT_COUNT) {
                if (!moveItemStackTo(stack,
                        TE_INVENTORY_SLOT_COUNT,
                        TE_INVENTORY_SLOT_COUNT + VANILLA_SLOT_COUNT,
                        false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!moveItemStackTo(stack,
                        0,
                        TE_INVENTORY_SLOT_COUNT,
                        false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();

            slot.onTake(player, stack);
        }

        return result;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                HolyHellBlocks.COFFIN.get()
        );
    }

    private void addPlayerInventory(Inventory inv) {
        int offset = 2;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(
                        inv,
                        col + row * 9 + 9,
                        8 + col * 18,
                        102 + row * 18 + offset
                ));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(inv, i, 8 + i * 18, 162));
        }
    }
}
