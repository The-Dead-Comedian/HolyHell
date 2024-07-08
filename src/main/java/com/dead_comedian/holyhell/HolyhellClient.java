package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.block.ModBlocks;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.spells.ChristianCrossModel;
import com.dead_comedian.holyhell.client.models.entity.spells.LastPrayerModel;

import com.dead_comedian.holyhell.client.renderer.AngelRenderer;
import com.dead_comedian.holyhell.client.renderer.spell.ChristianCrossRenderer;
import com.dead_comedian.holyhell.client.renderer.spell.LastPrayerRenderer;
import com.dead_comedian.holyhell.client.renderer.ReligiousRingsFeatureRenderer;
import com.dead_comedian.holyhell.entity.ModEntities;
import com.dead_comedian.holyhell.entity.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HolyhellClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIVINING_TABLE, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.ANGEL, AngelRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANGEL, AngelModel::getTexturedModelData);


        EntityRendererRegistry.register(ModEntities.LASTPRAYER, LastPrayerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LASTPRAYER, LastPrayerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CHRISTIANCROSS, ChristianCrossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHRISTIANCROSS, ChristianCrossModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RELIGIOUS_RINGS, ReligiousRingsFeatureRenderer::getTexturedModelData);

    }
}
