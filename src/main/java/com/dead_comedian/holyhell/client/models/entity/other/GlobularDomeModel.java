// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.dead_comedian.holyhell.client.models.entity.other;

import com.dead_comedian.holyhell.entity.custom.other.GlobularDomeEntity;
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

public class GlobularDomeModel <T extends GlobularDomeEntity> extends HierarchicalModel<T> {
	private final ModelPart body;

	public GlobularDomeModel(ModelPart root) {
		this.body = root.getChild("body");

	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(96, 97).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 48.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-48, 97).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(-48, 145).addBox(-24.0F, 24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(96, 97).addBox(-24.0F, -24.0F, 24.0F, 48.0F, 48.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(96, 49).addBox(24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
		.texOffs(96, 49).mirror().addBox(-24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 23.999F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(48, 145).addBox(-32.0F, -32.5F, -16.0F, 48.0F, 1.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
		return LayerDefinition.create(modelData, 256, 256);
	}
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}