package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.server.entity.HolyCowEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;


public class HolyCowModel<T extends HolyCowEntity> extends HierarchicalModel<T> {
	private final ModelPart bomboclat;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;

	public HolyCowModel(ModelPart root) {


		this.bomboclat = root.getChild("bomboclat");
		this.head = this.bomboclat.getChild("head");
		this.body = this.bomboclat.getChild("body");
		this.leg0 = this.bomboclat.getChild("leg0");
		this.leg1 = this.bomboclat.getChild("leg1");
		this.leg2 = this.bomboclat.getChild("leg2");
		this.leg3 = this.bomboclat.getChild("leg3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bomboclat = partdefinition.addOrReplaceChild("bomboclat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition head = bomboclat.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 52).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(44, 37).addBox(-3.5F, -11.0F, -6.5F, 7.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(62, 16).addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 27).addBox(-5.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 27).addBox(4.0F, -5.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -8.0F));

		PartDefinition body = bomboclat.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -19.0F, 2.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(62, 20).addBox(-2.0F, -17.0F, -7.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -29.0F, -15.0F, 12.0F, 18.0F, 19.0F, new CubeDeformation(0.2F))
				.texOffs(0, 37).addBox(-6.0F, -29.0F, -6.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 19.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition leg0 = bomboclat.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(62, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, 7.0F));

		PartDefinition leg1 = bomboclat.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(62, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -12.0F, 7.0F));

		PartDefinition leg2 = bomboclat.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(62, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, -6.0F));

		PartDefinition leg3 = bomboclat.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(62, 0).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -12.0F, -6.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public ModelPart getHead() {
		return this.head;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		bomboclat.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3);
	}


	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.leg0.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
		this.leg1.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leg2.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
		this.leg3.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
	}

	@Override
	public ModelPart root() {
		return bomboclat;
	}

}