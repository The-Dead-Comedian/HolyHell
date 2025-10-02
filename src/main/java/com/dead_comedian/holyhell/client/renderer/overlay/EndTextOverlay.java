package com.dead_comedian.holyhell.client.renderer.overlay;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.helper.PlayerHelpers;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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
            System.out.println(textCounter);
            if (player != null && shouldShowOverlay(player)) {
                GuiGraphics guiGraphics = event.getGuiGraphics();

                if (player.hasEffect(HolyHellEffects.PARANOIA) && player.getEffect(HolyHellEffects.PARANOIA).getAmplifier() == 3) {
                    renderMultiFileAnimation(guiGraphics, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
                }
            }
        }
    }


    public static final ResourceLocation[] FRAME_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/text_1.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/text_2.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/text_3.png"),
    };


    private static void renderMultiFileAnimation(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {

        int currentFrame = (int) ((textCounter / ANIMATION_SPEED) % FRAME_TEXTURES.length);
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
            Minecraft.getInstance().player.setData(HolyHellAttachments.CAN_TP_TO_ANGEL, true);
            if (Minecraft.getInstance().player != null && extraGraceTime == 0) {

                Minecraft.getInstance().player.setData(HolyHellAttachments.SHOULD_DISPLAY_TEXT, false);
                textCounter = 185;
            }


        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    private static boolean shouldShowOverlay(Player player) {

        return player.getData(HolyHellAttachments.SHOULD_DISPLAY_TEXT);
    }
}