package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.KamikazeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class KamikazeModel<T extends KamikazeEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;

	public KamikazeModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -5.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -6.5F, -6.5F, 13.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 5.5F));

		PartDefinition left_top = body.addOrReplaceChild("left_top", CubeListBuilder.create().texOffs(63, 2).mirror().addBox(-2.0F, -10.0F, 0.0F, 17.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.5F, -13.0F, 5.5F));

		PartDefinition right_top = body.addOrReplaceChild("right_top", CubeListBuilder.create().texOffs(63, 2).addBox(-15.0F, -10.0F, 0.0F, 17.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -13.0F, 5.5F));

		PartDefinition left_bottom = body.addOrReplaceChild("left_bottom", CubeListBuilder.create().texOffs(74, 32).mirror().addBox(-2.0F, -9.0F, 0.0F, 18.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.5F, 0.0F, 5.5F));

		PartDefinition right_bottom = body.addOrReplaceChild("right_bottom", CubeListBuilder.create().texOffs(74, 32).addBox(-16.0F, -9.0F, 0.0F, 18.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, 0.0F, 5.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimations.KAMIKAZE_FLY2, limbSwing, limbSwingAmount, 2f, 2.5f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}