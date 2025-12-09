package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.client.renderer.*;
import com.dead_comedian.holyhell.client.renderer.non_living.FireBallRenderer;
import com.dead_comedian.holyhell.client.renderer.non_living.GlobularDomeRenderer;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import com.dead_comedian.holyhell.registries.HolyHellItemProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Holyhell.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Holyhell.MOD_ID, value = Dist.CLIENT)
public class HolyhellModClient {

    public static final ResourceLocation ANGEL_VISION_SHADER = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "shaders/post/angel_vision.json");
    public static int loadShaderAttempt = 0;

    public HolyhellModClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        HolyHellItemProperties.addCustomItemProperties();


        EntityRenderers.register(HolyHellEntities.ANGEL.get(), AngelRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_ONE.get(), BabOneRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_TWO.get(), BabTwoRenderer::new);
        EntityRenderers.register(HolyHellEntities.BAB_THREE.get(), BabThreeRenderer::new);
        EntityRenderers.register(HolyHellEntities.CHERUB.get(), CherubRenderer::new);
        EntityRenderers.register(HolyHellEntities.HERETIC.get(), HereticRenderer::new);
        EntityRenderers.register(HolyHellEntities.HOLY_COW.get(), HolyCowRenderer::new);
        EntityRenderers.register(HolyHellEntities.HOLY_SPIRIT.get(), HolySpiritRenderer::new);
        EntityRenderers.register(HolyHellEntities.KAMIKAZE.get(), KamikazeRenderer::new);

        EntityRenderers.register(HolyHellEntities.ALL_SEER.get(), AllSeerRenderer::new);

        EntityRenderers.register(HolyHellEntities.BLINDING_BOMB.get(), ThrownItemRenderer::new);
        EntityRenderers.register(HolyHellEntities.FIREBALL.get(), FireBallRenderer::new);
        EntityRenderers.register(HolyHellEntities.GLOBULAR_DOME.get(), GlobularDomeRenderer::new);
    }


    public static void attemptLoadShader(ResourceLocation effect) {
        GameRenderer renderer = Minecraft.getInstance().gameRenderer;
        if (loadShaderAttempt <= 0) {
            renderer.loadEffect(effect);
            if (!ANGEL_VISION_SHADER.toString().equals((renderer.currentEffect().getName()))) {
                loadShaderAttempt = 12000;
                Holyhell.LOGGER.warn("Failed to load shader {}", effect);
            }
        }
    }
}
