package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.model.entity.HolySpiritModel;
import com.dead_comedian.holyhell.entity.HolySpiritEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HolySpiritRenderer extends MobRenderer<HolySpiritEntity, HolySpiritModel<HolySpiritEntity>> {
    private static final ResourceLocation NORMAL = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "textures/entity/holy_spirit.png");


    public HolySpiritRenderer(EntityRendererProvider.Context context) {
        super(context, new HolySpiritModel<>(context.bakeLayer(HolyHellModelLayers.HOLY_SPIRIT)), 0.6f);

    }

    @Override
    public ResourceLocation getTextureLocation(HolySpiritEntity entity) {
        return NORMAL;

    }

    @Override
    public void render(HolySpiritEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}
