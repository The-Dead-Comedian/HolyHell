package com.dead_comedian.holyhell.screen;

import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellScreens;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CoffinMenu extends AbstractContainerMenu {
    public final CoffinBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public static final int TOTAL_SLOTS = 59;
    public static final int MAIN_ROWS = 6;
    public static final int MAIN_COLUMNS = 9;
    public static final int MAIN_SLOT_COUNT = MAIN_ROWS * MAIN_COLUMNS; // 54


    public CoffinMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()), null);
    }

    public CoffinMenu(int id, Inventory inv, BlockEntity entity, ContainerData unused) {
        super(HolyHellScreens.COFFIN_MENU.get(), id);

        this.blockEntity = (CoffinBlockEntity) entity;
        this.data = blockEntity.getData();
        this.level = inv.player.level();

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            addMainStorageSlots(handler);
            addExtraSlots(handler);

            addPlayerInventory(inv);
            addPlayerHotbar(inv);

            addDataSlots(this.data);  // <-- ONLY this one
        });
    }


    public ContainerData getData() {
        return data;
    }

    private void addMainStorageSlots(IItemHandler handler) {
        for (int j = 0; j < MAIN_ROWS; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(handler, k + j * 9, 8 + k * 18, j * 18 - 18));
            }
        }
    }

    private void addExtraSlots(IItemHandler handler) {
        int startIndex = MAIN_SLOT_COUNT; // 54
        int startX = 176;                 // right side of container
        int startY = 18;

        this.addSlot(new SlotItemHandler(handler, startIndex, startX + 40, startY - 38));
        this.addSlot(new SlotItemHandler(handler, startIndex + 1, startX + 5, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 2, startX + 40, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 3, startX + 75, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 4, startX + 40, startY + 53));

    }


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = TOTAL_SLOTS;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, HolyHellBlocks.COFFIN.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int i = 2;
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 102 + l * 18 + i));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 162));
        }

    }
}
