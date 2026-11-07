package com.dead_comedian.holyhell.client.model.entity.non_living;// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.dead_comedian.holyhell.server.entity.non_living.JudgementSpearEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class JudgementSpearModel<T extends JudgementSpearEntity> extends HierarchicalModel<T> {
	private final ModelPart judgement_spear;

	public JudgementSpearModel(ModelPart root) {
		this.judgement_spear = root.getChild("judgement_spear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition judgement_spear = partdefinition.addOrReplaceChild("judgement_spear", CubeListBuilder.create().texOffs(0, 17).addBox(-14.2241F, -14.3448F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-12.2241F, -13.3448F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 0).addBox(-10.2241F, -12.3448F, -0.5F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 12).addBox(-13.2241F, -12.3448F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.2241F, -11.3448F, -0.5F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 9).addBox(-6.2241F, -10.3448F, -0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 11).addBox(-4.2241F, -9.3448F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 23).addBox(-2.2241F, -8.3448F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 23).addBox(-11.2241F, -8.3448F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 15).addBox(-4.2241F, -6.3448F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 17).addBox(-3.2241F, -5.3448F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(-2.2241F, -4.3448F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-9.2241F, -4.3448F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 18).addBox(-5.2241F, -3.3448F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-1.2241F, -2.3448F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 15).addBox(-0.2241F, -1.3448F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 19).addBox(0.7759F, -0.3448F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 18).addBox(1.7759F, 0.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 21).addBox(2.7759F, 1.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(3.7759F, 2.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 22).addBox(4.7759F, 3.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(5.7759F, 4.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 22).addBox(6.7759F, 5.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 5).addBox(7.7759F, 6.6552F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 6).addBox(8.7759F, 7.6552F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 11).addBox(10.7759F, 8.6552F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(11.7759F, 9.6552F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 8).addBox(13.7759F, 10.6552F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 15).addBox(9.7759F, 11.6552F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2759F, 27.3448F, -0.5F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(JudgementSpearEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

    @Override
    public ModelPart root() {
        return judgement_spear;
    }
}