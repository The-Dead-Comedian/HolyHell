package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.BabOneModel;
import com.dead_comedian.holyhell.client.model.BabThreeModel;
import com.dead_comedian.holyhell.client.model.BabTwoModel;
import com.dead_comedian.holyhell.client.model.HolySpiritModel;
import com.dead_comedian.holyhell.client.model.entity.AngelModel;
import com.dead_comedian.holyhell.client.model.entity.HereticModel;
import com.dead_comedian.holyhell.client.model.entity.KamikazeModel;
import com.dead_comedian.holyhell.client.model.entity.PalladinModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.FallingSwordModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.FireBallModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.SwordCrossModel;
import com.dead_comedian.holyhell.particle.LightRingParticle;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HolyHellClientEventBus {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.FALLING_SWORD, FallingSwordModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.FIREBALL, FireBallModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.SWORD_CROSS, SwordCrossModel::getTexturedModelData);

        event.registerLayerDefinition(HolyHellModelLayers.HERETIC, HereticModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.ANGEL, AngelModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.KAMIKAZE_ANGEL, KamikazeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.BAB, BabOneModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB1, BabTwoModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB2, BabThreeModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_SPIRIT, HolySpiritModel  ::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.PALLADIN, PalladinModel::getTexturedModelData);

    }
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HolyhellParticles.LIGHT_RING.get(), LightRingParticle.Provider::new);
    }
}