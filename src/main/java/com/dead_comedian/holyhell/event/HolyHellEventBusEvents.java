package com.dead_comedian.holyhell.event;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.entity.KamikazeAngelEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HolyHellEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HolyHellEntities.ANGEL.get(), AngelEntity.createAngelAttributes().build());
        event.put(HolyHellEntities.KAMIKAZE_ANGEL.get(), KamikazeAngelEntity.createKamikazeAttributes().build());
    }
}