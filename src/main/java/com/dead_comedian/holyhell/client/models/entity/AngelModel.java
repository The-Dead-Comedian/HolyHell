// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
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

public class AngelModel <T extends AngelEntity> extends HierarchicalModel<T> {
	private final ModelPart bone2;

	public AngelModel(ModelPart root) {
		this.bone2 = root.getChild("bone2");

	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition bone2 = modelPartData.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = bone2.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye = bone.addOrReplaceChild("eye", CubeListBuilder.create(), PartPose.offset(-0.5F, -17.0F, 0.5F));

		PartDefinition rightwing = eye.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -3.75F, 0.5F));

		PartDefinition leftwing = eye.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -4.0F, 0.5F));

		PartDefinition eye_ball = eye.addOrReplaceChild("eye_ball", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).mirror().addBox(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw_eye = eye_ball.addOrReplaceChild("jaw_eye", CubeListBuilder.create().texOffs(0, 25).addBox(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F))
				.texOffs(21, 18).addBox(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.7F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return bone2;
	}

	@Override
	public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimations.FLY_WALK, limbAngle, limbDistance, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ModAnimations.FLY_IDLE, animationProgress, 1f);
		this.animate(entity.attackAnimationState, ModAnimations.FLY_ATTACK, animationProgress, 1f);

	}

}