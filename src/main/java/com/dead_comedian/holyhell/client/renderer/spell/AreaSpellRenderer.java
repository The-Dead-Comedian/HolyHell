package com.dead_comedian.holyhell.client.renderer.spell;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.spells.AreaSpellModel;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.dead_comedian.holyhell.entity.custom.spells.AreaSpellEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class AreaSpellRenderer extends EntityRenderer<AreaSpellEntity> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/areaspell.png");
    private final AreaSpellModel<AreaSpellEntity> model;

    public AreaSpellRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new AreaSpellModel<>(context.getPart(HolyHellModelLayers.AREA_SPELL));
    }





    @Override
    public void render(AreaSpellEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(AreaSpellEntity entity) {
        return TEXTURE;
    }

}
