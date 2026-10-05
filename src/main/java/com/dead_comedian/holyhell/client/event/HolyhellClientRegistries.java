package com.dead_comedian.holyhell.client.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.HolyHellModClient;
import com.dead_comedian.holyhell.client.model.entity.*;
import com.dead_comedian.holyhell.client.model.entity.non_living.AngelProjectileModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobularDomeModel;
import com.dead_comedian.holyhell.client.renderer.block_entity.CarvedMarbleRenderer;
import com.dead_comedian.holyhell.client.renderer.block_entity.CoffinRenderer;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
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
import com.dead_comedian.holyhell.server.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.server.registries.HolyHellKeyBinds;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.dead_comedian.holyhell.server.registries.HolyHellParticles;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.particle.AttackSweepParticle;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = HolyHell.MOD_ID, value = Dist.CLIENT)
public class HolyhellClientRegistries {


    public static ShaderInstance ANGEL_RING_SHADER;

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skin);

            renderer.addLayer(
                    new LowerRingRenderLayer<>(
                            renderer,
                            event.getEntityModels()
                    )
            );
        }
    }

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(event.getResourceProvider(), HolyHellModClient.ANGEL_RINGS_SHADER, DefaultVertexFormat.POSITION),
                shader -> ANGEL_RING_SHADER = shader
        );
    }

    @SubscribeEvent
    public static void registerLayers(RegisterKeyMappingsEvent event) {
        event.register(HolyHellKeyBinds.VISION_ABILITY_KEY);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, LowerRingRenderLayer::getTexturedModelData);

        event.registerLayerDefinition(HolyHellModelLayers.COFFIN, CoffinRenderer::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.CARVED_MARBLE, CarvedMarbleRenderer::createBodyLayer);


        event.registerLayerDefinition(HolyHellModelLayers.GLOBULAR_DOME, GlobularDomeModel::createBodyLayer);
        event.registerLayerDefinition(HolyHellModelLayers.ANGEL_PROJECTILE, AngelProjectileModel::createBodyLayer);

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
        event.registerLayerDefinition(HolyHellModelLayers.REVENANT, RevenantModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(HolyHellBlockEntities.COFFIN_BLOCK_ENTITY.get(),CoffinRenderer::new);
        event.registerBlockEntityRenderer(HolyHellBlockEntities.CARVED_MARBLE_BLOCK_ENTITY.get(), CarvedMarbleRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HolyHellParticles.FIREBALL_TRAIL.get(), FireballTrail.Provider::new);
        event.registerSpriteSet(HolyHellParticles.FIREBALL_IMPACT.get(), FireballImpact.Provider::new);

        event.registerSpriteSet(HolyHellParticles.HOSTILE_LOCATOR.get(), HostileLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyHellParticles.BOSS_LOCATOR.get(), BossLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyHellParticles.PEACEFUL_LOCATOR.get(), PeacefulLocatorParticle.Provider::new);
        event.registerSpriteSet(HolyHellParticles.PLAYER_LOCATOR.get(), PlayerLocatorParticle.Provider::new);

        event.registerSpriteSet(HolyHellParticles.OBFUSCATION.get(), ObfuscationParticle.Provider::new);

        event.registerSpriteSet(HolyHellParticles.LIGHT_RING.get(), LightRingParticle.Provider::new);
        event.registerSpriteSet(HolyHellParticles.SOUND_RING.get(), SoundRingParticle.Provider::new);
        event.registerSpriteSet(HolyHellParticles.STUN.get(), StunParticle1.Provider::new);
        event.registerSpriteSet(HolyHellParticles.STUN2.get(), StunParticle2.Provider::new);

        event.registerSpriteSet(HolyHellParticles.SWEEP_ATTACK.get(), AttackSweepParticle.Provider::new);

        event.registerSpriteSet(HolyHellParticles.EYE0.get(), EyeParticle0.Provider::new);
        event.registerSpriteSet(HolyHellParticles.EYE1.get(), EyeParticle1.Provider::new);
        event.registerSpriteSet(HolyHellParticles.EYE2.get(), EyeParticle2.Provider::new);
        event.registerSpriteSet(HolyHellParticles.EYE3.get(), EyeParticle3.Provider::new);

        event.registerSpriteSet(HolyHellParticles.KAMIKAZE_EXPLOSION.get(), KamikazeExplosionParticle.Provider::new);
    }
}
