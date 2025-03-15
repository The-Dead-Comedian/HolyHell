package com.dead_comedian.holyhell.block.entity;

import com.dead_comedian.holyhell.registries.HolyHellBlockEntities;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class DiviningTableBlockEntity extends BlockEntity {

    public int timer = 0;

    public void setTimer(int timer1) {
      timer = timer1;
    }



    public void tick(Level world, BlockPos pos, BlockState state) {

        if (timer >= 1 && timer <= 1501) {
            timer++;
        }
        if (world instanceof ServerLevel) {
            if ( timer == 0) {
                ((ServerLevel) world).sendParticles(HolyhellParticles.EYE3.get(),pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5,1,0,0,0,0);
            }
            if (timer == 2) {
                ((ServerLevel) world).sendParticles(HolyhellParticles.EYE0.get(),pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5,1,0,0,0,0);
            }
            if (timer == 501) {
                ((ServerLevel) world).sendParticles(HolyhellParticles.EYE1.get(),pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5,1,0,0,0,0);
            }
            if (timer == 1001) {
                ((ServerLevel) world).sendParticles(HolyhellParticles.EYE2.get(),pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5,1,0,0,0,0);
            }
            if (timer >= 1500) {
                ((ServerLevel) world).sendParticles(HolyhellParticles.EYE3.get(),pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5,1,0,0,0,0);
            }
        }
    }

    public int getTimer() {
        return timer;
    }

    public DiviningTableBlockEntity(BlockPos pos, BlockState state) {
        super(HolyHellBlockEntities.DIVINING_TABLE_BLOCK_ENTITY.get(), pos, state);
    }


}
