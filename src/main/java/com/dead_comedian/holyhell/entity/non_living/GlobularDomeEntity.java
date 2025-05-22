package com.dead_comedian.holyhell.entity.non_living;

import com.dead_comedian.holyhell.registries.HolyHellSound;
import com.dead_comedian.holyhell.registries.HolyhellParticles;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class GlobularDomeEntity extends Entity {
    private int ticksLeft;

    public String userNBT;

    public void setUser(String player) {
        userNBT = player;
    }

    public GlobularDomeEntity(EntityType<?> type, Level world) {
        super(type, world);

        this.ticksLeft = 200;


    }


    @Override
    public void tick() {
        super.tick();

        List<Entity> entityBelow = this.level().getEntities(this, this.getBoundingBox().inflate(0.2));
        List<Entity> entityBelow2 = this.level().getEntities(this, this.getBoundingBox().inflate(0.25));
        launchLivingEntities(entityBelow);
        launchLivingEntities1(entityBelow2);
        entityBelow.removeAll(entityBelow);
        entityBelow2.removeAll(entityBelow2);
        BlockState blockState = this.level().getBlockState(this.blockPosition());
        BlockState blockState2 = this.getBlockStateOnLegacy();
        boolean bl = blockState.is(HolyhellTags.Blocks.DOME_CLEARS_OUT) || blockState2.is(HolyhellTags.Blocks.DOME_CLEARS_OUT);


        if (bl) {
            this.destroyBlocks(this.getBoundingBox());
        }


        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    private boolean destroyBlocks(AABB box) {
        int i = Mth.floor(box.minX);
        int j = Mth.floor(box.minY);
        int k = Mth.floor(box.minZ);
        int l = Mth.floor(box.maxX);
        int m = Mth.floor(box.maxY);
        int n = Mth.floor(box.maxZ);
        boolean bl = false;

        for (int o = i; o <= l; ++o) {
            for (int p = j; p <= m; ++p) {
                for (int q = k; q <= n; ++q) {
                    BlockPos blockPos = new BlockPos(o, p, q);
                    BlockState blockState = this.level().getBlockState(blockPos);
                    if (blockState.isAir() || blockState.is(HolyhellTags.Blocks.DOME_CLEARS_OUT)) {

                        this.level().setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                    }
                }
            }
        }

        return bl;
    }


    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {

    }
    private void launchLivingEntities1(List<Entity> entities) {
        double d = (this.getBoundingBox().minX + this.getBoundingBox().maxX) / 2.0;
        double e = (this.getBoundingBox().minZ + this.getBoundingBox().maxZ) / 2.0;

        for (Entity entity : entities) {
            if (!(entity instanceof Player)) {


                this.level().addParticle(HolyhellParticles.LIGHT_RING.get(), this.getRandomX(0.1),  this.getY(0.5), this.getRandomZ(0.1), 0.0, 0.0, 0.0);
                this.level().playSound(this, this.blockPosition(), HolyHellSound.GLOBULAR_DOME.get(), SoundSource.PLAYERS,0.5f,1.4f);

            }}
    }

    private void launchLivingEntities(List<Entity> entities) {
        double d = (this.getBoundingBox().minX + this.getBoundingBox().maxX) / 2.0;
        double e = (this.getBoundingBox().minZ + this.getBoundingBox().maxZ) / 2.0;

        for (Entity entity : entities) {
            if (!(entity instanceof Player)) {


                double f = entity.getX() - d;
                double g = entity.getZ() - e;
                double h = Math.max(f * f + g * g, 0.1);
                entity.push(f / h * 2, 0.4, g / h * 2);

            }
        }


    }
}