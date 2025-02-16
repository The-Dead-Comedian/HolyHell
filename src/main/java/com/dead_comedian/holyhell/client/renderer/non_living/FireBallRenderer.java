package com.dead_comedian.holyhell.client.renderer.non_living;



import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.FireBallModel;
import com.dead_comedian.holyhell.entity.non_living.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class FireBallRenderer extends ArrowRenderer<FireBallEntity> {
    int timer = 1;
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/fireball/fireball3.png");
    private final FireBallModel<FireBallEntity> model;

    public FireBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FireBallModel<>(context.bakeLayer(HolyHellModelLayers.FIREBALL));
    }


    @Override
    public void render(FireBallEntity mobEntity, float f, float g, PoseStack matrixStack,
                       MultiBufferSource vertexConsumerProvider, int i) {

        VertexConsumer vertexConsumer = null;
        timer++;
        if (timer <= 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE1));

        } else if (timer <= 40 && timer > 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE2));
        } else if (timer <= 60 && timer > 40) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE3));
            timer = 1;
        }

        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public ResourceLocation getTextureLocation(FireBallEntity entity) {
        timer++;

        if (timer <= 20) {
            return TEXTURE1;
        } else if (timer <= 40 && timer > 20) {
            return TEXTURE1;
        } else if (timer <= 60 && timer > 40) {
            timer = 1;
            return TEXTURE1;
        }
        return TEXTURE1;
    }

}