package com.dead_comedian.holyhell.client.model.entity.non_living;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.server.entity.non_living.GlobularDomeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GlobularDomeModel <T extends GlobularDomeEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;
	private final ModelPart eye;

	public GlobularDomeModel(ModelPart root) {
		this.body = root.getChild("body");
		this.eye = this.body.getChild("eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(96, 97).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 48.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(-48, 97).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(-48, 145).addBox(-24.0F, 24.0F, -24.0F, 48.0F, 0.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(96, 97).mirror().addBox(-24.0F, -24.0F, 24.0F, 48.0F, 48.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(96, 49).addBox(24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(96, 49).mirror().addBox(-24.0F, -24.0F, -24.0F, 0.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(48, 145).addBox(-32.0F, -32.5F, -16.0F, 48.0F, 1.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}