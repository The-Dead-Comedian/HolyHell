package com.dead_comedian.holyhell.client.renderer.block_entity;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.block.entity.GlobeBlockEntity;
import com.dead_comedian.holyhell.client.model.entity.non_living.GlobeModel;
import com.dead_comedian.holyhell.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlobeRenderer implements BlockEntityRenderer<GlobeBlockEntity> {
    /**
     * The texture for the book above the enchantment table.
     */
    public static final Material BOOK_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(HolyHell.MOD_ID, "textures/entity/globe.png"));
    private final GlobeModel bookModel;
    public int time=0;
    public GlobeRenderer(BlockEntityRendererProvider.Context pContext) {
        this.bookModel = new GlobeModel(pContext.bakeLayer(HolyHellModelLayers.GLOBE));
    }



    @Override
    public void render(GlobeBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        time++;
        pPoseStack.pushPose();
        VertexConsumer vertexconsumer = BOOK_LOCATION.buffer(pBuffer, RenderType::entitySolid);
        this.bookModel.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }
}