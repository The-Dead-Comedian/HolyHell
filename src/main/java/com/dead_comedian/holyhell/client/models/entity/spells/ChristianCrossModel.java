// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.spells;

import com.dead_comedian.holyhell.entity.custom.spells.ChristianCrossEntity;
import com.dead_comedian.holyhell.entity.custom.spells.LastPrayerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class ChristianCrossModel<T extends ChristianCrossEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;

	public ChristianCrossModel(ModelPart root) {
		this.bone = root.getChild("bone");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 29).cuboid(0.0F, -25.0F, -18.0F, 0.0F, 48.0F, 37.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(-66, 0).cuboid(-33.0F, 0.0F, -33.0F, 66.0F, 0.0F, 66.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 30.0F, 0.0F));
		return TexturedModelData.of(modelData, 512, 512);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bone;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}