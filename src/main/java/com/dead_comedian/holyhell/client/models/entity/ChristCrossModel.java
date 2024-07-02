// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
/*
package com.example.mod;

public class CustomModel extends EntityModel<Entity> {
	private final ModelPart bb_main;
	public CustomModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new Dilation(0.0F))
		.uv(30, 0).cuboid(-3.0F, -13.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 48).cuboid(-2.0F, -27.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F))
		.uv(26, 53).cuboid(-3.0F, -35.0F, -3.0F, 6.0F, 8.0F, 6.0F, new Dilation(0.0F))
		.uv(16, 49).cuboid(-2.0F, -41.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
		.uv(1, 19).mirrored().cuboid(-2.0F, -34.0F, 3.0F, 4.0F, 4.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
		.uv(34, 13).cuboid(-3.0F, -35.0F, 13.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(34, 13).cuboid(-3.0F, -35.0F, -19.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 33).cuboid(-4.0F, -48.0F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F))
		.uv(1, 19).cuboid(-2.0F, -34.0F, 3.0F, 4.0F, 4.0F, 10.0F, new Dilation(0.0F))
		.uv(56, 29).cuboid(-2.0F, -34.0F, -13.0F, 4.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}*/