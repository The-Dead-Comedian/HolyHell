package com.dead_comedian.holyhell;


import com.dead_comedian.holyhell.client.renderer.*;
import com.dead_comedian.holyhell.client.renderer.non_living.*;
import com.dead_comedian.holyhell.client.renderer.render_layer.LowerRingRenderLayer;
import com.dead_comedian.holyhell.client.renderer.render_layer.UpperRingRenderLayer;
import com.dead_comedian.holyhell.event.DeathHandler;
import com.dead_comedian.holyhell.event.HolyCowSpawnHandler;

import com.dead_comedian.holyhell.event.PlayerDeathHandler;
import com.dead_comedian.holyhell.event.RegenerationHandler;
import com.dead_comedian.holyhell.registries.*;
import com.dead_comedian.holyhell.screen.CoffinScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(HolyHell.MOD_ID)
public class HolyHell {
    //
    public static final String MOD_ID = "holyhell";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HolyHell(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerLayerDefinitions);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        HolyHellItems.register(modEventBus);
        HolyHellScreens.register(modEventBus);
        HolyHellSound.register(modEventBus);
        HolyHellBlocks.register(modEventBus);
        HolyHellEffects.register(modEventBus);
        HolyHellEntities.register(modEventBus);
        HolyhellParticles.register(modEventBus);
        HolyHellCreativeTab.register(modEventBus);
        HolyHellBlockEntities.register(modEventBus);
        HolyHellCriteriaTriggers.init();

        DeathHandler.register();
        RegenerationHandler.register();
        HolyCowSpawnHandler.register();
        PlayerDeathHandler.register();
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, LowerRingRenderLayer::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGSV, UpperRingRenderLayer::getTexturedModelData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(HolyHellEntities.GATE.get(), GateRenderer::new);
            EntityRenderers.register(HolyHellEntities.GLOBULAR_DOME.get(), GlobularDomeRenderer::new);
            EntityRenderers.register(HolyHellEntities.BLINDING_BOMB.get(), ThrownItemRenderer::new);
            EntityRenderers.register(HolyHellEntities.FIREBALL.get(), FireBallRenderer::new);

            EntityRenderers.register(HolyHellEntities.REVENANT.get(), RevenantRenderer::new);

            EntityRenderers.register(HolyHellEntities.ANGEL.get(), AngelRenderer::new);
            EntityRenderers.register(HolyHellEntities.KAMIKAZE.get(), KamikazeRenderer::new);
            EntityRenderers.register(HolyHellEntities.HERETIC.get(), HereticRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_ONE.get(), BabOneRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_TWO.get(), BabTwoRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_THREE.get(), BabThreeRenderer::new);
            EntityRenderers.register(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritRenderer::new);
            EntityRenderers.register(HolyHellEntities.CHERUB.get(), CherubRenderer::new);
            EntityRenderers.register(HolyHellEntities.HOLY_COW.get(), HolyCowRenderer::new);

            MenuScreens.register(HolyHellScreens.COFFIN_MENU.get(), CoffinScreen::new);



            event.enqueueWork(HolyHellItemProperties::addCustomItemProperties);
        }
    }
}
