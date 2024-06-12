package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.client.models.entity.LastPrayerModel;
import com.dead_comedian.holyhell.entity.ModModelLayers;
import com.dead_comedian.holyhell.entity.custom.LastPrayerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class LastPrayerRenderer extends EntityRenderer<LastPrayerEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/last_prayer.png");
    private final LastPrayerModel<LastPrayerEntity> model;

    public LastPrayerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new LastPrayerModel<>(context.getPart(ModModelLayers.LASTPRAYER));
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
