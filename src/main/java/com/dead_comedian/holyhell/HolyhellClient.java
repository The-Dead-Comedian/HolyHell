package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.models.entity.HailingHereticModel;
import com.dead_comedian.holyhell.client.renderer.HailingHereticRenderer;
import com.dead_comedian.holyhell.client.renderer.spell.*;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;

import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.spells.AreaSpellModel;
import com.dead_comedian.holyhell.client.models.entity.spells.ChristianCrossModel;
import com.dead_comedian.holyhell.client.models.entity.spells.LastPrayerModel;

import com.dead_comedian.holyhell.client.renderer.AngelRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HolyhellClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.DIVINING_TABLE, RenderLayer.getCutout());
        EntityRendererRegistry.register(HolyHellEntities.ANGEL, AngelRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.ANGEL, AngelModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.HAILING_HERETIC, HailingHereticRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.HAILING_HERETIC, HailingHereticModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.LASTPRAYER, LastPrayerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.LASTPRAYER, LastPrayerModel::getTexturedModelData);
        EntityRendererRegistry.register(HolyHellEntities.CHRISTIANCROSS, ChristianCrossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.CHRISTIANCROSS, ChristianCrossModel::getTexturedModelData);
        EntityRendererRegistry.register(HolyHellEntities.AREASPELL, AreaSpellRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.AREA_SPELL, AreaSpellModel::getTexturedModelData);


        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.RELIGIOUS_RINGS, ReligiousRingsFeatureRenderer::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.ATHEIST_AMAZEMENT, AtheistAmazementFeatureRenderer::getTexturedModelData);

    }
}
