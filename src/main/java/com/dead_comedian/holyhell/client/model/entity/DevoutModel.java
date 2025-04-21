package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.DevoutEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Math;

public class DevoutModel<T extends DevoutEntity> extends HierarchicalModel<T> implements ArmedModel {
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart hood;
	private final ModelPart right_hand;
	private final ModelPart right_elbow;
	private final ModelPart left_hand;
	private final ModelPart left_elbow;
	private final ModelPart robe;

	public DevoutModel(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.hood = this.body.getChild("hood");
		this.right_hand = this.body.getChild("right_hand");
		this.right_elbow = this.right_hand.getChild("right_elbow");
		this.left_hand = this.body.getChild("left_hand");
		this.left_elbow = this.left_hand.getChild("left_elbow");
		this.robe = this.body.getChild("robe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hood = body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(1, 39).addBox(-6.5F, -14.5F, -6.25F, 13.0F, 15.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(59, 11).addBox(-5.5F, -13.0F, -5.75F, 11.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 11).addBox(-5.5F, -13.0F, -5.25F, 11.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, -0.25F));

		PartDefinition right_hand = body.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(56, 45).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -21.0F, 0.0F));

		PartDefinition right_elbow = right_hand.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(56, 57).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(76, 54).addBox(-2.5F, 5.0F, -2.5F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(42, 42).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition left_hand = body.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(56, 45).mirror().addBox(-2.5F, -3.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.5F, -21.0F, 0.0F));

		PartDefinition left_elbow = left_hand.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(56, 57).mirror().addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(76, 54).mirror().addBox(-2.5F, 5.0F, -2.5F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(42, 42).mirror().addBox(-2.0F, 10.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition robe = body.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -12.5F, -6.0F, 12.0F, 25.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(DevoutEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(ModAnimations.DEVOUT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimations.DEVOUT_IDLE, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, ModAnimations.DEVOUT_CAST, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Math.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Math.clamp(pHeadPitch, -25.0F, 45.0F);

		this.hood.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.hood.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return all;
	}
	private ModelPart getArm(HumanoidArm pArm) {
		return pArm == HumanoidArm.LEFT ? this.left_hand : this.right_hand;
	}


	@Override
	public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
		this.getArm(pSide).translateAndRotate(pPoseStack);
	}
}