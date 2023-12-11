package net.deadcomedian.holyhell.entity.client;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deadcomedian.holyhell.entity.animations.ModAnimationDefinitions;

import net.deadcomedian.holyhell.entity.custom.HailingHereticEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class HailingHereticModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hailinghereticmodel"), "main");
	private final ModelPart hole;
	private final ModelPart head;

	public HailingHereticModel(ModelPart root) {
		this.hole = root.getChild("hole");
		this.head = root.getChild("hole").getChild("bottom").getChild("body").getChild("top").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hole = partdefinition.addOrReplaceChild("hole", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bottom = hole.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, -4.6174F, -8.4803F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, 14.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition back_right_leg2 = bottom.addOrReplaceChild("back_right_leg2", CubeListBuilder.create().texOffs(30, 48).addBox(-2.5125F, 0.8536F, -2.3536F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-3.0125F, 5.1036F, -2.8536F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9875F, -0.7209F, -2.1267F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r1 = back_right_leg2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 31).addBox(-2.975F, -2.3232F, -1.3232F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4875F, 1.3536F, -0.8536F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone2 = back_right_leg2.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(37, 62).addBox(-1.75F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(62, 14).addBox(-2.5F, 1.5F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2625F, 9.8536F, 0.1464F));

		PartDefinition back_left_leg2 = bottom.addOrReplaceChild("back_left_leg2", CubeListBuilder.create().texOffs(30, 48).mirror().addBox(-2.4875F, 0.8536F, -2.3536F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 0).mirror().addBox(-2.9875F, 5.1036F, -2.8536F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.2375F, -0.7209F, -2.1267F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r2 = back_left_leg2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 31).mirror().addBox(-2.025F, -2.3232F, -1.3232F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4875F, 1.3536F, -0.8536F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone3 = back_left_leg2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(37, 62).mirror().addBox(-2.25F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(62, 14).mirror().addBox(-2.5F, 1.5F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.2625F, 9.8536F, 0.1464F));

		PartDefinition body = bottom.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -0.5F, -14.0F, 9.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.6174F, -8.4803F, 0.3054F, 0.0F, 0.0F));

		PartDefinition top = body.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 40).addBox(-3.5F, -2.653F, -7.452F, 7.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -13.5F, 0.48F, 0.0F, 0.0F));

		PartDefinition head = top.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 32).addBox(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
		.texOffs(38, 15).addBox(-4.0F, -4.0F, -8.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 1.847F, -5.452F, -0.48F, 0.0F, 0.0F));

		PartDefinition front_right_leg = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(30, 48).addBox(-2.5062F, 1.4786F, -2.1768F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-3.0062F, 5.4786F, -2.6768F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.9938F, 3.0214F, -8.8232F));

		PartDefinition cube_r3 = front_right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 55).addBox(-2.975F, -3.5F, -1.5F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4938F, 1.9786F, -0.3268F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone = front_right_leg.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(37, 62).addBox(-1.75F, 0.125F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(62, 14).addBox(-2.25F, 2.375F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2562F, 10.3536F, 0.3232F));

		PartDefinition front_left_leg = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(30, 48).mirror().addBox(-2.4938F, 1.4786F, -2.1768F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 0).mirror().addBox(-2.9938F, 5.4786F, -2.6768F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.9938F, 3.0214F, -8.8232F));

		PartDefinition cube_r4 = front_left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 55).mirror().addBox(-2.025F, -3.5F, -1.5F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4938F, 1.9786F, -0.1768F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone4 = front_left_leg.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(37, 62).mirror().addBox(-2.25F, 0.125F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(62, 14).mirror().addBox(-2.75F, 2.375F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.2562F, 10.3536F, 0.3232F));

		PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.HERETIC_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((HailingHereticEntity) entity).idleAnimationState, ModAnimationDefinitions.HERETIC_IDLE, ageInTicks, 1f);
		this.animate(((HailingHereticEntity) entity).attackAnimationState, ModAnimationDefinitions.HERETIC_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

	}

	@Override
	public ModelPart root() {
		return hole;
	}
}