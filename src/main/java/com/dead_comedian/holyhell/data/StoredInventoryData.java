package com.dead_comedian.holyhell.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoredInventoryData extends SavedData {

    private static final String DATA_NAME = "holyhell_player_inventories";

    private final Map<UUID, PlayerInventoryRecord> playerInventories = new HashMap<>();

    public static class PlayerInventoryRecord {
        public ItemStack[] items = new ItemStack[36];
        public ItemStack[] armor = new ItemStack[4];
        public ItemStack[] offhand = new ItemStack[1];
    }

    // Get instance
    public static StoredInventoryData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(
                StoredInventoryData::load,
                StoredInventoryData::new,
                DATA_NAME
        );
    }

    public StoredInventoryData() {}

    // Save inventory to SavedData
    public void savePlayerInventory(UUID uuid, PlayerInventoryRecord record) {
        playerInventories.put(uuid, record);
        setDirty();
    }

    public PlayerInventoryRecord getPlayerInventory(UUID uuid) {
        return playerInventories.get(uuid);
    }

    // ---- Serialization ----
    public static StoredInventoryData load(CompoundTag tag) {
        StoredInventoryData data = new StoredInventoryData();

        ListTag list = tag.getList("players", 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag pTag = list.getCompound(i);
            UUID uuid = pTag.getUUID("uuid");

            PlayerInventoryRecord record = new PlayerInventoryRecord();

            readStacks(pTag.getList("items", 10), record.items);
            readStacks(pTag.getList("armor", 10), record.armor);
            readStacks(pTag.getList("offhand", 10), record.offhand);

            data.playerInventories.put(uuid, record);
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();

        for (var entry : playerInventories.entrySet()) {
            UUID uuid = entry.getKey();
            PlayerInventoryRecord record = entry.getValue();

            CompoundTag pTag = new CompoundTag();
            pTag.putUUID("uuid", uuid);

            pTag.put("items", writeStacks(record.items));
            pTag.put("armor", writeStacks(record.armor));
            pTag.put("offhand", writeStacks(record.offhand));

            list.add(pTag);
        }

        tag.put("players", list);
        return tag;
    }

    // Utils
    private static ListTag writeStacks(ItemStack[] stacks) {
        ListTag list = new ListTag();
        for (int i = 0; i < stacks.length; i++) {
            CompoundTag slotTag = new CompoundTag();
            slotTag.putInt("slot", i);
            stacks[i].save(slotTag);
            list.add(slotTag);
        }
        return list;
    }

    private static void readStacks(ListTag list, ItemStack[] target) {
        for (int i = 0; i < list.size(); i++) {
            CompoundTag slotTag = list.getCompound(i);
            int slot = slotTag.getInt("slot");
            target[slot] = ItemStack.of(slotTag);
        }
    }
}
