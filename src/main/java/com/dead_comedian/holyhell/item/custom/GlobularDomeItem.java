package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
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
        GlobularDomeEntity globularDomeEntity = new GlobularDomeEntity(HolyHellEntities.GLOBULAR_DOME.get(), user.level());
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