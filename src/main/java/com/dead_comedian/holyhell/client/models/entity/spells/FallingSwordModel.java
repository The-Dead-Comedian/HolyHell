// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.spells;

import com.dead_comedian.holyhell.entity.custom.spells.FallingSwordEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class FallingSwordModel<T extends FallingSwordEntity> extends SinglePartEntityModel<T> {
	private final ModelPart bone;
	public FallingSwordModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(18, 0).cuboid(-6.4931F, -28.2426F, -1.52F, 13.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.4931F, -26.2426F, -1.02F, 7.0F, 22.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 24).mirrored().cuboid(4.5069F, -26.2426F, -1.52F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 24).cuboid(-6.4931F, -26.2426F, -1.52F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(18, 22).cuboid(-1.4931F, -32.2426F, -1.52F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(18, 5).cuboid(-2.4931F, -37.2426F, -2.52F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.0069F, -5.2574F, 0.02F, 0.0F, 0.0F, -3.1416F));

		ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(18, 15).cuboid(-5.5F, -5.5F, -1.0F, 5.0F, 5.0F, 2.0F, new Dilation(-0.03F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 64, 64);
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