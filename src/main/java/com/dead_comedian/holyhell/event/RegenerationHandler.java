package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegenerationHandler {
    @SubscribeEvent
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(HolyHellEffects.BLOODLUST.get())) {
                event.setCanceled(true);
            }
        }
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(RegenerationHandler.class);
    }
}
