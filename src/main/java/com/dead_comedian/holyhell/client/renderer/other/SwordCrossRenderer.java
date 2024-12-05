package com.dead_comedian.holyhell.client.renderer.other;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.client.models.entity.other.FallingSwordModel;
import com.dead_comedian.holyhell.client.models.entity.other.SwordCrossModel;
import com.dead_comedian.holyhell.entity.custom.other.FallingSwordEntity;
import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;


public class SwordCrossRenderer<T extends SwordCrossEntity> extends EntityRenderer<T> {
    private static final Identifier TEXTURE = new Identifier(Holyhell.MOD_ID, "textures/entity/sword_cross.png");
    private final SwordCrossModel<SwordCrossEntity> model;

    public SwordCrossRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new SwordCrossModel<>(context.getPart(HolyHellModelLayers.SWORD_CROSS));
    }





    @Override
    public void render(T mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        MinecraftClient mc = MinecraftClient.getInstance();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-mc.gameRenderer.getCamera().getYaw()));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, 15728640);
    }

    @Override
    public Identifier getTexture(SwordCrossEntity entity) {
        return TEXTURE;
    }

}
