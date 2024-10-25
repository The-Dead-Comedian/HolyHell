package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.models.entity.*;
import com.dead_comedian.holyhell.client.models.entity.other.BlindingBombModel;
import com.dead_comedian.holyhell.client.models.entity.other.FallingSwordModel;
import com.dead_comedian.holyhell.client.models.entity.other.FireBallModel;
import com.dead_comedian.holyhell.client.models.entity.other.GlobularDomeModel;
import com.dead_comedian.holyhell.client.renderer.*;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.other.*;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HolyhellClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.DIVINING_TABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.GLOBE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.CANDELABRA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.CANDLEHOLDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HolyHellBlocks.STONE_CROSS, RenderLayer.getCutout());

        // MOBS

        EntityRendererRegistry.register(HolyHellEntities.ANGEL, AngelRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.ANGEL, AngelModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.HAILING_HERETIC, HailingHereticRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.HAILING_HERETIC, HailingHereticModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.KAMIKAZE_ANGEL, KamikazeAngelRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.KAMIKAZE_ANGEL, KamikazeAngelModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.PALLADIN, PalladinRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.PALLADIN, PalladinModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.BAB, BabRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.BAB, BabOneModel::getTexturedModelData);

        //NON MOBS
        EntityRendererRegistry.register(HolyHellEntities.LIGHT_BEAM, LightBeamRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.LIGHT_BEAM, LightBeamModel ::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.GLOBULAR_DOME, GlobularDomeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.FIREBALL, FireBallRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.FIREBALL, FireBallModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.BLINDING_BOMB, BlindingBombRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.BLINDING_BOMB, BlindingBombModel::getTexturedModelData);

        EntityRendererRegistry.register(HolyHellEntities.FALLING_SWORD, FallingSwordRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.FALLING_SWORD, FallingSwordModel::getTexturedModelData);


        //FEATURE
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.RELIGIOUS_RINGS, ReligiousRingsLowerFeatureRenderer::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.RELIGIOUS_RINGSV, ReligiousRingsUpperFeatureRenderer::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HolyHellModelLayers.ATHEIST_AMAZEMENT, AtheistAmazementFeatureRenderer::getTexturedModelData);

    }
}
