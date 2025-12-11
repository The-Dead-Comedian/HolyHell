package com.dead_comedian.holyhell.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerCoffinStatus extends SavedData {

    private static final String NAME = "holyhell_coffin_status";

    public static class Status {
        public boolean active = false;
        public int coffinX, coffinY, coffinZ;

        public String Serialise(){
            return "{ " +
                    "Active: " + this.active + ", " +
                    "pos: [ " + coffinX + ", " + coffinY + ", " + coffinZ + " ]" +
                    " }";
        }

        public void SetActive(boolean value){
            this.active = value;
        }

        public BlockPos GetBlockPos(){
            return new BlockPos(this.coffinX, this.coffinY, this.coffinZ);
        }
    }

    private final Map<UUID, Status> map = new HashMap<>();

    public static PlayerCoffinStatus get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                PlayerCoffinStatus::load,
                PlayerCoffinStatus::new,
                NAME
        );
    }

    public PlayerCoffinStatus() {
    }

    public Status getStatus(UUID id) {
        return map.computeIfAbsent(id, k -> new Status());
    }

    public void Update(UUID id, boolean active, int x, int y, int z) {
        Status s = getStatus(id);
        s.active = active;
        s.coffinX = x;
        s.coffinY = y;
        s.coffinZ = z;
        setDirty();
    }

    public void deactivate(UUID id) {
        Status s = getStatus(id);
        s.SetActive(false);
        setDirty();
    }

    // ---- Load/Save ----

    public static PlayerCoffinStatus load(CompoundTag tag) {
        PlayerCoffinStatus status = new PlayerCoffinStatus();
        ListTag list = tag.getList("Players", 10);

        for (int i = 0; i < list.size(); i++) {
            CompoundTag data = list.getCompound(i);
            if (!data.hasUUID("id")) {
                continue;
            }

            Status s = new Status();
            s.active = data.getBoolean("active");
            s.coffinX = data.getInt("x");
            s.coffinY = data.getInt("y");
            s.coffinZ = data.getInt("z");
            UUID id = data.getUUID("id");
                status.map.put(id, s);


        }

        return status;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();

        for (var entry : map.entrySet()) {
            CompoundTag data = new CompoundTag();

            if (entry.getKey() == null) continue;

            data.putUUID("id", entry.getKey());
            Status s = entry.getValue();
            data.putBoolean("active", s.active);

            data.putInt("x", s.coffinX);
            data.putInt("y", s.coffinY);
            data.putInt("z", s.coffinZ);

            list.add(data);

        }

        tag.put("Players", list);
        return tag;
    }
}
