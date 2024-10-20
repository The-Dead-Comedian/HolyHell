// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;

import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.custom.BabEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BabThreeModel <T extends BabEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;

	public BabThreeModel(ModelPart root) {
		this.bone = root.getChild("bone");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 19.0F, 0.0F));

		ModelPartData torso = bone.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -0.5F, -6.0F, 12.0F, 4.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-6.0F, -2.5F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -3.5F, 0.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 30).cuboid(-5.0F, -3.5F, -5.0F, 10.0F, 7.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

		ModelPartData right_arm = torso.addChild("right_arm", ModelPartBuilder.create().uv(40, 43).cuboid(-2.5F, -2.0F, -4.0F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, -7.0F));

		ModelPartData left_arm = torso.addChild("left_arm", ModelPartBuilder.create().uv(40, 43).cuboid(-2.5F, -2.0F, -1.5F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, 7.5F));

		ModelPartData left_leg = bone.addChild("left_leg", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, -0.5F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.5F, 3.0F));

		ModelPartData right_leg = bone.addChild("right_leg", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, -0.5F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.5F, -3.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(ModAnimations.BABWALK3, limbSwing, limbSwingAmount, 2f, 2.5f);

        this.updateAnimation(entity.Lvl3IdleAnimationState, ModAnimations.BABIDLE3, ageInTicks, 1f);
        this.updateAnimation(entity.Lvl3AttackAnimationState, ModAnimations.BABATTACK3, ageInTicks, 1f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart getPart() {
        return null;
    }
}