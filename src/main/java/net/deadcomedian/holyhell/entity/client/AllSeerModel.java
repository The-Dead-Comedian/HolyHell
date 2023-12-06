package net.deadcomedian.holyhell.entity.client;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deadcomedian.holyhell.entity.animations.ModAnimationDefinitions;

import net.deadcomedian.holyhell.entity.custom.AllSeerEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class AllSeerModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "allseer"), "main");
	private final ModelPart all;
	private final ModelPart head;
	public AllSeerModel(ModelPart root) {
		this.all = root.getChild("all");
		this.head =root.getChild("all").getChild("eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -1.0F));

		PartDefinition eye = all.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -16.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition right_chain = eye.addOrReplaceChild("right_chain", CubeListBuilder.create().texOffs(96, 0).addBox(-5.55F, -4.2F, -6.3F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(87, 66).addBox(-3.55F, -6.2F, -1.8F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(87, 66).addBox(1.45F, -6.2F, -1.8F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(66, 87).addBox(-3.55F, -8.2F, -1.8F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-64.45F, 16.2F, -3.7F));

		PartDefinition chain = right_chain.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(28, 56).addBox(0.0F, -10.0F, -4.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.45F, -6.2F, -0.3F));

		PartDefinition chain2 = chain.addOrReplaceChild("chain2", CubeListBuilder.create().texOffs(28, 84).addBox(-3.0F, -7.0F, 0.0F, 6.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition chain3 = chain2.addOrReplaceChild("chain3", CubeListBuilder.create().texOffs(28, 69).addBox(0.0F, -7.0F, -3.0F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition chain4 = chain3.addOrReplaceChild("chain4", CubeListBuilder.create().texOffs(88, 87).addBox(-2.5F, -7.0F, 0.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.05F, -5.0F, 0.0F));

		PartDefinition chain5 = chain4.addOrReplaceChild("chain5", CubeListBuilder.create().texOffs(0, 85).addBox(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.05F, -6.0F, 0.0F));

		PartDefinition chain6 = chain5.addOrReplaceChild("chain6", CubeListBuilder.create().texOffs(21, 0).addBox(-2.5F, -6.0F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition eye3 = chain6.addOrReplaceChild("eye3", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.5F));

		PartDefinition rightwing = eye3.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -6.75F, 0.5F));

		PartDefinition leftwing = eye3.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 19).addBox(-15.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -7.0F, 0.5F));

		PartDefinition eye_ball = eye3.addOrReplaceChild("eye_ball", CubeListBuilder.create().texOffs(66, 66).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition jaw_eye = eye_ball.addOrReplaceChild("jaw_eye", CubeListBuilder.create().texOffs(0, 81).addBox(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F))
				.texOffs(66, 78).addBox(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.7F));

		PartDefinition left_chain = eye.addOrReplaceChild("left_chain", CubeListBuilder.create().texOffs(96, 0).addBox(-6.45F, -4.2F, -6.3F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(87, 66).addBox(0.55F, -6.2F, -1.8F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(87, 66).addBox(-4.45F, -6.2F, -1.8F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(66, 87).addBox(-4.45F, -8.2F, -1.8F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(64.45F, 16.2F, -3.7F));

		PartDefinition chain7 = left_chain.addOrReplaceChild("chain7", CubeListBuilder.create().texOffs(28, 56).addBox(0.0F, -10.0F, -4.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.45F, -6.2F, -0.3F));

		PartDefinition chain8 = chain7.addOrReplaceChild("chain8", CubeListBuilder.create().texOffs(28, 84).addBox(-3.0F, -8.0F, 0.0F, 6.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition chain9 = chain8.addOrReplaceChild("chain9", CubeListBuilder.create().texOffs(28, 69).addBox(0.0F, -8.0F, -3.0F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition chain10 = chain9.addOrReplaceChild("chain10", CubeListBuilder.create().texOffs(88, 87).addBox(-2.5F, -7.0F, 0.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.05F, -7.0F, 0.0F));

		PartDefinition chain11 = chain10.addOrReplaceChild("chain11", CubeListBuilder.create().texOffs(0, 85).addBox(0.0F, -5.0F, -2.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.05F, -6.0F, 0.0F));

		PartDefinition chain12 = chain11.addOrReplaceChild("chain12", CubeListBuilder.create().texOffs(21, 0).addBox(-2.5F, -6.0F, 0.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition eye2 = chain12.addOrReplaceChild("eye2", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.5F));

		PartDefinition rightwing2 = eye2.addOrReplaceChild("rightwing2", CubeListBuilder.create().texOffs(0, 19).addBox(-15.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -6.75F, 0.5F));

		PartDefinition leftwing2 = eye2.addOrReplaceChild("leftwing2", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -7.0F, 0.5F));

		PartDefinition eye_ball2 = eye2.addOrReplaceChild("eye_ball2", CubeListBuilder.create().texOffs(66, 66).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 81).addBox(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition jaw_eye2 = eye_ball2.addOrReplaceChild("jaw_eye2", CubeListBuilder.create().texOffs(0, 81).addBox(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F))
				.texOffs(66, 78).addBox(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.7F));

		PartDefinition rings = eye.addOrReplaceChild("rings", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition ring3 = rings.addOrReplaceChild("ring3", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -25.0F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(-5.0F, -25.5F, -23.0F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.475F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r1 = ring3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, -1.0F, -23.0F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r2 = ring3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -1.0F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 24.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition ring2 = rings.addOrReplaceChild("ring2", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -25.0F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(-5.0F, -25.5F, -23.0F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 1.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r3 = ring2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, -1.0F, -23.0F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r4 = ring2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -1.0F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 24.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition ring = rings.addOrReplaceChild("ring", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -25.025F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 64).addBox(-5.0F, -25.5F, -23.025F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.525F, 1.0F));

		PartDefinition cube_r5 = ring.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, -1.0F, -23.0F, 10.0F, 2.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, -0.025F, 0.0F, 0.0F, -3.1416F));

		PartDefinition cube_r6 = ring.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(74, 114).addBox(-5.0F, -25.5F, -1.0F, 10.0F, 51.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 23.975F, 0.0F, 3.1416F, 0.0F));

		PartDefinition hands = all.addOrReplaceChild("hands", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_hand = hands.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(0, 112).addBox(-6.5F, -0.5F, -12.0F, 13.0F, 25.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(-39.5F, 17.5F, -2.0F));

		PartDefinition left_pinkie = left_hand.addOrReplaceChild("left_pinkie", CubeListBuilder.create().texOffs(0, 64).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, 7.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_pinkie2 = left_pinkie.addOrReplaceChild("left_pinkie2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition left_pointer = left_hand.addOrReplaceChild("left_pointer", CubeListBuilder.create().texOffs(0, 64).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, -7.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_pointer2 = left_pointer.addOrReplaceChild("left_pointer2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition left_thumb = left_hand.addOrReplaceChild("left_thumb", CubeListBuilder.create().texOffs(0, 64).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.5F, -11.5F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_thumb2 = left_thumb.addOrReplaceChild("left_thumb2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition left_middle = left_hand.addOrReplaceChild("left_middle", CubeListBuilder.create().texOffs(0, 64).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_middle2 = left_middle.addOrReplaceChild("left_middle2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition right_hand = hands.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(0, 112).mirror().addBox(-6.5F, -0.5F, -12.0F, 13.0F, 25.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(39.5F, 17.5F, -2.0F));

		PartDefinition right_pinkie = right_hand.addOrReplaceChild("right_pinkie", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.5F, 7.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_pinkie2 = right_pinkie.addOrReplaceChild("right_pinkie2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition right_pointer = right_hand.addOrReplaceChild("right_pointer", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.5F, -7.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_pointer2 = right_pointer.addOrReplaceChild("right_pointer2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition right_thumb = right_hand.addOrReplaceChild("right_thumb", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 10.5F, -11.5F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_thumb2 = right_thumb.addOrReplaceChild("right_thumb2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition right_middle = right_hand.addOrReplaceChild("right_middle", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_middle2 = right_middle.addOrReplaceChild("right_middle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.ALLSEER_IDLE, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((AllSeerEntity) entity).idleAnimationState, ModAnimationDefinitions.ALLSEER_IDLE, ageInTicks, 1f);

		this.animate(((AllSeerEntity) entity).attackAnimationState, ModAnimationDefinitions.ALLSEER_ATTACK1, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

@Override
	public ModelPart root() {
		return all;
	}


}