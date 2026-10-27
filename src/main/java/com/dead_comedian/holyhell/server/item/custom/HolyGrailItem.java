package com.dead_comedian.holyhell.server.item.custom;

import com.dead_comedian.holyhell.server.registries.HolyhellDataComps;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class HolyGrailItem extends SwordItem {
    public HolyGrailItem(Tier tier, Properties properties) {
        super(tier, properties);
        this.asItem().getDefaultInstance().set(HolyhellDataComps.GRAIL_LEVEL, 0);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if (player.getItemInHand(usedHand).get(HolyhellDataComps.GRAIL_LEVEL) != null &&
                player.getItemInHand(usedHand).get(HolyhellDataComps.GRAIL_LEVEL) >= 3) {


        }


        return super.use(level, player, usedHand);
    }
}
