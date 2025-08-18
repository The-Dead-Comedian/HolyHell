package com.dead_comedian.holyhell.event;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.*;
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
        event.put(HolyHellEntities.BAB_ONE.get(), BabOneEntity.createBabOneAttributes().build());
        event.put(HolyHellEntities.BAB_TWO.get(), BabTwoEntity.createBabTwoAttributes().build());
        event.put(HolyHellEntities.BAB_THREE.get(), BabThreeEntity.createBabThreeAttributes().build());
        event.put(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritEntity.createHolySpiritAttributes().build());
        event.put(HolyHellEntities.CHERUB.get(), CherubEntity.createCherubAttributes().build());
        event.put(HolyHellEntities.HOLY_COW.get(), HolyCowEntity.createHolyCowAttributes().build());
//        event.put(HolyHellEntities.DEVOUT.get(), DevoutEntity.createDevoutAttributes().build());
//        event.put(HolyHellEntities.PALLADIN.get(), PalladinEntity.createPalladinAttributes().build());

    }
}