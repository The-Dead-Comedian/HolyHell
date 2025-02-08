package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.HailingHereticModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HailingHereticRenderer extends MobRenderer<HailingHereticEntity, HailingHereticModel<HailingHereticEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/hailing_heretic.png");

    public HailingHereticRenderer(EntityRendererProvider.Context context) {
        super(context, new HailingHereticModel<>(context.bakeLayer(HolyHellModelLayers.HAILING_HERETIC)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(HailingHereticEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(HailingHereticEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
