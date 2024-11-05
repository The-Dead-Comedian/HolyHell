// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;

import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BabOneModel<T extends BabOneEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone2;
	public BabOneModel(ModelPart root) {
		this.bone2 = root.getChild("bone2");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone2 = modelPartData.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bone = bone2.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -3.5F, -5.0F, 10.0F, 7.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, 0.0F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);

	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.BABWALK1, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.updateAnimation(entity.Lvl1IdleAnimationState, ModAnimations.BABIDLE1, ageInTicks, 1f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bone2;
	}
}