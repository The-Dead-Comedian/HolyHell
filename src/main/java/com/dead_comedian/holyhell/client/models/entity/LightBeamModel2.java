// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class LightBeamModel2<T extends LightBeamEntity> extends HierarchicalModel<T> {
	private final ModelPart bone;

	public LightBeamModel2(ModelPart root) {
		this.bone = root.getChild("bone");

	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition bone = modelPartData.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(20, 0).addBox(-12.5F, 103.0F, -31.5F, 22.0F, 0.0F, 40.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-12.5F, 0.0F, -31.5F, 22.0F, 0.0F, 40.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, 0.0F, -19.0F, 15.0F, 103.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 11.5F));

		PartDefinition faze2 = bone.addOrReplaceChild("faze2", CubeListBuilder.create().texOffs(64, 137).addBox(-8.0F, -51.5F, -8.0F, 16.0F, 103.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 51.5F, -11.5F));
		return LayerDefinition.create(modelData, 256, 256);
	}
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return bone;
	}


}