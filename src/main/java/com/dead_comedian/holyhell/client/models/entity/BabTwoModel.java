// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;
import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.BabThreeEntity;
import com.dead_comedian.holyhell.entity.custom.BabTwoEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
   
public class BabTwoModel <T extends BabTwoEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;

	public BabTwoModel(ModelPart root) {
		this.bone = root.getChild("bone");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.of(0.0F, 20.9F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData head = bone.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.1F, 0.0F));

		ModelPartData right_arm = head.addChild("right_arm", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -1.0F, -2.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, -5.0F, -5.5F));

		ModelPartData left_arm = head.addChild("left_arm", ModelPartBuilder.create().uv(0, 17).cuboid(-1.5F, -1.0F, -0.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, -5.0F, 5.5F));

		ModelPartData bone2 = head.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -7.0F, -5.0F, 10.0F, 7.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_leg = bone.addChild("left_leg", ModelPartBuilder.create().uv(12, 17).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.1F, 2.0F));

		ModelPartData right_leg = bone.addChild("right_leg", ModelPartBuilder.create().uv(12, 17).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.1F, -2.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(ModAnimations.BABWALK2, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.updateAnimation(entity.Lvl2IdleAnimationState, ModAnimations.BABIDLE2, ageInTicks, 1f);
		this.updateAnimation(entity.Lvl2IdleAnimationState, ModAnimations.BABATTACK2, ageInTicks, 1f);
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