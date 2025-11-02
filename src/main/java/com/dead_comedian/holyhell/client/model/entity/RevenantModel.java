package com.dead_comedian.holyhell.client.model.entity;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.dead_comedian.holyhell.client.animation.ModAnimations;
import com.dead_comedian.holyhell.entity.RevenantEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class RevenantModel<T extends RevenantEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart bone;
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart right_arm;
    private final ModelPart bone3;
    private final ModelPart left_arm;
    private final ModelPart head;
    private final ModelPart right_leg;
    private final ModelPart left_leg;

    public RevenantModel(ModelPart root) {
        this.bone = root.getChild("bone");
        this.body = this.bone.getChild("body");
        this.torso = this.body.getChild("torso");
        this.right_arm = this.torso.getChild("right_arm");
        this.bone3 = this.right_arm.getChild("bone3");
        this.left_arm = this.torso.getChild("left_arm");
        this.head = this.torso.getChild("head");
        this.right_leg = this.body.getChild("right_leg");
        this.left_leg = this.body.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -16.7714F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(40, 22).addBox(-5.0F, -6.2857F, -2.5F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-5.0F, -6.2857F, -2.5F, 10.0F, 6.0F, 5.0F, new CubeDeformation(0.2F))
                .texOffs(50, 32).addBox(-7.0F, -16.2857F, -3.5F, 14.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(50, 49).addBox(-7.0F, -16.2857F, -3.5F, 14.0F, 10.0F, 7.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 1.0571F, 0.0F));

        PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 32).addBox(-3.0F, 4.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 51).addBox(-3.0F, 4.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.2F))
                .texOffs(70, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(70, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(-9.0F, -14.2857F, 0.0F));

        PartDefinition bone3 = right_arm.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(24, 51).addBox(0.0F, -26.5F, -12.0F, 0.0F, 53.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -18.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 51).addBox(-1.0F, -50.0F, -8.0F, 0.0F, 53.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 23.5F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 32).mirror().addBox(-2.0F, 4.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 51).mirror().addBox(-2.0F, 4.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.2F)).mirror(false)
                .texOffs(70, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(70, 16).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(9.0F, -14.2857F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -17.0F, 0.0F, 20.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.2857F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 0).addBox(-2.5F, -1.2F, -2.5F, 5.0F, 17.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-2.6F, -1.05F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offset(-2.5F, 0.9714F, 0.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-2.5F, -1.2F, -2.5F, 5.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 63).addBox(-2.4F, -1.05F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offset(2.5F, 0.9714F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(RevenantEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (entity.isArmed()) {
             this.animateWalk(ModAnimations.REVENANT_WALK_ARMED, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.animate(entity.idleAnimationState, ModAnimations.REVENANT_IDLE_ARMED, ageInTicks, 1f);
            this.animate(entity.attackAnimationState, ModAnimations.REVENANT_ATTACK_ARMED, ageInTicks, 1f);

        } else {
            this.animateWalk(ModAnimations.REVENANT_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.animate(entity.idleAnimationState, ModAnimations.REVENANT_IDLE, ageInTicks, 1f);
            this.animate(entity.attackAnimationState, ModAnimations.REVENANT_ATTACK, ageInTicks, 1f);

        }
        this.animate(entity.catatonicAnimationState, ModAnimations.REVENANT_CATATONIC, ageInTicks, 1f);
        this.animate(entity.catatonicRiseAnimationState, ModAnimations.REVENANT_CATATONIC_RISE, ageInTicks, 1f);
        this.animate(entity.catatonicSitAnimationState, ModAnimations.REVENANT_CATATONIC_SIT, ageInTicks, 1f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return bone;
    }
}