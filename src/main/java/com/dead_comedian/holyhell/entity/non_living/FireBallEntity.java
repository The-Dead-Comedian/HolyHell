package com.dead_comedian.holyhell.entity.non_living;

import com.dead_comedian.holyhell.registries.HolyHellItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.AbstractArrow;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class FireBallEntity extends AbstractArrow {

    private static final double ARROW_BASE_DAMAGE = 2.0D;
    public FireBallEntity(EntityType<? extends AbstractArrow> entityType, double d, double e, double f, Level world) {
        super(entityType, world);
        this.setPosRaw(d, e, f);

    }

    public FireBallEntity(EntityType<FireBallEntity> fireBallEntityEntityType, Level world) {
        super(fireBallEntityEntityType, world);
    }


    @Override
    public boolean isNoGravity() {
        return false;
    }



    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
            return SoundEvents.FIRE_EXTINGUISH;
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.discard();
    }

    @Override
    public void setBaseDamage(double pBaseDamage) {
        super.setBaseDamage(6.0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }


    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);



    }


}