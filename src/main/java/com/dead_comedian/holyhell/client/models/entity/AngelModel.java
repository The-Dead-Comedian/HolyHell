// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animations.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class AngelModel <T extends AngelEntity> extends SinglePartEntityModel<T> {
	private final ModelPart eye;

	public AngelModel(ModelPart root) {
		this.eye = root.getChild("eye");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData eye = modelPartData.addChild("eye", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 7.0F, 0.5F));

		ModelPartData rightwing = eye.addChild("rightwing", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(0.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.5F, -3.75F, 0.5F));

		ModelPartData leftwing = eye.addChild("leftwing", ModelPartBuilder.create().uv(0, 0).cuboid(-15.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, -4.0F, 0.5F));

		ModelPartData eye_ball = eye.addChild("eye_ball", ModelPartBuilder.create().uv(0, 13).cuboid(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 25).mirrored().cuboid(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new Dilation(-0.1F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData jaw_eye = eye_ball.addChild("jaw_eye", ModelPartBuilder.create().uv(0, 25).cuboid(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new Dilation(-0.1F))
		.uv(21, 18).cuboid(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 3.7F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		eye.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return eye;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.FLY_WALK, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.FLY_IDLE, animationProgress, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.FLY_ATTACK, animationProgress, 1f);

	}
}