// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.model.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.HolySpiritEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class HolySpiritModel <T extends HolySpiritEntity> extends HierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart eye;

	public HolySpiritModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.eye = root.getChild("bone").getChild("body").getChild("head").getChild("eye");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition bone = modelPartData.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-22.0F, 24.0F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(22.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -7.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, 2.0F));

		PartDefinition left_hand = head.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(14, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, -4.0F));

		PartDefinition right_hand = head.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(2, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, -4.0F));

		PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.6F, -6.75F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -8.5F, 2.5F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(12, 17).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -8.5F, 2.5F));
		return LayerDefinition.create(modelData, 32, 32);
	}
	@Override
	public void setupAnim(HolySpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ModAnimations.SPIRIT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.animate(entity.attackAnimationState, ModAnimations.SPIRIT, ageInTicks, 1f);


		Entity entity1 = Minecraft.getInstance().getCameraEntity();
		if (entity.getTarget() !=null) {
			entity1 = entity.getTarget();
		}

		if (entity1 != null) {
			Vec3 vec3d = ((Entity)entity1).getEyePosition(0.0F);
			Vec3 vec3d2 = entity.getEyePosition(0.0F);




			Vec3 vec3d3 = entity.getViewVector(0.0F);
			vec3d3 = new Vec3(vec3d3.x, 0.0, vec3d3.z);
			Vec3 vec3d4 = (new Vec3(vec3d2.x - vec3d.x, 0.0, vec3d2.z - vec3d.z)).normalize().yRot(1.5707964F);
			double e = vec3d3.dot(vec3d4);
			this.eye.x = Mth.sqrt((float)Math.abs(e)) * 2.0F * (float)Math.signum(e);
		}

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