package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.PalladinModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.PalladinEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PalladinRenderer extends MobRenderer<PalladinEntity, PalladinModel<PalladinEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Holyhell.MOD_ID, "textures/entity/paladin.png");

    public PalladinRenderer(EntityRendererProvider.Context context) {
        super(context, new PalladinModel<>(context.bakeLayer(HolyHellModelLayers.PALLADIN)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(PalladinEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PalladinEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
