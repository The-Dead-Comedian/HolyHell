package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.KamikazeModel;
import com.dead_comedian.holyhell.entity.KamikazeEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class KamikazeRenderer extends MobRenderer<KamikazeEntity, KamikazeModel<KamikazeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/kamikaze_angel.png");

    public KamikazeRenderer(EntityRendererProvider.Context context) {
        super(context, new KamikazeModel<>(context.bakeLayer(HolyHellModelLayers.KAMIKAZE_ANGEL)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(KamikazeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(KamikazeEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}