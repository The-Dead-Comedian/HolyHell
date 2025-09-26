package com.dead_comedian.holyhell.client.renderer.overlay;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.mojang.blaze3d.platform.GlStateManager;
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
public class EyeTransitionOverlay {
    private static final int ANIMATION_SPEED = 1;
    public static int eyeTransitionCounter = 12;


    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;

            if (player != null && shouldShowOverlay(player)) {
                GuiGraphics guiGraphics = event.getGuiGraphics();

                if (player.hasEffect(HolyHellEffects.ANGELIC_VISION)) {
                    renderMultiFileAnimation(guiGraphics, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
                }
            }
        }
    }


    public static final ResourceLocation[] FRAME_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_1.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_2.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_3.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_4.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_5.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_5.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_4.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_3.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_2.png"),
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/misc/eye_overlay_1.png"),


    };


    private static void renderMultiFileAnimation(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {


        int currentFrame = (int) ((eyeTransitionCounter / ANIMATION_SPEED) % FRAME_TEXTURES.length);

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

        if (currentFrame == 9) {
            Minecraft.getInstance().player.setData(HolyHellAttachments.ANGEL_VISION_TRANSITION_SYNCED_DATA, false);
            Minecraft.getInstance().player.setData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA, !Minecraft.getInstance().player.getData(HolyHellAttachments.ANGEL_VISION_SHADER_SYNCED_DATA));
        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    private static boolean shouldShowOverlay(Player player) {

        return player.getData(HolyHellAttachments.ANGEL_VISION_TRANSITION_SYNCED_DATA);
    }
}