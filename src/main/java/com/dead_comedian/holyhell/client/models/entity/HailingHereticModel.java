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
		ModelPartData hole = modelPartData.addChild("hole", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bottom = hole.addChild("bottom", ModelPartBuilder.create().uv(0, 20).cuboid(-4.5F, -5.1174F, -13.4803F, 9.0F, 9.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.5F, 14.0F));

		ModelPartData back_right_leg2 = bottom.addChild("back_right_leg2", ModelPartBuilder.create().uv(20, 47).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F))
				.uv(40, 0).cuboid(-2.5F, 9.0F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -2.5F, -4.5F));

		ModelPartData back_left_leg2 = bottom.addChild("back_left_leg2", ModelPartBuilder.create().uv(40, 0).cuboid(-2.5F, 9.0F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F))
				.uv(20, 47).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -2.5F, -4.5F));

		ModelPartData body = bottom.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, -5.1174F, -9.0F, 11.0F, 11.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -13.4803F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(30, 31).cuboid(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F))
				.uv(32, 12).cuboid(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.4796F, -8.952F));

		ModelPartData front_right_leg = body.addChild("front_right_leg", ModelPartBuilder.create().uv(20, 47).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F))
				.uv(40, 47).cuboid(-2.5F, 9.0F, -2.5F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -3.5F, -5.5197F));

		ModelPartData front_left_leg = body.addChild("front_left_leg", ModelPartBuilder.create().uv(40, 47).cuboid(-2.4938F, 9.0959F, -2.4465F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F))
				.uv(20, 47).cuboid(-2.4938F, 0.0959F, -2.4465F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.9938F, -3.5959F, -5.5732F));
		return TexturedModelData.of(modelData, 64, 64);
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

		this.updateAnimation(entity.attackAnimationState, ModAnimations.HERETIC_ATTACK, animationProgress, 1f);

	}
}