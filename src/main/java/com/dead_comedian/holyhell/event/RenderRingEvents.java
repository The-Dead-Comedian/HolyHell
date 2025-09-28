package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.render_layer.UpperRingRenderLayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderRingEvents {

    @SubscribeEvent
    public static void renderRings(RenderPlayerEvent.Pre event) {
        System.out.println("wawa");
        event.getRenderer().addLayer(new LowerRingRenderLayer(event.getRenderer(), Minecraft.getInstance().getEntityModels()));
        event.getRenderer().addLayer(new UpperRingRenderLayer(event.getRenderer(), Minecraft.getInstance().getEntityModels()));
    }



}