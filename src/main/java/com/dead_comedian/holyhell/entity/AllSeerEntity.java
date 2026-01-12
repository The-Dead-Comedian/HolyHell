package com.dead_comedian.holyhell.entity;

import com.dead_comedian.holyhell.registries.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.ArrayList;
import java.util.List;

public class AllSeerEntity extends Mob {


    public AllSeerEntity(EntityType<? extends Mob> type, Level world) {
        super(type, world);
    }


    @Override
    public void tick() {
        List<? extends Player> nearbyPlayers = this.level().getNearbyPlayers(TargetingConditions.forNonCombat().range(50), this, new AABB(50, 50, 50, -50, -50, -50));

        super.tick();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).isEmpty()) {
            if (!player.level().isClientSide()) {
                player.addItem(new ItemStack(HolyHellItems.HOLY_GRAIL.get(), 1));
                ServerLevel targetLevel = player.level().getServer().getLevel(Level.END);
                    if (targetLevel != null) {
                        player.changeDimension(new DimensionTransition(targetLevel, new Vec3(ServerLevel.END_SPAWN_POINT.getX(), ServerLevel.END_SPAWN_POINT.getY(), ServerLevel.END_SPAWN_POINT.getZ()),
                                player.getDeltaMovement(), Direction.WEST.toYRot(), player.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET)))
                        ;

                }

            }
        }


        return super.mobInteract(player, hand);
    }


    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.isCreativePlayer();
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


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.ARMOR, 1.3f)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 10);
    }


}