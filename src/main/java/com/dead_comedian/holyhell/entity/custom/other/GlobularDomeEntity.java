package com.dead_comedian.holyhell.entity.custom.other;

import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.Iterator;
import java.util.List;

public class GlobularDomeEntity extends Entity {
    private int ticksLeft;

    public String userNBT;

    public void setUser(String player){
        userNBT=player;
    }

    public String getUser(){
        return userNBT;
    }


    public GlobularDomeEntity(EntityType<?> type, World world) {
        super(type, world);

        this.ticksLeft =200;


    }





    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(0.2));
        launchLivingEntities(entityBelow);

        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        BlockState blockState2 = this.getLandingBlockState();
        boolean bl = blockState.isIn(HolyhellTags.Blocks.DOME_CLEARS_OUT) || blockState2.isIn(HolyhellTags.Blocks.DOME_CLEARS_OUT) ;


        if(bl){
             this.destroyBlocks(this.getBoundingBox());
        }



        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    private boolean destroyBlocks(Box box) {
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.floor(box.minY);
        int k = MathHelper.floor(box.minZ);
        int l = MathHelper.floor(box.maxX);
        int m = MathHelper.floor(box.maxY);
        int n = MathHelper.floor(box.maxZ);
        boolean bl = false;

        for (int o = i; o <= l; ++o) {
            for (int p = j; p <= m; ++p) {
                for (int q = k; q <= n; ++q) {
                    BlockPos blockPos = new BlockPos(o, p, q);
                    BlockState blockState = this.getWorld().getBlockState(blockPos);
                    if (blockState.isAir() || blockState.isIn(HolyhellTags.Blocks.DOME_CLEARS_OUT)) {

                        this.getWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                }}
            }
        }

        return bl;
    }






    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }



    private void launchLivingEntities(List<Entity> entities) {
        double d = (this.getBoundingBox().minX + this.getBoundingBox().maxX) / 2.0;
        double e = (this.getBoundingBox().minZ + this.getBoundingBox().maxZ) / 2.0;

        for (Entity entity : entities) {
            if (!(entity instanceof PlayerEntity)) {


                double f = entity.getX() - d;
                double g = entity.getZ() - e;
                double h = Math.max(f * f + g * g, 0.1);

                entity.addVelocity(f / h * 2, 0.4, g / h * 2);
            }
        }



    }
}
