package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;
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
        StoredInventoryData data = StoredInventoryData.get(level);

        StoredInventoryData.PlayerInventoryRecord record =
                new StoredInventoryData.PlayerInventoryRecord();

        // main inventory
        for (int i = 0; i < 36; i++) {
            record.items[i] = player.getInventory().items.get(i).copy();
        }

        // armor
        for (int i = 0; i < 4; i++) {
            record.armor[i] = player.getInventory().armor.get(i).copy();
        }

        // offhand
        record.offhand[0] = player.getInventory().offhand.get(0).copy();

        data.savePlayerInventory(player.getUUID(), record);

        // optional: wipe drops
        player.getInventory().clearContent();
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(PlayerDeathHandler.class);
    }

}
