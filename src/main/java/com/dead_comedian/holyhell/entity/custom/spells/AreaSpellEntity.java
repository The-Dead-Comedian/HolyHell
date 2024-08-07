package com.dead_comedian.holyhell.entity.custom.spells;



import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class AreaSpellEntity extends Entity {

    private boolean startedAttack;
    private int ticksLeft;
    private int warmup = 0;

    public AreaSpellEntity(EntityType<? extends AreaSpellEntity> entityType, World world) {
        super(entityType, world);
        this.ticksLeft =22;
    }

    public AreaSpellEntity(World world, double x, double y, double z, float yaw) {
        this((EntityType<? extends AreaSpellEntity>) HolyHellEntities.AREASPELL, world);

        this.setYaw(yaw * 57.295776F);
        this.setPosition(x, y, z);
    }

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(AreaSpellEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public void areaAttack(AreaSpellEntity entity, World world){

        Box userHitbox = new Box(entity.getBlockPos()).expand(15);
        List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, userHitbox);

        for(LivingEntity i : list){

                i.addStatusEffect(new StatusEffectInstance(HolyHellEffects.CONFUSION, 200 ,1));






        }}


    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }


    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick() {
        super.tick();

        areaAttack(this, this.getWorld());

        if (!this.startedAttack) {
            this.getWorld().sendEntityStatus(this, (byte)4);
            this.startedAttack = true;
        }

        if (--this.ticksLeft < 0) {
            this.discard();
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    private void damage(LivingEntity target) {

        if (target.isAlive() && !target.isInvulnerable()) {


            target.damage(this.getDamageSources().generic(), 6.0F);
        }

    }
}







