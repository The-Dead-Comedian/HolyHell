// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;



public class KamikazeAngelModel  <T extends KamikazeAngelEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;

	public KamikazeAngelModel(ModelPart root) {
		this.body = root.getChild("body");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-6.5F, -6.5F, -6.5F, 13.0F, 13.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.5F, 5.5F));

		ModelPartData left_top = body.addChild("left_top", ModelPartBuilder.create().uv(63, 2).mirrored().cuboid(-2.0F, -10.0F, 0.0F, 17.0F, 12.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(6.5F, -13.0F, 5.5F));

		ModelPartData right_top = body.addChild("right_top", ModelPartBuilder.create().uv(63, 2).cuboid(-15.0F, -10.0F, 0.0F, 17.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, -13.0F, 5.5F));

		ModelPartData left_bottom = body.addChild("left_bottom", ModelPartBuilder.create().uv(74, 32).mirrored().cuboid(-2.0F, -9.0F, 0.0F, 18.0F, 12.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(6.5F, 0.0F, 5.5F));

		ModelPartData right_bottom = body.addChild("right_bottom", ModelPartBuilder.create().uv(74, 32).cuboid(-16.0F, -9.0F, 0.0F, 18.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, 0.0F, 5.5F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.KAMIKAZE_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
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