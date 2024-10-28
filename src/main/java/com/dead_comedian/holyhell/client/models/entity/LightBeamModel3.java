// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class LightBeamModel3<T extends LightBeamEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;

	public LightBeamModel3(ModelPart root) {
		this.bone = root.getChild("bone");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(20, 0).cuboid(-12.5F, 103.0F, -31.5F, 22.0F, 0.0F, 40.0F, new Dilation(0.0F))
				.uv(20, 0).cuboid(-12.5F, 0.0F, -31.5F, 22.0F, 0.0F, 40.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-9.0F, 0.0F, -19.0F, 15.0F, 103.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 0.0F, 11.5F));

		ModelPartData faze3 = bone.addChild("faze3", ModelPartBuilder.create().uv(128, 137).cuboid(-8.0F, -51.5F, -8.0F, 16.0F, 103.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 51.5F, -11.5F));
		return TexturedModelData.of(modelData, 256, 256);
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