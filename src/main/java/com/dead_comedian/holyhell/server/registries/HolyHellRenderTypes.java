package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.event.HolyhellClientRegistries;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class HolyHellRenderTypes {
    public static final RenderType ANGEL_RINGS = RenderType.create(
            "angel_rings",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> HolyhellClientRegistries.ANGEL_RING_SHADER))
                    .setTextureState(
                            RenderStateShard.MultiTextureStateShard.builder()
                                    .add(ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/environment/background.png"), false, false)
                                    .add(ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/environment/eye_ring.png"), false, false)
                                    .build()
                    )
                    .createCompositeState(false)
    );
    public static final RenderType NO_SHADE_TRANSLUCENT = RenderType.create(
            "no_shade_translucent",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setOverlayState(RenderStateShard.OVERLAY)
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> HolyhellClientRegistries.ANGEL_RING_SHADER))
                    .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                            .add(ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/lightbeam.png"), false, false)
                            .add(ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/environment/eye_ring.png"), false, false)
                            .build())
                    .createCompositeState(true)


    );


}
