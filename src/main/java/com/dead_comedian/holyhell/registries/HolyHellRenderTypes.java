package com.dead_comedian.holyhell.registries;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;

public class HolyHellRenderTypes {
    public static final RenderType RED_OVERLAY = RenderType.create(
            "red_overlay",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getPositionColorShader)) // simple, not affected by post fx
                    .setTransparencyState(RenderType.TransparencyStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setDepthTestState(RenderStateShard.NO_DEPTH_TEST) // draw on top
                    .setCullState(RenderStateShard.NO_CULL)
                    .createCompositeState(false)
    );
}
