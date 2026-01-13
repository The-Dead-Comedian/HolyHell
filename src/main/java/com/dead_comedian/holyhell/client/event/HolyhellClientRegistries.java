package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.*;
import com.dead_comedian.holyhell.client.model.entity.non_living.FireBallModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.render_layer.UpperRingRenderLayer;
import com.dead_comedian.holyhell.client.screen.CoffinScreen;
import com.dead_comedian.holyhell.particle.KamikazeExplosionParticle;
import com.dead_comedian.holyhell.particle.LightRingParticle;
import com.dead_comedian.holyhell.particle.ObfuscationParticle;
import com.dead_comedian.holyhell.particle.SoundRingParticle;
import com.dead_comedian.holyhell.particle.eye_particle.EyeParticle0;
import com.dead_comedian.holyhell.particle.eye_particle.EyeParticle1;
import com.dead_comedian.holyhell.particle.eye_particle.EyeParticle2;
import com.dead_comedian.holyhell.particle.eye_particle.EyeParticle3;
import com.dead_comedian.holyhell.particle.fireball.FireballImpact;
import com.dead_comedian.holyhell.particle.fireball.FireballTrail;
import com.dead_comedian.holyhell.particle.locator.BossLocatorParticle;
import com.dead_comedian.holyhell.particle.locator.HostileLocatorParticle;
import com.dead_comedian.holyhell.particle.locator.PeacefulLocatorParticle;
import com.dead_comedian.holyhell.particle.locator.PlayerLocatorParticle;
import com.dead_comedian.holyhell.particle.stun_particles.StunParticle1;
import com.dead_comedian.holyhell.particle.stun_particles.StunParticle2;
import com.dead_comedian.holyhell.server.registries.HolyHellKeyBinds;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.dead_comedian.holyhell.server.registries.HolyHellScreens;
import com.dead_comedian.holyhell.server.registries.HolyhellParticles;
import net.minecraft.client.particle.AttackSweepParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Holyhell.MOD_ID,value = Dist.CLIENT)
public class HolyhellClientRegistries {



    @SubscribeEvent
    public static void registerKey(RegisterKeyMappingsEvent event) {
        event.register(HolyHellKeyBinds.VISION_ABILITY_KEY);
        event.register(HolyHellKeyBinds.RING_ABILITY_KEY);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGSV, UpperRingRenderLayer::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, LowerRingRenderLayer::getTexturedModelData);


        event.registerLayerDefinition(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.FIREBALL, FireBallModel::createBodyLayer);

        event.registerLayerDefinition(HolyHellModelLayers.ALL_SEER, AllSeerModel::createBodyLayer);


        event.registerLayerDefinition(HolyHellModelLayers.HERETIC, HereticModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.ANGEL, AngelModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.KAMIKAZE_ANGEL, KamikazeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.BAB, BabOneModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB1, BabTwoModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.BAB2, BabThreeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_SPIRIT, HolySpiritModel::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.CHERUB, CherubModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.HOLY_COW, HolyCowModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(HolyHellScreens.COFFIN_MENU.get(), CoffinScreen::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HolyhellParticles.FIREBALL_TRAIL.get(), FireballTrail.Provider::new);
        event.registerSpriteSet(HolyhellParticles.FIREBALL_IMPACT.get(), FireballImpact.Provider::new);

        event.registerSpriteSet(HolyhellParticles.HOSTILE_LOCATOR.get(), HostileLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.BOSS_LOCATOR.get(), BossLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.PEACEFUL_LOCATOR.get(), PeacefulLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyhellParticles.PLAYER_LOCATOR.get(), PlayerLocatorParticle.Provider::new);

        event.registerSpriteSet(HolyhellParticles.OBFUSCATION.get(), ObfuscationParticle.Provider::new);

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
