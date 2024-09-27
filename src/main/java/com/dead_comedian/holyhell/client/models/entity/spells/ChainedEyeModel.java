// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

/*package com.example.mod;
   
public class ChainedEyeModel extends EntityModel<Entity> {
	private final ModelPart bb_main;
	public ChainedEyeModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-6.5F, -5.0F, -6.5F, 13.0F, 5.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-1.0F, -7.0F, 1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(28, 18).cuboid(-1.0F, -9.0F, -3.5F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(28, 27).cuboid(0.0F, -16.0F, -2.5F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(28, 27).cuboid(0.0F, -25.0F, -2.5F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 18).cuboid(-3.75F, -32.0F, -3.5F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(28, 27).cuboid(1.0F, -4.0F, 4.0F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(28, 27).cuboid(1.0F, -13.0F, 4.0F, 0.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -8.0F, 1.5F, 0.0F, 1.6144F, 0.0F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, -2.5F, 0.0F, 3.1416F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}*/