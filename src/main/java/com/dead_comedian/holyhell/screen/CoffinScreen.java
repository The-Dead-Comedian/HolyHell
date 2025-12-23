package com.dead_comedian.holyhell.screen;

import com.dead_comedian.holyhell.Holyhell;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;

public class CoffinScreen extends AbstractContainerScreen<CoffinMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/gui/coffin_gui.png");

    public CoffinScreen(CoffinMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelY = -30;
        this.inventoryLabelY = 92;
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - 175) / 2;
        int y = (height - 238) / 2;

        g.blit(TEXTURE, x, y, 0, 0, 278, 222, 318, 222);

        renderIndicators(g, x, y);
    }

    private void renderIndicators(GuiGraphics g, int x, int y) {

        ContainerData d = menu.blockEntity.getData();

        if (d.get(0) == 1)
            g.blit(TEXTURE, x + 210, y + 11, 280, 29, 30, 37, 318, 222);

        if (d.get(2) == 1)
            g.blit(TEXTURE, x + 205, y + 47, 280, 94, 38, 25, 318, 222);

        if (d.get(1) == 1)
            g.blit(TEXTURE, x + 176, y + 46, 281, 1, 37, 26, 318, 222);

        if (d.get(3) == 1)
            g.blit(TEXTURE, x + 239, y + 46, 281, 67, 37, 26, 318, 222);

        if (d.get(4) == 1)
            g.blit(TEXTURE, x + 211, y + 70, 287, 122, 26, 58, 318, 222);
    }
}