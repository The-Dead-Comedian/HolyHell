package com.dead_comedian.holyhell.item.custom;


import com.dead_comedian.holyhell.entity.non_living.BlindingBombEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlindingBombItem extends Item {
    public BlindingBombItem(Properties settings) {
        super(settings);
    }
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound((Player)null, user.getX(), user.getY(), user.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            BlindingBombEntity snowballEntity = new BlindingBombEntity(world, user);
            snowballEntity.setItem(itemStack);
            snowballEntity.shootFromRotation(user, user.getXRot(), user.getYRot(), 1.0F, 1.5F, 1.0F);
            world.addFreshEntity(snowballEntity);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        if (!user.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}