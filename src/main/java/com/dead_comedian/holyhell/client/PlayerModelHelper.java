package com.dead_comedian.holyhell.client;

import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.player.Player;

public class PlayerModelHelper {
    public static void beforeSetupAnim(Player player, HumanoidModel<?> model) {

        model.head.resetPose();
        model.hat.resetPose();
        model.body.resetPose();
        model.leftArm.resetPose();
        model.rightArm.resetPose();
        model.leftLeg.resetPose();
        model.rightLeg.resetPose();

        if (player.swingTime > 0) {
            model.leftArm.resetPose();
            model.rightArm.resetPose();
        }
    }

    public static void afterSetupAnim(Player player, HumanoidModel<?> model) {


        if (player.getMainHandItem().is(HolyHellItems.BAB.get()) || player.getOffhandItem().is(HolyHellItems.BAB.get())) {
            model.rightArm.yRot = 0;
            model.leftArm.yRot = 0;

            if (player.getDeltaMovement().z() > 0 || player.getDeltaMovement().x > 0) {
                model.rightArm.xRot = (float) Math.toRadians(-80 + Math.sin(player.tickCount * Math.toRadians(24)) * 30F);
                model.leftArm.xRot = (float) Math.toRadians(-80 + Math.sin(player.tickCount * Math.toRadians(24)) * 30F);
            }

            model.rightArm.xRot = (float) Math.toRadians(-80);
            model.leftArm.xRot = (float) Math.toRadians(-80);

            model.rightArm.zRot = 0;
            model.leftArm.zRot = 0;
        }
    }
}

