package com.dead_comedian.holyhell.screen;

import com.dead_comedian.holyhell.block.CoffinBlock;
import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.data.PlayerCoffinStatus;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellScreens;
import com.dead_comedian.holyhell.registries.HolyHellSound;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
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
    private final Container container;

    public static final int TOTAL_SLOTS = 59;
    public static final int MAIN_ROWS = 6;
    public static final int MAIN_COLUMNS = 9;
    public static final int MAIN_SLOT_COUNT = MAIN_ROWS * MAIN_COLUMNS; // 54


    public CoffinMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()), null);
    }

    public CoffinMenu(int id, Inventory inv, BlockEntity entity, Container pContainer) {
        super(HolyHellScreens.COFFIN_MENU.get(), id);
        this.blockEntity = (CoffinBlockEntity) entity;
        this.data = blockEntity.getData();
        this.level = inv.player.level();
        this.container = pContainer;

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

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        if (pPlayer.level().getServer() != null) {
            ServerLevel level = pPlayer.level().getServer().overworld();
            if (!blockEntity.getBlockState().getValue(CoffinBlock.ACTIVATED)) {
                blockEntity.setStoredPlayer(null);
            } else {
                // Coffin was active → deactivate player on close
                ServerLevel s = (ServerLevel) pPlayer.level();
                PlayerCoffinStatus.get(s).deactivate(pPlayer.getUUID());
            }
        }

        this.level.playLocalSound(pPlayer.blockPosition(), HolyHellSound.COFFIN_LID.get(), SoundSource.BLOCKS, 1, 1f, false);
    }

    private void addMainStorageSlots(IItemHandler handler) {
        for (int j = 0; j < MAIN_ROWS; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(handler, k + j * 9, 8 + k * 18, j * 18 - 18));
            }
        }
    }

    private void addExtraSlots(IItemHandler handler) {
        int startIndex = MAIN_SLOT_COUNT;
        int startX = 176;
        int startY = 18;

        this.addSlot(new SlotItemHandler(handler, startIndex, startX + 40, startY - 38));
        this.addSlot(new SlotItemHandler(handler, startIndex + 1, startX + 5, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 2, startX + 40, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 3, startX + 75, startY - 3));
        this.addSlot(new SlotItemHandler(handler, startIndex + 4, startX + 40, startY + 53));

    }


    private static final int TE_INVENTORY_SLOT_COUNT = 59;
    private static final int VANILLA_SLOT_COUNT = 36;


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            // ---- BlockEntity → Player ----
            if (index < TE_INVENTORY_SLOT_COUNT) {
                // Move from block (0–58) → player (59–95)
                if (!this.moveItemStackTo(stack,
                        TE_INVENTORY_SLOT_COUNT,
                        TE_INVENTORY_SLOT_COUNT + VANILLA_SLOT_COUNT,
                        false)) {
                    return ItemStack.EMPTY;
                }
            }

            // ---- Player → BlockEntity ----
            else {
                // Move from player (59–95) → block (0–58)
                if (!this.moveItemStackTo(stack,
                        0,
                        TE_INVENTORY_SLOT_COUNT,
                        false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, stack);
        }

        return itemstack;
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
