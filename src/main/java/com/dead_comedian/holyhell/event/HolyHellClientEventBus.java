package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.*;
import com.dead_comedian.holyhell.client.model.entity.non_living.*;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.overlay.EyeTransitionOverlay;
import com.dead_comedian.holyhell.networking.packet.ServerboundAngelShaderAbilityPacket;
import com.dead_comedian.holyhell.particle.*;
import com.dead_comedian.holyhell.registries.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.AttackSweepParticle;
import net.minecraft.client.player.AbstractClientPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.network.PacketDistributor;


@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellClientEventBus {


    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (HolyHellKeyBinds.VISION_ABILITY_KEY.consumeClick()) {
            EyeTransitionOverlay.eyeTransitionCounter = 0;
            PacketDistributor.sendToServer(new ServerboundAngelShaderAbilityPacket());
        }
    }

    @SubscribeEvent
    public static void increaseCounter(ClientTickEvent.Post event) {
        if (EyeTransitionOverlay.eyeTransitionCounter >= 0) {
            if (EyeTransitionOverlay.eyeTransitionCounter < EyeTransitionOverlay.FRAME_TEXTURES.length) {
                EyeTransitionOverlay.eyeTransitionCounter++;
            }
        }
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(HolyHellKeyBinds.VISION_ABILITY_KEY);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        HolyHellItemProperties.addCustomItemProperties();

        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGSV, ReligiousRingsUpperFeatureRenderer::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, ReligiousRingsLowerFeatureRenderer::getTexturedModelData);


        event.registerLayerDefinition(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::createBodyLayer);
//        event.registerLayerDefinition(HolyHellModelLayers.FALLING_SWORD, FallingSwordModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.FIREBALL, FireBallModel::createBodyLayer);
//        event.registerLayerDefinition(HolyHellModelLayers.SWORD_CROSS, SwordCrossModel::getTexturedModelData);


        event.registerLayerDefinition(HolyHellModelLayers.HERETIC, HereticModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.ANGEL, AngelModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.KAMIKAZE_ANGEL, KamikazeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.BAB, BabOneModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB1, BabTwoModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB2, BabThreeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_SPIRIT, HolySpiritModel::getTexturedModelData);
        //      event.registerLayerDefinition(HolyHellModelLayers.PALLADIN, PalladinModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.CHERUB, CherubModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_COW, HolyCowModel::createBodyLayer);
        //      event.registerLayerDefinition(HolyHellModelLayers.DEVOUT, DevoutModel::createBodyLayer);

    }


//
//    @SubscribeEvent
//    public static void a(RenderPlayerEvent event) {
//        AbstractClientPlayer abstractClientPlayer = ((AbstractClientPlayer) (Object) Minecraft.getInstance().player);
//        event.getRenderer().addLayer(new ReligiousRingsUpperFeatureRenderer<>(event.getRenderer(),event.getRenderer().getModel()));
//    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HolyhellParticles.LIGHT_RING.get(), LightRingParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.SOUND_RING.get(), SoundRingParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.STUN.get(), StunParticle1.Provider::new);
        event.registerSpriteSet(HolyhellParticles.STUN2.get(), StunParticle2.Provider::new);

        event.registerSpriteSet(HolyhellParticles.SWEEP_ATTACK.get(), AttackSweepParticle.Provider::new);

        event.registerSpriteSet(HolyhellParticles.EYE0.get(), EyeParticle0.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE1.get(), EyeParticle1.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE2.get(), EyeParticle2.Provider::new);
        event.registerSpriteSet(HolyhellParticles.EYE3.get(), EyeParticle3.Provider::new);

        event.registerSpriteSet(HolyhellParticles.KAMIKAZE_EXPLOSION.get(), KamikazeExplosionParticle.Provider::new);
    }


}
