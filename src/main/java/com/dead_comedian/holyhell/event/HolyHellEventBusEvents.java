package com.dead_comedian.holyhell.event;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.entity.KamikazeEntity;
import com.dead_comedian.holyhell.entity.PalladinEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HolyHellEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HolyHellEntities.ANGEL.get(), AngelEntity.createAngelAttributes().build());
        event.put(HolyHellEntities.KAMIKAZE.get(), KamikazeEntity.createKamikazeAttributes().build());
        event.put(HolyHellEntities.HERETIC.get(), HereticEntity.createHereticAttributes().build());
        event.put(HolyHellEntities.PALLADIN.get(), PalladinEntity.createPalladinAttributes().build());
    }
}