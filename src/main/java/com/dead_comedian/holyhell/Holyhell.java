package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.renderer.overlay.EyeTransitionOverlay;
import com.dead_comedian.holyhell.networking.HolyHellMessages;
import com.dead_comedian.holyhell.registries.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Holyhell.MOD_ID)
public class Holyhell {
    public static final String MOD_ID = "holyhell";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Holyhell(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);


        HolyHellAttachments.register(modEventBus);
        HolyHellBlockEntities.register(modEventBus);
        HolyHellBlocks.register(modEventBus);
        HolyHellCreativeTab.register(modEventBus);
        HolyHellEffects.register(modEventBus);
        HolyHellEntities.register(modEventBus);
        HolyHellItems.register(modEventBus);
        HolyHellCriteriaTriggers.register(modEventBus);
        HolyhellParticles.register(modEventBus);
        HolyHellSound.register(modEventBus);


        if (FMLEnvironment.dist == Dist.CLIENT) {
            NeoForge.EVENT_BUS.register(EyeTransitionOverlay.class);
        }

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
