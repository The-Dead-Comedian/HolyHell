package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.LastPlayerModel;
import com.dead_comedian.holyhell.entity.ModModelLayers;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.LastPrayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.util.Identifier;

public class LastPrayerRenderer extends EntityRenderer<LastPrayerEntity> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/angel.png");
    private final LastPlayerModel<LastPrayerEntity> model;
    public LastPrayerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model =  new LastPlayerModel(context.getPart(ModModelLayers.LASTPRAYER));
    }





    @Override
    public void render(LastPrayerEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LastPrayerEntity entity) {
        return TEXTURE;
    }

}
