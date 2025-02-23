package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.HereticEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.joml.Math;

public class HereticModel<T extends HereticEntity> extends HierarchicalModel<T> {

	private final ModelPart hole;
	private final ModelPart head;

	public HereticModel(ModelPart root) {
		this.hole = root.getChild("hole");
		this.head = root.getChild("hole").getChild("bottom").getChild("body").getChild("head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hole = partdefinition.addOrReplaceChild("hole", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bottom = hole.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 20).addBox(-4.5F, -5.1174F, -13.4803F, 9.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.5F, 14.0F));

		PartDefinition back_right_leg2 = bottom.addOrReplaceChild("back_right_leg2", CubeListBuilder.create().texOffs(20, 47).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 0).addBox(-2.5F, 9.0F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.5F, -4.5F));

		PartDefinition back_left_leg2 = bottom.addOrReplaceChild("back_left_leg2", CubeListBuilder.create().texOffs(40, 0).addBox(-2.5F, 9.0F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(20, 47).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -2.5F, -4.5F));

		PartDefinition body = bottom.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -5.1174F, -9.0F, 11.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -13.4803F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 31).addBox(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
		.texOffs(32, 12).addBox(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.4796F, -8.952F));

		PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(20, 47).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 47).addBox(-2.5F, 9.0F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -3.5F, -5.5197F));

		PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(40, 47).addBox(-2.4938F, 9.0959F, -2.4465F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(20, 47).addBox(-2.4938F, 0.0959F, -2.4465F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.9938F, -3.5959F, -5.5732F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(HereticEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(ModAnimations.HERETIC_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.animate(entity.attackAnimationState, ModAnimations.HERETIC_ATTACK, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Math.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Math.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return hole;
	}
}