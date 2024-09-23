// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.spells;

import com.dead_comedian.holyhell.entity.custom.spells.GlobularDomeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class GlobularDomeModel <T extends GlobularDomeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;

	public GlobularDomeModel(ModelPart root) {
		this.body = root.getChild("body");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(96, 97).cuboid(-24.0F, -24.0F, -24.0F, 48.0F, 48.0F, 0.0F, new Dilation(0.0F))
		.uv(-48, 97).cuboid(-24.0F, -24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new Dilation(0.0F))
		.uv(-48, 145).cuboid(-24.0F, 24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new Dilation(0.0F))
		.uv(96, 97).cuboid(-24.0F, -24.0F, 24.0F, 48.0F, 48.0F, 0.0F, new Dilation(0.0F))
		.uv(96, 49).cuboid(24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new Dilation(0.0F))
		.uv(96, 49).mirrored().cuboid(-24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 23.999F, 0.0F, 3.1416F, 0.0F, 0.0F));

		ModelPartData eye = body.addChild("eye", ModelPartBuilder.create().uv(48, 145).cuboid(-32.0F, -32.5F, -16.0F, 48.0F, 1.0F, 48.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 8.0F, -8.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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