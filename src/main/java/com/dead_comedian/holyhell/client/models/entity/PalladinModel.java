// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.PalladinEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PalladinModel<T extends PalladinEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;

	public PalladinModel(ModelPart root) {
		this.body = root.getChild("body");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-10.0F, -18.0F, -5.5F, 20.0F, 18.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 29).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 53).cuboid(-12.0F, -22.0F, 0.0F, 24.0F, 16.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 0.0F));

		ModelPartData left_hand = torso.addChild("left_hand", ModelPartBuilder.create().uv(62, 0).cuboid(-7.7375F, 6.5F, -4.0F, 8.0F, 15.0F, 8.0F, new Dilation(0.0F))
				.uv(48, 29).mirrored().cuboid(-9.7375F, -4.5F, -5.0F, 10.0F, 15.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 69).cuboid(-7.7625F, 1.5F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 69).cuboid(-7.7625F, -3.5F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-10.2625F, -16.5F, 0.0F));

		ModelPartData right_hand = torso.addChild("right_hand", ModelPartBuilder.create().uv(62, 0).mirrored().cuboid(-0.2625F, 6.5F, -4.0F, 8.0F, 15.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(48, 29).cuboid(-0.2625F, -4.5F, -5.0F, 10.0F, 15.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 69).mirrored().cuboid(-0.2375F, 1.5F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 69).mirrored().cuboid(-0.2375F, -3.5F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(10.2625F, -16.5F, 0.0F));

		ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create().uv(32, 78).cuboid(-3.5F, 0.0F, -3.5F, 7.0F, 8.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.5F, -8.0F, 0.0F));

		ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create().uv(32, 78).mirrored().cuboid(-3.5F, 0.0F, -3.5F, 7.0F, 8.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.5F, -8.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.PALLADIN_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.PALLADIN_IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.PALLADIN_CAST, ageInTicks, 1f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return body;
	}
}