package net.deadcomedian.holyhell.event;

import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.entity.ModEntities;
import net.deadcomedian.holyhell.entity.custom.AllSeerEntity;
import net.deadcomedian.holyhell.entity.custom.AngelEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ANGEL.get(), AngelEntity.createAttributes().build());
        event.put(ModEntities.ALL_SEER.get(), AllSeerEntity.createAttributes().build());
    }
}