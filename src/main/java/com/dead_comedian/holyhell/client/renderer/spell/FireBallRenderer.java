package com.dead_comedian.holyhell.client.renderer.spell;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.spells.FireBallModel;
import com.dead_comedian.holyhell.entity.custom.spells.FireBallEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class FireBallRenderer extends EntityRenderer<FireBallEntity> {
    int timer = 1;
    private static final Identifier TEXTURE1 = new Identifier(Holyhell.MOD_ID, "textures/entity/fireball/fireball1.png");
    private static final Identifier TEXTURE2 = new Identifier(Holyhell.MOD_ID, "textures/entity/fireball/fireball2.png");
    private static final Identifier TEXTURE3 = new Identifier(Holyhell.MOD_ID, "textures/entity/fireball/fireball3.png");
    private final FireBallModel<FireBallEntity> model;

    public FireBallRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new FireBallModel<>(context.getPart(HolyHellModelLayers.FIREBALL));
    }


    @Override
    public void render(FireBallEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        VertexConsumer vertexConsumer = null;
            timer++;
        if (timer <= 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE1));
            System.out.println(timer);
        } else if (timer <= 40 && timer > 20) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE2));
        } else if (timer <= 60 && timer > 40) {
            vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE3));
            timer = 1;
        }
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public Identifier getTexture(FireBallEntity entity) {
        timer++;

        if (timer <= 2) {
            System.out.println("work");
            return TEXTURE1;
        } else if (timer <= 4 && timer > 2) {
            return TEXTURE1;
        } else if (timer <= 6 && timer > 4) {
            timer = 1;
            return TEXTURE1;
        }
        return TEXTURE1;
    }

}
