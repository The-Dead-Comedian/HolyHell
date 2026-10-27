package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.event.EyeTransitionOverlay;
import com.dead_comedian.holyhell.server.registries.*;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HolyHell.MOD_ID)
public class HolyHell {
    public static final String MOD_ID = "holyhell";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HolyHell(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(HolyHellMessages::register);
        NeoForge.EVENT_BUS.register(this);


        HolyhellAttributes.register(modEventBus);
        HolyHellActivities.register(modEventBus);
        HolyHellAttachments.register(modEventBus);
        HolyHellBlockEntities.register(modEventBus);
        HolyHellBlocks.register(modEventBus);
        HolyHellCreativeTab.register(modEventBus);
        HolyHellEffects.register(modEventBus);
        HolyHellEntities.register(modEventBus);
        HolyHellItems.register(modEventBus);
        HolyHellCriteriaTriggers.register(modEventBus);
        HolyHellParticles.register(modEventBus);
        HolyHellSounds.register(modEventBus);
        HolyHellDimensions.register(modEventBus);
        HolyHellFeatures.ConfiguredFeatures.register(modEventBus);
        HolyHellFeatures.PlacedFeatures.register(modEventBus);

        HolyHellStructurePlacements.register(modEventBus);
        HolyhellDataComps.register(modEventBus);

        HolyHellMemoryModules.register(modEventBus);
        HolyHellSensorTypes.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            NeoForge.EVENT_BUS.register(EyeTransitionOverlay.class);
        }

        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from com.dead_comedian.holyhell.server starting");
    }
}
