package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class  AngelModel<T extends AngelEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;
	private final ModelPart eye;
	private final ModelPart rightwing;
	private final ModelPart leftwing;
	private final ModelPart eye_ball;
	private final ModelPart jaw_eye;

	public AngelModel(ModelPart root) {
		this.body = root.getChild("body");
		this.eye = this.body.getChild("eye");
		this.rightwing = this.eye.getChild("rightwing");
		this.leftwing = this.eye.getChild("leftwing");
		this.eye_ball = this.eye.getChild("eye_ball");
		this.jaw_eye = this.eye_ball.getChild("jaw_eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create(), PartPose.offset(-0.5F, -17.0F, 0.5F));

		PartDefinition rightwing = eye.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -3.75F, 0.5F));

		PartDefinition leftwing = eye.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -4.0F, 0.5F));

		PartDefinition eye_ball = eye.addOrReplaceChild("eye_ball", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).mirror().addBox(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw_eye = eye_ball.addOrReplaceChild("jaw_eye", CubeListBuilder.create().texOffs(0, 25).addBox(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 18).addBox(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.7F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);


		this.animateWalk(ModAnimations.FLY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimations.FLY_IDLE, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}