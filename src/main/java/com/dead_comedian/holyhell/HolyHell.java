package com.dead_comedian.holyhell;


import com.dead_comedian.holyhell.block.entity.GlobeBlockEntity;
import com.dead_comedian.holyhell.client.renderer.*;
import com.dead_comedian.holyhell.client.renderer.block_entity.GlobeRender;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsLowerFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.feature.ReligiousRingsUpperFeatureRenderer;
import com.dead_comedian.holyhell.client.renderer.non_living.*;
import com.dead_comedian.holyhell.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public HolyHell(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerLayerDefinitions);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        HolyHellItems.register(modEventBus);
        HolyHellSound.register(modEventBus);
        HolyHellBlocks.register(modEventBus);
        HolyHellEffects.register(modEventBus);
        HolyHellEntities.register(modEventBus);
        HolyhellParticles.register(modEventBus);
        HolyHellCreativeTab.register(modEventBus);
        HolyHellBlockEntities.register(modEventBus);

    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGS, ReligiousRingsLowerFeatureRenderer::getTexturedModelData);
        event.registerLayerDefinition(HolyHellModelLayers.RELIGIOUS_RINGSV, ReligiousRingsUpperFeatureRenderer::getTexturedModelData);
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
            EntityRenderers.register(HolyHellEntities.GLOBULAR_DOME.get(), GlobularDomeRenderer::new);
            EntityRenderers.register(HolyHellEntities.FALLING_SWORD.get(), FallingSwordRenderer::new);
            EntityRenderers.register(HolyHellEntities.BLINDING_BOMB.get(), ThrownItemRenderer::new);
            EntityRenderers.register(HolyHellEntities.FIREBALL.get(), FireBallRenderer::new);
            EntityRenderers.register(HolyHellEntities.SWORD_CROSS.get(), SwordCrossRenderer::new);


            EntityRenderers.register(HolyHellEntities.ANGEL.get(), AngelRenderer::new);
            EntityRenderers.register(HolyHellEntities.KAMIKAZE.get(), KamikazeRenderer::new);
            EntityRenderers.register(HolyHellEntities.HERETIC.get(), HereticRenderer::new);
            EntityRenderers.register(HolyHellEntities.PALLADIN.get(), PalladinRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_ONE.get(), BabOneRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_TWO.get(), BabTwoRenderer::new);
            EntityRenderers.register(HolyHellEntities.BAB_THREE .get(), BabThreeRenderer::new);
            EntityRenderers.register(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritRenderer::new);
            EntityRenderers.register(HolyHellEntities.CHERUB.get(), CherubRenderer::new);

            BlockEntityRenderers.register(HolyHellBlockEntities.GLOBE_BLOCK_ENTITY.get(), GlobeRender::new);
            event.enqueueWork(HolyHellItemProperties::addCustomItemProperties);
        }
    }
}
