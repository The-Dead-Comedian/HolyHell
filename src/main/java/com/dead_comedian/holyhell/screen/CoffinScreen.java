package com.dead_comedian.holyhell.screen;

import com.dead_comedian.holyhell.HolyHell;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoffinScreen extends AbstractContainerScreen<CoffinMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HolyHell.MOD_ID,"textures/gui/coffin_gui.png");

    public CoffinScreen(CoffinMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelY=-30;
        this.inventoryLabelY=92;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 175) / 2;
        int y = (height - 238 ) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 278, 222,318,222);


    }

//    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
//        if(menu.isCrafting()) {
//            guiGraphics.blit(TEXTURE, x + 85, y + 30, 176, 0, 8, menu.getScaledProgress());
//        }
//    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
