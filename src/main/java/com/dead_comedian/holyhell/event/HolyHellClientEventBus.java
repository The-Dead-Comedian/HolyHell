package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.FallingSwordModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.FireBallModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
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
    }
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HolyhellParticles.LIGHT_RING.get(), LightRingParticle.Provider::new);
    }
}