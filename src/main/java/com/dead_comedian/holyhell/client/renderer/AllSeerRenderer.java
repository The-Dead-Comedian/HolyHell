package com.dead_comedian.holyhell.client.renderer;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.AllSeerModel;
import com.dead_comedian.holyhell.client.renderer.glow_layer.GlowingSeerLayer;
import com.dead_comedian.holyhell.server.entity.AllSeerEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class AllSeerRenderer extends MobRenderer<AllSeerEntity, AllSeerModel<AllSeerEntity>> {

    protected float roll;
    protected float oRoll;

    public AllSeerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AllSeerModel<>(pContext.bakeLayer(HolyHellModelLayers.ALL_SEER)), 0);
        this.addLayer(new GlowingSeerLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(AllSeerEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/all_seer.png");
    }

    @Override
    protected void setupRotations(AllSeerEntity entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        Vec3 camPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double dx = camPos.x - entity.getX();
        double dz = camPos.z - entity.getZ();
        double dy = camPos.y - entity.getY()-entity.getBoundingBox().getYsize()/2;
        float facingYaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);
        float pitch = (float) (Mth.atan2(dy, horizontalDist) * (180.0 / Math.PI)); // sign flipped vs above, see note
        super.setupRotations(entity, poseStack, partialTick, facingYaw, partialTick,1);
        poseStack.mulPose(Axis.XP.rotationDegrees(pitch));
    }

    @Override
    public void render(AllSeerEntity entity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(entity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, -1572864);
    }
}