// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.other;

import com.dead_comedian.holyhell.entity.custom.other.TrappedStoneCrossEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class TrappedStoneCrossModel<T extends TrappedStoneCrossEntity> extends SinglePartEntityModel<T> {

	private final ModelPart bone;
	public TrappedStoneCrossModel(ModelPart root) {

		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 48).cuboid(7.0F, 0.0F, -15.0F, 10.0F, 8.0F, 9.0F, new Dilation(0.0F))
				.uv(38, 48).cuboid(-11.0F, 0.0F, -15.0F, 10.0F, 8.0F, 9.0F, new Dilation(0.0F))
				.uv(56, 62).cuboid(-9.5F, 8.0F, -12.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(56, 65).cuboid(-11.0F, 8.0F, -10.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(56, 65).cuboid(14.0F, 8.0F, -10.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(56, 62).cuboid(15.5F, 8.0F, -12.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(56, 65).cuboid(1.5F, 8.0F, -20.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(56, 62).cuboid(3.0F, 8.0F, -22.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(56, 65).cuboid(1.5F, 8.0F, 16.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(56, 62).cuboid(3.0F, 8.0F, 15.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(31, 80).cuboid(-1.0F, 0.0F, -22.0F, 8.0F, 8.0F, 40.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -1.0F, 2.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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