package com.dead_comedian.holyhell.server.entity;

import com.dead_comedian.holyhell.server.entity.ai.AllSeerAi;
import com.dead_comedian.holyhell.server.entity.ai.AngelAi;
import com.dead_comedian.holyhell.server.entity.non_living.AngelProjectileEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import com.dead_comedian.holyhell.server.registries.HolyhellAttributes;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

public class AllSeerEntity extends Mob {


    private static final EntityDataAccessor<Boolean> VIOLENT =
            SynchedEntityData.defineId(AllSeerEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Float> SHIELD =
            SynchedEntityData.defineId(AllSeerEntity.class, EntityDataSerializers.FLOAT);

    private int shieldRechargeTimer=200;

    public void setViolent(boolean attacking) {
        this.entityData.set(VIOLENT, attacking);
    }

    public boolean isViolent() {
        return this.entityData.get(VIOLENT);
    }

    public void setShield(float shield) {
        this.entityData.set(SHIELD, shield);
    }

    public float getShield() {
        return this.entityData.get(SHIELD);
    }

    private ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("boss.holyhell.all_seer"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_6);

    public AllSeerEntity(EntityType<? extends Mob> type, Level world) {
        super(type, world);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence();
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }


    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    @Override
    protected void customServerAiStep() {
        this.level().getProfiler().push("angelBrain");
        ((Brain<AllSeerEntity>) this.brain).tick((ServerLevel) this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("angelActivityUpdate");
        AllSeerAi.updateActivity(this);
        this.level().getProfiler().pop();

        super.customServerAiStep();
    }

    @Override
    public void tick() {
        super.tick();
        if(getShield()<=0){
            if(shieldRechargeTimer>=0){
                shieldRechargeTimer--;
            }else{
                shieldRechargeTimer=200;
                setShield(50);
            }
        }
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return AllSeerAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Override
    protected Brain.Provider<AllSeerEntity> brainProvider() {
        return Brain.provider(AllSeerAi.MEMORY_MODULES, AllSeerAi.SENSORS);
    }

    @Override
    public Brain<AllSeerEntity> getBrain() {
        return (Brain<AllSeerEntity>) super.getBrain();
    }



    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VIOLENT, false);
        builder.define(SHIELD, 50f);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).isEmpty() && !isViolent()) {
            if (!player.level().isClientSide()) {
                player.addItem(new ItemStack(HolyHellItems.HOLY_GRAIL.get(), 1));
                ServerLevel targetLevel = player.level().getServer().getLevel(Level.END);
                if (targetLevel != null) {
                    player.teleportTo(265, 12, 312);
                }

            }
        }
        if (player.getItemInHand(hand).is(Items.BARRIER)) {
            setViolent(true);
        }


        return super.mobInteract(player, hand);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("isViolent", this.isViolent());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setViolent(compound.getBoolean("isViolent"));
    }

    @Override
    public void knockback(double strength, double x, double z) {

    }

    @Override
    protected float getKnockback(Entity attacker, DamageSource damageSource) {
        return 0;
    }

    @Override
    public float getScale() {
        return isViolent() ? 3f : 1.5f;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !isViolent();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof Player player && player.isCreative()){
            return super.hurt(source,amount);
        }

        if (getShield() > 0 && source.getDirectEntity() instanceof AngelProjectileEntity) {
            this.setShield(this.getShield() - amount);
            return false;
        } else if (getShield() > 0 && !(source.getDirectEntity() instanceof AngelProjectileEntity)) {
            return false;
        } else if (getShield() <= 0) {
            return super.hurt(source, amount);
        }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(HolyhellAttributes.MAX_SEER_SHIELD, 50)
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 100);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

    }
}