package com.dead_comedian.holyhell.server.item.custom;

import com.dead_comedian.holyhell.server.entity.BabOneEntity;
import com.dead_comedian.holyhell.server.entity.BabThreeEntity;
import com.dead_comedian.holyhell.server.entity.BabTwoEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class BabItem extends Item {
    public BabItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {


        Level level = player.level();
        Mob babEntity = new BabOneEntity(HolyHellEntities.BAB_ONE.get(), level);


        if (item.get(DataComponents.CUSTOM_DATA) != null) {
            if (item.get(DataComponents.CUSTOM_DATA).copyTag().getInt("level") == 2) {
                babEntity = new BabTwoEntity(HolyHellEntities.BAB_TWO.get(), level);
            } else if (item.get(DataComponents.CUSTOM_DATA).copyTag().getInt("level") == 3) {
                babEntity = new BabThreeEntity(HolyHellEntities.BAB_THREE.get(), level);
            }
        }

        level.addFreshEntity(babEntity);
        babEntity.moveTo(player.blockPosition().above(), babEntity.getYRot(), babEntity.getXRot());
        babEntity.addDeltaMovement(player.getLookAngle().multiply(2, 2, 2));


        if (item.getEntityRepresentation() != null) {
            item.getEntityRepresentation().discard();
        }
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();

            tooltipComponents.add(Component.literal("level: " + tag.getInt("level")));
            tooltipComponents.add(Component.literal("tamed: " + tag.getBoolean("tamed")));

        }
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Mob babEntity = new BabOneEntity(HolyHellEntities.BAB_ONE.get(), level);


        if (stack.get(DataComponents.CUSTOM_DATA) != null) {
            if (stack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("level") == 2) {
                babEntity = new BabTwoEntity(HolyHellEntities.BAB_TWO.get(), level);
            } else if (stack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("level") == 3) {
                babEntity = new BabThreeEntity(HolyHellEntities.BAB_THREE.get(), level);
            }
        }

        level.addFreshEntity(babEntity);
        babEntity.moveTo(player.blockPosition().above(), babEntity.getYRot(), babEntity.getXRot());
        babEntity.addDeltaMovement(player.getLookAngle().multiply(2, 2, 2));
        stack.consume(1, player);

        return InteractionResultHolder.pass(stack);
    }

}
