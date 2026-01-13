package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.server.entity.CherubEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.joml.Math;

public class CherubModel<T extends CherubEntity> extends HierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart head;


	public CherubModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.head = this.bone.getChild("bone2").getChild("head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 35.0F, 0.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(18, 42).addBox(-2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(-2.5F, 1.0F, -2.0F, 5.0F, 8.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(0, 56).addBox(-2.5F, 2.75F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -26.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition head = bone2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(20, 30).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -2.25F, 0.75F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-5.7426F, -5.874F, -0.0209F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2574F, 0.124F, -0.2291F, 0.0F, 0.0F, 0.7854F));

		PartDefinition right_hang = bone2.addOrReplaceChild("right_hang", CubeListBuilder.create().texOffs(12, 32).addBox(0.0F, -1.0F, -4.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.366F, -1.0981F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_hang = bone2.addOrReplaceChild("left_hang", CubeListBuilder.create().texOffs(12, 34).addBox(0.0F, -1.0F, -4.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.366F, -1.0981F, 0.7854F, 0.0F, 0.0F));

		PartDefinition lantern = bone2.addOrReplaceChild("lantern", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 2.5981F, -3.7679F, -0.5236F, 0.0F, 0.0F));

		PartDefinition tip = lantern.addOrReplaceChild("tip", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition midtip = tip.addOrReplaceChild("midtip", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, -1.0F, -1.4679F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 0.4679F));

		PartDefinition mid = midtip.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(6, 36).addBox(-1.5F, 0.0F, 0.0321F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition bottom = mid.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -1.4679F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition bell = bottom.addOrReplaceChild("bell", CubeListBuilder.create().texOffs(44, 40).addBox(-3.5F, 5.0F, -2.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(44, 33).addBox(-3.5F, 10.0F, -2.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offset(1.0F, -3.0F, -0.4679F));

		PartDefinition left_wind = bone2.addOrReplaceChild("left_wind", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -7.0F, 0.5F, 15.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 2.0F));

		PartDefinition right_wind = bone2.addOrReplaceChild("right_wind", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -7.0F, 0.5F, 15.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(ModAnimations.CHERUB_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimations.CHERUB_IDLE, ageInTicks, 1f);

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		bone.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Math.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Math.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}
	@Override
	public ModelPart root() {
		return bone;
	}
}