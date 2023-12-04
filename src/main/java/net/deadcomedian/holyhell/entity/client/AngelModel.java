package net.deadcomedian.holyhell.entity.client;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.deadcomedian.holyhell.entity.animations.ModAnimationDefinitions;
import net.deadcomedian.holyhell.entity.custom.AngelEntity;
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


public class AngelModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "angelmodel"), "main");
	private final ModelPart eye;
	private final ModelPart head;

	public AngelModel(ModelPart root) {
		this.eye = root.getChild("eye");
		this.head =root.getChild("eye");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eye = partdefinition.addOrReplaceChild("eye", CubeListBuilder.create(), PartPose.offset(-0.5F, 7.0F, 0.5F));

		PartDefinition rightwing = eye.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -9.25F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -3.75F, 0.5F));

		PartDefinition leftwing = eye.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -9.0F, 0.0F, 15.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -4.0F, 0.5F));

		PartDefinition eye_ball = eye.addOrReplaceChild("eye_ball", CubeListBuilder.create().texOffs(0, 13).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).mirror().addBox(-3.5F, 1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw_eye = eye_ball.addOrReplaceChild("jaw_eye", CubeListBuilder.create().texOffs(0, 25).addBox(-3.5F, -1.0F, -7.2F, 7.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F))
		.texOffs(21, 18).addBox(-3.5F, 0.0F, -7.2F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.7F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.FLY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((AngelEntity) entity).idleAnimationState, ModAnimationDefinitions.FLY_IDLE, ageInTicks, 1f);
		this.animate(((AngelEntity) entity).attackAnimationState, ModAnimationDefinitions.FLY_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		eye.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return eye;
	}
}