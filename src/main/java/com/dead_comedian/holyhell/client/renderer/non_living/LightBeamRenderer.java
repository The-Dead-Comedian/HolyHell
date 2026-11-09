package com.dead_comedian.holyhell.client.renderer.non_living;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.client.model.entity.non_living.JudgementSpearModel;
import com.dead_comedian.holyhell.client.model.entity.non_living.LightBeamModel;
import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import com.dead_comedian.holyhell.server.entity.non_living.LightBeamEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.dead_comedian.holyhell.server.registries.HolyHellRenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
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

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/religious_rings.png");

    private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, "textures/entity/lightbeam.png");


    private static final float TEXTURE_WIDTH = 64F;
    private static final float TEXTURE_HEIGHT = 64F;
    private static final float HALF = 7F / 16F;
    private static final float SEGMENT_HEIGHT = 2F;
    private static final float SIDE_0 = 14F / TEXTURE_HEIGHT;
    private static final float SIDE_1 = 46F / TEXTURE_HEIGHT;

    private final ModelPart bb_main;
    private final ModelPart bb_main1;


    public LightBeamRenderer(EntityRendererProvider.Context context) {
        super(context);

        ModelPart modelPart = context.getModelSet().bakeLayer(HolyHellModelLayers.RELIGIOUS_RINGS);
        this.bb_main1 = modelPart.getChild("bb_main1");
        this.bb_main = modelPart.getChild("bb_main");
    }


    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -28.0F, -11.0F, 22.0F, 7.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 28.0F, 0.0F));
        PartDefinition bb_main1 = modelPartData.addOrReplaceChild("bb_main1", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -17.0F, -11.0F, 22.0F, 7.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }

    @Override
    public void render(LightBeamEntity entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        float length = 64F;
        VertexConsumer vc1 = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));

        PoseStack.Pose pose = poseStack.last();


        for (float y2 = 0F; y2 < length; y2 += SEGMENT_HEIGHT ) {
            poseStack.pushPose();
            float n = y2 % 2 == 0 ? -(entity.tickCount * (float) (-11)) : entity.tickCount * (float) (-11);
            poseStack.scale(0.9f, 0.9f, 0.9f);
            poseStack.mulPose(Axis.YP.rotationDegrees(-n));
            poseStack.translate(0, y2, 0);
            this.bb_main1.render(poseStack, vc1, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
        VertexConsumer vc = buffer.getBuffer(HolyHellRenderTypes.NO_SHADE_TRANSLUCENT);
        for (float y0 = 0F; y0 < length; y0 += SEGMENT_HEIGHT) {
            float y1 = Math.min(y0 + SEGMENT_HEIGHT, length);
            float frac = (y1 - y0) / SEGMENT_HEIGHT;

            float vBottom = SIDE_1;
            float vTop = SIDE_1 - (SIDE_1 - SIDE_0) * frac;


            face(vc, pose, -HALF, -HALF, HALF, -HALF, 14, 28, 0, 0, -1, y0, y1, vTop, vBottom); // north
            face(vc, pose, HALF, -HALF, HALF, HALF, 28, 42, 1, 0, 0, y0, y1, vTop, vBottom); // east
            face(vc, pose, HALF, HALF, -HALF, HALF, 42, 56, 0, 0, 1, y0, y1, vTop, vBottom); // south
            face(vc, pose, -HALF, HALF, -HALF, -HALF, 0, 14, -1, 0, 0, y0, y1, vTop, vBottom); // west
        }

        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    private static void face(VertexConsumer vc, PoseStack.Pose pose,
                             float x1, float z1, float x2, float z2,
                             float uPx0, float uPx1, float nx, float ny, float nz,
                             float y0, float y1, float vTop, float vBottom) {
        float u0 = uPx0 / TEXTURE_WIDTH, u1 = uPx1 / TEXTURE_WIDTH;
        vertex(vc, pose, x1, y0, z1, u0, vBottom, nx, ny, nz);
        vertex(vc, pose, x1, y1, z1, u0, vTop, nx, ny, nz);
        vertex(vc, pose, x2, y1, z2, u1, vTop, nx, ny, nz);
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