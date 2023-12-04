package net.deadcomedian.holyhell.event;

import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.entity.client.EvangelistArmorModel;
import net.deadcomedian.holyhell.entity.client.AllSeerModel;

import net.deadcomedian.holyhell.entity.client.AngelModel;
import net.deadcomedian.holyhell.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ANGEL_LAYER, AngelModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ALL_SEER_LAYER, AllSeerModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(ModModelLayers.EVANGELIST_ARMOR, EvangelistArmorModel::createBodyLayer);

    }


}
