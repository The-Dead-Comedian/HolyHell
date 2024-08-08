// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;



public class HailingHereticModel <T extends HailingHereticEntity> extends SinglePartEntityModel<T> {
	private final ModelPart hole;

	public HailingHereticModel(ModelPart root) {
		this.hole = root.getChild("hole");


	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData hole = modelPartData.addChild("hole", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 26.0F, 0.0F));

		ModelPartData bottom = hole.addChild("bottom", ModelPartBuilder.create().uv(5, 6).cuboid(-4.0F, -4.6174F, -8.4803F, 8.0F, 8.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -14.5F, 14.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData back_right_leg2 = bottom.addChild("back_right_leg2", ModelPartBuilder.create().uv(11, 9).cuboid(-2.5125F, 0.8536F, -2.3536F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F))
		.uv(30, 48).cuboid(-3.0125F, 5.1036F, -2.8536F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.9875F, -2.7209F, -2.1267F, 0.3054F, 0.0F, 0.0F));

		ModelPartData bone2 = back_right_leg2.addChild("bone2", ModelPartBuilder.create().uv(13, 11).cuboid(-1.75F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
		.uv(50, 31).cuboid(-2.5F, 1.5F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.2625F, 9.8536F, 0.1464F));

		ModelPartData back_left_leg2 = bottom.addChild("back_left_leg2", ModelPartBuilder.create().uv(12, 9).mirrored().cuboid(-2.4875F, 0.8536F, -2.3536F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(30, 48).mirrored().cuboid(-2.9875F, 5.1036F, -2.8536F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.2375F, -2.7209F, -2.1267F, 0.3054F, 0.0F, 0.0F));

		ModelPartData bone3 = back_left_leg2.addChild("bone3", ModelPartBuilder.create().uv(13, 11).mirrored().cuboid(-2.25F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(50, 31).mirrored().cuboid(-2.5F, 1.5F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.2625F, 9.8536F, 0.1464F));

		ModelPartData body = bottom.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -0.5F, -14.0F, 9.0F, 9.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.6174F, -8.4803F, 0.3054F, 0.0F, 0.0F));

		ModelPartData top = body.addChild("top", ModelPartBuilder.create().uv(0, 40).cuboid(-3.5F, -2.653F, -7.452F, 7.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 3.5F, -13.5F, 0.48F, 0.0F, 0.0F));

		ModelPartData head = top.addChild("head", ModelPartBuilder.create().uv(26, 32).cuboid(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F))
		.uv(38, 15).cuboid(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 1.847F, -5.452F, -0.48F, 0.0F, 0.0F));

		ModelPartData front_right_leg = body.addChild("front_right_leg", ModelPartBuilder.create().uv(10, 9).cuboid(-2.5062F, 1.4786F, -2.1768F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F))
		.uv(30, 48).cuboid(-3.0062F, 5.4786F, -2.6768F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.9938F, 1.0214F, -8.8232F));

		ModelPartData bone = front_right_leg.addChild("bone", ModelPartBuilder.create().uv(15, 9).cuboid(-1.75F, 0.125F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(50, 31).cuboid(-2.25F, 2.375F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.2562F, 10.3536F, 0.3232F));

		ModelPartData front_left_leg = body.addChild("front_left_leg", ModelPartBuilder.create().uv(11, 9).mirrored().cuboid(-2.4938F, 1.4786F, -2.1768F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(30, 48).mirrored().cuboid(-2.9938F, 5.4786F, -2.6768F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.9938F, 1.0214F, -8.8232F));

		ModelPartData bone4 = front_left_leg.addChild("bone4", ModelPartBuilder.create().uv(14, 8).mirrored().cuboid(-2.25F, 0.125F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(50, 31).mirrored().cuboid(-2.75F, 2.375F, -2.5F, 5.0F, 3.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.2562F, 10.3536F, 0.3232F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		hole.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return hole;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.HERETIC_WALK, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.HERETIC_IDLE, animationProgress, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.HERETIC_ATTACK, animationProgress, 1f);

	}
}