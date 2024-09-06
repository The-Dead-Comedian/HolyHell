// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import javax.swing.text.html.parser.Entity;


public class KamikazeAngelModel  <T extends KamikazeAngelEntity> extends SinglePartEntityModel<T> {
	private final ModelPart eye;

	public KamikazeAngelModel(ModelPart root) {
		this.eye = root.getChild("eye");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData eye = modelPartData.addChild("eye", ModelPartBuilder.create().uv(0, 0).cuboid(-6.5F, -5.0F, -8.5F, 13.0F, 13.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		ModelPartData wing_up_right = eye.addChild("wing_up_right", ModelPartBuilder.create().uv(52, 0).cuboid(-10.0F, -15.0F, -2.0F, 11.0F, 15.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, -5.0F, 0.0F));

		ModelPartData wing_up_left = eye.addChild("wing_up_left", ModelPartBuilder.create().uv(44, 38).cuboid(-1.0F, -15.0F, -2.0F, 11.0F, 15.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -5.0F, 0.0F));

		ModelPartData wing_mid_right = eye.addChild("wing_mid_right", ModelPartBuilder.create().uv(30, 26).cuboid(-15.0F, -8.0F, -2.0F, 15.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, 2.0F, 0.0F));

		ModelPartData wing_mid_left = eye.addChild("wing_mid_left", ModelPartBuilder.create().uv(0, 26).cuboid(0.0F, -8.0F, -2.0F, 15.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(6.5F, 2.0F, 0.0F));

		ModelPartData wing_low_right = eye.addChild("wing_low_right", ModelPartBuilder.create().uv(22, 38).cuboid(-10.0F, 0.0F, -2.0F, 11.0F, 15.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, 8.0F, 0.0F));

		ModelPartData wing_low_left = eye.addChild("wing_low_left", ModelPartBuilder.create().uv(0, 38).cuboid(-1.0F, 0.0F, -2.0F, 11.0F, 15.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, 8.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.KAMIKAZE_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		eye.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return eye;
	}
}