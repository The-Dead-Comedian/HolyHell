package com.dead_comedian.holyhell.client.renderer;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.AngelModel;
import com.dead_comedian.holyhell.client.models.entity.KamikazeAngelModel;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KamikazeAngelRenderer extends MobEntityRenderer<KamikazeAngelEntity, KamikazeAngelModel<KamikazeAngelEntity>> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/kamikaze_angel.png");

    public KamikazeAngelRenderer(EntityRendererFactory.Context context) {
        super(context, new KamikazeAngelModel<>(context.getPart(HolyHellModelLayers.KAMIKAZE_ANGEL)), 0.6f);
    }

    @Override
    public Identifier getTexture(KamikazeAngelEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(KamikazeAngelEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {


        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }


}
