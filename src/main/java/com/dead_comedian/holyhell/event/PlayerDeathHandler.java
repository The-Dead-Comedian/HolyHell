package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;

import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.data.PlayerCoffinStatus;
import com.dead_comedian.holyhell.data.StoredInventoryData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID)
public class PlayerDeathHandler {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ServerLevel level = player.serverLevel();

        PlayerCoffinStatus status = PlayerCoffinStatus.get(level);
        PlayerCoffinStatus.Status s = status.getStatus(player.getUUID());
        CoffinBlockEntity entitty = (CoffinBlockEntity) level.getBlockEntity(s.GetBlockPos());


        //System.out.println(s.Serialise());
        if (!s.active) return;

        // ✔ If coffin IS active → wipe + save inventory
        StoredInventoryData data = StoredInventoryData.get(level);
        StoredInventoryData.PlayerInventoryRecord record =
                new StoredInventoryData.PlayerInventoryRecord();

        for (int i = 0; i < 36; i++)
            record.items[i] = player.getInventory().items.get(i).copyAndClear();

        for (int i = 0; i < 4; i++)
            record.armor[i] = player.getInventory().armor.get(i).copyAndClear();

        record.offhand[0] = player.getInventory().offhand.get(0).copyAndClear();

        data.savePlayerInventory(player.getUUID(), record);

        player.getInventory().clearContent();

        // ⛔ deactivate after death
        status.deactivate(player.getUUID());
        entitty.PostDeathHook();
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(PlayerDeathHandler.class);
    }
}
