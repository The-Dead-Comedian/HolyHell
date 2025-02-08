package com.dead_comedian.holyhell.entity.custom.other;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FallingSwordEntity extends AbstractArrow {

    private int ticksLeft;
    public FallingSwordEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
        this.ticksLeft =50;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);


    }
    @Override
    public void tick() {
        super.tick();
        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }


}
