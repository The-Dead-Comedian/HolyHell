package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.KamikazeAngelModel;
import com.dead_comedian.holyhell.entity.KamikazeAngelEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class KamikazeAngelRenderer extends MobRenderer<KamikazeAngelEntity, KamikazeAngelModel<KamikazeAngelEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/kamikaze_angel.png");

    public KamikazeAngelRenderer(EntityRendererProvider.Context context) {
        super(context, new KamikazeAngelModel<>(context.bakeLayer(HolyHellModelLayers.KAMIKAZE_ANGEL)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(KamikazeAngelEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(KamikazeAngelEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}