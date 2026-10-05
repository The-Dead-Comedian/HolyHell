package com.dead_comedian.holyhell.client.renderer.block_entity;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.block.entity.CarvedMarbleBlockEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.Objects;

public class CarvedMarbleRenderer<T extends CarvedMarbleBlockEntity> implements BlockEntityRenderer<T> {

    private final ModelPart bb_main;

    public CarvedMarbleRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(HolyHellModelLayers.CARVED_MARBLE);
        this.bb_main = root.getChild("bb_main");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(-16, -16).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }


    @Override
    public void render(T entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

        if (entity.blockState != null) {
            Block block = entity.blockState.getBlock();
            String[] textureComponents = BuiltInRegistries.BLOCK.getKey(block).toString().split(":");
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(textureComponents[0], "textures/block/" + textureComponents[1] + ".png");
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentCull(texture));
            poseStack.pushPose();

            if (entity.getLevel().getBlockState(entity.getBlockPos()).hasProperty(HorizontalDirectionalBlock.FACING)) {
                if (entity.getLevel().getBlockState(entity.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.SOUTH ||
                        entity.getLevel().getBlockState(entity.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.NORTH) {
                    poseStack.scale(0.95f, 1, 1f);
                    poseStack.translate(0.525, -0.5, 0.5);
                }
                if (entity.getLevel().getBlockState(entity.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST ||
                        entity.getLevel().getBlockState(entity.getBlockPos()).getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST) {
                    poseStack.scale(1f, 1, 0.95f);
                    poseStack.translate(0.5, -0.5, 0.525);
                }
            } else {
                poseStack.scale(0.95f, 1, 0.95f);
                poseStack.translate(0.525, -0.5, 0.525);
            }

            bb_main.render(poseStack, vertexConsumer, i, i1);
            poseStack.popPose();
        }


    }
}
