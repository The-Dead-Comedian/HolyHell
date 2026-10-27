package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

@EventBusSubscriber(modid = HolyHell.MOD_ID, value = Dist.CLIENT)
public class HolyHellClientBossBarEvent {
    private static final ResourceLocation BAR_BACKGROUND_SPRITES = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/gui/sprites/boss_bar/angel_hp_background.png");
    private static final ResourceLocation BAR_PROGRESS_SPRITES = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/gui/sprites/boss_bar/angel_hp.png");

    @SubscribeEvent
    public static void onRenderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
        renderBossBar(event, "All Seer", BAR_BACKGROUND_SPRITES, BAR_PROGRESS_SPRITES, 225, 26);
    }


    public static void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event, String bossName, ResourceLocation backgroundSprite, ResourceLocation barSprite, int frameWidth, int frameHeight) {
        Component name = event.getBossEvent().getName();
        if (name.getString().contains(bossName)) {
            event.setCanceled(true);

            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.level == null) return;
            AllSeerEntity allSeerEntity = null;
            for (AllSeerEntity entity : mc.level.getEntitiesOfClass(AllSeerEntity.class, mc.player.getBoundingBox().inflate(100.0D))) {
                allSeerEntity = entity;
                break;
            }


            if (allSeerEntity!=null && allSeerEntity.isViolent()) {
                GuiGraphics guiGraphics = event.getGuiGraphics();
                int screenWidth = guiGraphics.guiWidth();
                int y = event.getY();

                int frameX = (screenWidth / 2) - (frameWidth / 2);

                guiGraphics.blit(backgroundSprite, frameX, y, 0, 0, frameWidth, frameHeight, frameWidth, frameHeight);

                float progress = event.getBossEvent().getProgress();
                int progressMaxWidth = 182;
                int progressHeight = 5;
                int currentProgressWidth = (int) (progressMaxWidth * progress);

                if (currentProgressWidth > 0) {
                    guiGraphics.blit(barSprite, frameX + 21, y+11, 0, 0, currentProgressWidth, progressHeight, progressMaxWidth, progressHeight);
                }
                event.setIncrement(frameHeight + 5);
            }
        }
    }
}
