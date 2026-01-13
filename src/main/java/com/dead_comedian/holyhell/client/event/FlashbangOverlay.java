package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Holyhell.MOD_ID, value = Dist.CLIENT)
public class FlashbangOverlay {

    public static int pluhTimer = 0;

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;

            if (player != null && shouldShowOverlay(player)) {
                GuiGraphics guiGraphics = event.getGuiGraphics();
                renderOverlay(guiGraphics, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());

            }
        }
    }

    private static void renderOverlay(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        if (pluhTimer < 40) {
            pluhTimer++;

            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1 - ((float) 1 / pluhTimer));
        } else {
                Minecraft.getInstance().player.setData(HolyHellAttachments.FLASHBANG, false);
                pluhTimer = 0;
        }

        guiGraphics.blit(ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/flashbang.png"), 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    private static boolean shouldShowOverlay(Player player) {

        return player.getData(HolyHellAttachments.FLASHBANG);
    }
}