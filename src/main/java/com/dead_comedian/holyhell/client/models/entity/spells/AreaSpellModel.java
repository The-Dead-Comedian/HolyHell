// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.spells;

import com.dead_comedian.holyhell.entity.custom.spells.AreaSpellEntity;
import com.dead_comedian.holyhell.entity.custom.spells.ChristianCrossEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import javax.swing.text.html.parser.Entity;

public class AreaSpellModel <T extends AreaSpellEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bb_main;
	public AreaSpellModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-121.0F, -24.0F, -121.0F, 242.0F, 78.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-241.0F, -24.0F, -1.0F, 242.0F, 78.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-120.0F, 0.0F, 120.0F, -3.1416F, 0.0F, 3.1416F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-242.0F, -24.0F, -0.001F, 242.0F, 78.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-121.002F, 0.0F, 121.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-241.0F, -24.0F, -1.0F, 242.0F, 78.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(121.999F, 0.0F, -120.0F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 512, 512);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bb_main;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}