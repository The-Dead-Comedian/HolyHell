// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.model.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;

import com.dead_comedian.holyhell.entity.BabThreeEntity;
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

public class BabThreeModel <T extends BabThreeEntity> extends HierarchicalModel<T> {
	private final ModelPart bone;





	public BabThreeModel(ModelPart root) {
		this.bone = root.getChild("bone");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 20.9F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 1.1F, 0.0F));

		PartDefinition right_arm = head.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5F, -1.0F, -2.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(48, 27).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -5.0F, -5.5F));

		PartDefinition left_arm = head.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5F, -1.0F, -0.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(48, 27).mirror().addBox(-2.0F, -1.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, -5.0F, 5.5F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition robe = head.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(20, 41).addBox(-5.5F, -10.0F, -5.5F, 11.0F, 12.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition trail = robe.addOrReplaceChild("trail", CubeListBuilder.create().texOffs(-7, 53).addBox(-4.0F, 0.0F, -5.5F, 8.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.5F, 2.001F, 0.0F));

		PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(12, 17).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.1F, 2.0F));

		PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(12, 17).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.1F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimations.BABWALK3, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.animate(entity.Lvl3IdleAnimationState, ModAnimations.BABIDLE3, ageInTicks, 1f);
		this.animate(entity.Lvl3AttackAnimationState, ModAnimations.BABATTACK3, ageInTicks, 1f);
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return bone;
	}
}