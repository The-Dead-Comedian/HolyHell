package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Holyhell.MOD_ID, value = Dist.CLIENT)
public class EndTextOverlay {
    private static final int ANIMATION_SPEED = 60;
    public static int textCounter = 185;
    public static int extraGraceTime = 60;


    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player != null && shouldShowOverlay(player)) {
                GuiGraphics guiGraphics = event.getGuiGraphics();

                if (player.hasEffect(HolyHellEffects.PARANOIA) && player.getEffect(HolyHellEffects.PARANOIA).getAmplifier() == 3) {
                    renderMultiFileAnimation(guiGraphics, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(),player);
                }
            }
        }
    }


    public static final ResourceLocation[] FRAME_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/end_text/text_1.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/end_text/text_2.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/end_text/text_3.png"),
    };


    private static void renderMultiFileAnimation(GuiGraphics guiGraphics, int screenWidth, int screenHeight, Player player) {

        int currentFrame = (int) ((textCounter / ANIMATION_SPEED) % FRAME_TEXTURES.length);
        if (textCounter >= 180) {
            currentFrame = 2;
        }
        ResourceLocation currentTexture = FRAME_TEXTURES[currentFrame];

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();

        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(currentTexture,
                0, 0, -90,
                0.0F, 0.0F,
                screenWidth, screenHeight,
                screenWidth, screenHeight
        );

        if (currentFrame == 2) {
            extraGraceTime--;
            if (player != null) {

                System.out.println(player.getData(HolyHellAttachments.CAN_TP_TO_ANGEL));
                player.setData(HolyHellAttachments.CAN_TP_TO_ANGEL, true);
                if (extraGraceTime == 0) {

                    player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, false);
                    textCounter = 185;
                }
            }


        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }


    @SubscribeEvent
    public static void increaseCounter(ClientTickEvent.Post event) {
        if (EndTextOverlay.textCounter >= 0) {
            if (EndTextOverlay.textCounter < EndTextOverlay.FRAME_TEXTURES.length * 60 + 5) {
                EndTextOverlay.textCounter++;
            }
        }
    }

    private static boolean shouldShowOverlay(Player player) {

        return player.getData(HolyHellAttachments.SHOULD_DISPLAY_TEXT);
    }
}