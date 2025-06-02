//package com.dead_comedian.holyhell.client.renderer;
//
//
//import com.dead_comedian.holyhell.HolyHell;
//import com.dead_comedian.holyhell.client.model.entity.DevoutModel;
//import com.dead_comedian.holyhell.client.model.entity.PalladinModel;
//import com.dead_comedian.holyhell.entity.DevoutEntity;
//import com.dead_comedian.holyhell.entity.PalladinEntity;
//import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.model.IllagerModel;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.monster.Vindicator;
//
//public class DevoutRenderer extends MobRenderer<DevoutEntity, DevoutModel<DevoutEntity>> {
//    private static final ResourceLocation TEXTURE = new ResourceLocation(HolyHell.MOD_ID, "textures/entity/devout.png");
//
//    public DevoutRenderer(EntityRendererProvider.Context context) {
//        super(context, new DevoutModel<>(context.bakeLayer(HolyHellModelLayers.DEVOUT)), 0.6f);
//        this.addLayer(new ItemInHandLayer<DevoutEntity, DevoutModel<DevoutEntity>>(this, context.getItemInHandRenderer()) {
//            public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, DevoutEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
//                if (pLivingEntity.isAggressive()) {
//                    super.render(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch);
//                }
//
//            }
//        });
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(DevoutEntity entity) {
//        return TEXTURE;
//    }
//
//    @Override
//    public void render(DevoutEntity mobEntity, float f, float g, PoseStack matrixStack,
//                       MultiBufferSource vertexConsumerProvider, int i) {
//
//
//        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
//    }
//
//}