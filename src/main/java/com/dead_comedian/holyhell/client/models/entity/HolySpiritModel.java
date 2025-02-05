// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.HolySpiritEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class HolySpiritModel <T extends HolySpiritEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;
	private final ModelPart eye;

	public HolySpiritModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.eye = root.getChild("bone").getChild("body").getChild("head").getChild("eye");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-22.0F, 24.0F, 0.0F));

		ModelPartData body = bone.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(22.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -7.0F, -7.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -8.0F, 2.0F));

		ModelPartData left_hand = head.addChild("left_hand", ModelPartBuilder.create().uv(14, 20).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 0.0F, -4.0F));

		ModelPartData right_hand = head.addChild("right_hand", ModelPartBuilder.create().uv(2, 20).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, -4.0F));

		ModelPartData eye = head.addChild("eye", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.6F, -6.75F));

		ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, -8.5F, 2.5F));

		ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create().uv(12, 17).cuboid(-1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -8.5F, 2.5F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(HolySpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.SPIRIT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.updateAnimation(entity.attackAnimationState, ModAnimations.SPIRIT, ageInTicks, 1f);


		Entity entity1 = MinecraftClient.getInstance().getCameraEntity();
		if (entity.getTarget() !=null) {
			entity1 = entity.getTarget();
		}

		if (entity1 != null) {
			Vec3d vec3d = ((Entity)entity1).getCameraPosVec(0.0F);
			Vec3d vec3d2 = entity.getCameraPosVec(0.0F);




			Vec3d vec3d3 = entity.getRotationVec(0.0F);
			vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z);
			Vec3d vec3d4 = (new Vec3d(vec3d2.x - vec3d.x, 0.0, vec3d2.z - vec3d.z)).normalize().rotateY(1.5707964F);
			double e = vec3d3.dotProduct(vec3d4);
			this.eye.pivotX = MathHelper.sqrt((float)Math.abs(e)) * 2.0F * (float)Math.signum(e);
		}

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bone;
	}
}