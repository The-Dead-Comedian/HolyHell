package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.entity.custom.BabOneEntity;
import com.dead_comedian.holyhell.entity.custom.other.GlobularDomeEntity;
import com.dead_comedian.holyhell.entity.custom.other.SwordCrossEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlobularDomeItem extends Item {
    public GlobularDomeItem(Properties settings) {
        super(settings);
    }

    String owner;


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {


        BlockPos blockPos = user.blockPosition();
        GlobularDomeEntity globularDomeEntity = new GlobularDomeEntity(HolyHellEntities.GLOBULAR_DOME, user.level());
        user.level().addFreshEntity(globularDomeEntity);
        globularDomeEntity.moveTo(blockPos, globularDomeEntity.getYRot(), globularDomeEntity.getXRot());
        globularDomeEntity.setUser(owner);
        user.getCooldowns().addCooldown(this, 200);

        if (!user.isCreative()) {
            user.getItemInHand(hand).shrink(1);
        }

        return InteractionResultHolder.consume(user.getItemInHand(hand));
    }
}
