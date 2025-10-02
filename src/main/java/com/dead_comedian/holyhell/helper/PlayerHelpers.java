package com.dead_comedian.holyhell.helper;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PlayerHelpers {



    public static boolean isLookingIntoVoidInEnd(LocalPlayer player) {
        if (player == null) return false;

        // 1. Check End dimension
        if (player.level().dimension() != Level.END) {
            return false;
        }

        // 2. Looking downward: pitch (XRot) > some threshold
        float pitch = player.getXRot();  // positive when looking downward
        // You can pick a threshold; e.g. > 45° downwards
        if (pitch < 0.0f) {
            return false;
        }

        // 3. Ray trace along look vector
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getLookAngle();
        Vec3 target = eyePos.add(lookVec.scale(256));  // cast a long ray

        ClipContext ctx = new ClipContext(eyePos, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);

        BlockHitResult hit = player.level().clip(ctx);

        boolean miss = (hit.getType() == HitResult.Type.MISS);
        boolean targetBelowVoid = (target.y < 0);

        return miss && targetBelowVoid;
    }

}
