package com.dead_comedian.holyhell.client.renderer.non_living;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.JudgementSpearModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.LightBeamModel;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.entity.non_living.LightBeamEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;


public class LightBeamRenderer extends EntityRenderer<LightBeamEntity> {

    private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/lightbeam.png");


    private static final float TEXTURE_WIDTH = 64F;
    private static final float TEXTURE_HEIGHT = 64F;
    private static final float HALF = 7F / 16F;
    private static final float SEGMENT_HEIGHT = 2F;
    private static final float SIDE_0 = 14F / TEXTURE_HEIGHT;
    private static final float SIDE_1 = 46F / TEXTURE_HEIGHT;



    public LightBeamRenderer(EntityRendererProvider.Context context) {
        super(context);
     }




    @Override
    public void render(LightBeamEntity entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float length = 64F;
         VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE1));
        PoseStack.Pose pose = poseStack.last();

        for (float y0 = 0F; y0 < length; y0 += SEGMENT_HEIGHT) {
            float y1 = Math.min(y0 + SEGMENT_HEIGHT, length);
            float frac = (y1 - y0) / SEGMENT_HEIGHT;

            float vBottom = SIDE_1;
            float vTop = SIDE_1 - (SIDE_1 - SIDE_0) * frac;

            face(vc, pose, -HALF, -HALF,  HALF, -HALF, 14, 28,  0, 0, -1, y0, y1, vTop, vBottom); // north
            face(vc, pose,  HALF, -HALF,  HALF,  HALF, 28, 42,  1, 0,  0, y0, y1, vTop, vBottom); // east
            face(vc, pose,  HALF,  HALF, -HALF,  HALF, 42, 56,  0, 0,  1, y0, y1, vTop, vBottom); // south
            face(vc, pose, -HALF,  HALF, -HALF, -HALF,  0, 14, -1, 0,  0, y0, y1, vTop, vBottom); // west
        }

        super.render(entity, yaw, partialTick, poseStack, buffer, 15728640);
    }

    private static void face(VertexConsumer vc, PoseStack.Pose pose,
                             float x1, float z1, float x2, float z2,
                             float uPx0, float uPx1, float nx, float ny, float nz,
                             float y0, float y1, float vTop, float vBottom) {
        float u0 = uPx0 / TEXTURE_WIDTH, u1 = uPx1 / TEXTURE_WIDTH;
        vertex(vc, pose, x1, y0, z1, u0, vBottom, nx, ny, nz);
        vertex(vc, pose, x1, y1, z1, u0, vTop,    nx, ny, nz);
        vertex(vc, pose, x2, y1, z2, u1, vTop,    nx, ny, nz);
        vertex(vc, pose, x2, y0, z2, u1, vBottom, nx, ny, nz);
    }

    private static void vertex(VertexConsumer vc, PoseStack.Pose pose,
                               float x, float y, float z, float u, float v,
                               float nx, float ny, float nz) {
        vc.addVertex(pose, x, y, z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(LightTexture.FULL_BRIGHT)
                .setNormal(pose, nx, ny, nz);
    }


    @Override
    protected int getBlockLightLevel(LightBeamEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(LightBeamEntity entity) {
        return TEXTURE1;
    }

    @Override
    public boolean shouldRender(LightBeamEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

}