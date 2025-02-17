// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.model.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;

import com.dead_comedian.holyhell.entity.PalladinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class PalladinModel<T extends PalladinEntity> extends HierarchicalModel<T> {
    private final ModelPart body;

    public PalladinModel(ModelPart root) {
        this.body = root.getChild("body");

    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -18.0F, -5.5F, 20.0F, 18.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 29).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 53).addBox(-12.0F, -22.0F, 0.0F, 24.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

        PartDefinition left_hand = torso.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(96, 48).addBox(-7.7375F, -2.5F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 29).mirror().addBox(-9.7375F, -4.5F, -5.0F, 10.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 69).addBox(-7.7625F, 1.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 69).addBox(-7.7625F, -3.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.2625F, -16.5F, 0.0F));

        PartDefinition right_hand = torso.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(96, 48).mirror().addBox(-0.2625F, -2.5F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 29).addBox(-0.2625F, -4.5F, -5.0F, 10.0F, 15.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 69).mirror().addBox(-0.2375F, 1.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 69).mirror().addBox(-0.2375F, -3.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(10.2625F, -16.5F, 0.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 78).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -8.0F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 78).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, -8.0F, 0.0F));
        return LayerDefinition.create(modelData, 128, 128);
    }
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(ModAnimations.PALLADIN_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, ModAnimations.PALLADIN_IDLE, ageInTicks, 1f);
        this.animate(entity.attackAnimationState, ModAnimations.PALLADIN_CAST, ageInTicks, 1f);
    }
    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}