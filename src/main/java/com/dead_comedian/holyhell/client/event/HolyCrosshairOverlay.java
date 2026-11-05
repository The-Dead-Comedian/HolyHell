package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import com.dead_comedian.holyhell.server.registries.HolyhellDataComps;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.List;

@EventBusSubscriber(modid = HolyHell.MOD_ID, value = Dist.CLIENT)
public class HolyCrosshairOverlay {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;

            if (player != null && shouldShowOverlay(player)) {
                GuiGraphics guiGraphics = event.getGuiGraphics();
                renderOverlay(guiGraphics);

            }
        }
    }

    private static void renderOverlay(GuiGraphics guiGraphics) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        guiGraphics.blit(ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/gui/sprites/hud/grail_crosshair.png"), (guiGraphics.guiWidth() - 15) / 2, (guiGraphics.guiHeight() - 15) / 2, -90, 0.0F, 0.0F, 15, 15, 15, 15);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    private static boolean shouldShowOverlay(Player player) {

        AABB aabb = new AABB(player.getX() - 10, player.getY() - 10, player.getZ() - 10, player.getX() + 10, player.getY() + 10, player.getZ() + 10);
        List<Entity> mobba = player.level().getEntities(player, aabb);
        for (Entity entity : mobba) {
            if (entity instanceof Monster monster) {

                Vec3 vec3 = player.getViewVector(1.0F).normalize();
                Vec3 vec31 = new Vec3(monster.getX() - player.getX(), monster.getEyeY() - player.getEyeY(), monster.getZ() - player.getZ());
                double d0 = vec31.length();
                vec31 = vec31.normalize();
                double d1 = vec3.dot(vec31);
                if (player.getItemInHand(InteractionHand.MAIN_HAND).is(HolyHellItems.HOLY_GRAIL.get()) &&
                        (player.getItemInHand(InteractionHand.MAIN_HAND).getComponents().get(HolyhellDataComps.GRAIL_LEVEL.get()) != null &&
                                player.getItemInHand(InteractionHand.MAIN_HAND).getComponents().get(HolyhellDataComps.GRAIL_LEVEL.get()) >= 3) &&
                        d1 > (double) 1.0F - 0.025 / d0 &&
                        player.hasLineOfSight(monster)) {
                    return true;
                }
            }
        }


        return false;
    }
}