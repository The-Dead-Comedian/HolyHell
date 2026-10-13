package com.dead_comedian.holyhell.server.item.custom;

import com.dead_comedian.holyhell.server.entity.non_living.ChakramEntity;
import com.dead_comedian.holyhell.server.registries.HolyHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class ChakramItem extends Item implements ProjectileItem {
    public ChakramItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new ChakramEntity(HolyHellEntities.CHAKRAM.get(), level);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double) 8.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) -2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2);
    }

    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }


    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);
        if ((!(f > 0.0F) || player.isInWaterOrRain())) {
            Holder<SoundEvent> holder = (Holder) EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
            if (!level.isClientSide) {
                if (f == 0.0F) {
                    ChakramEntity throwntrident = new ChakramEntity(level, player, stack);
                    throwntrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (player.hasInfiniteMaterials()) {
                        throwntrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(throwntrident);
                    level.playSound((Player) null, throwntrident, (SoundEvent) holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.hasInfiniteMaterials()) {
                        stack.consume(1, player);
                        player.getCooldowns().addCooldown(stack.getItem(),10);
                    }
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (f > 0.0F) {
                float f7 = player.getYRot();
                float f1 = player.getXRot();
                float f2 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f1 * ((float) Math.PI / 180F));
                float f3 = -Mth.sin(f1 * ((float) Math.PI / 180F));
                float f4 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f1 * ((float) Math.PI / 180F));
                float f5 = Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
                f2 *= f / f5;
                f3 *= f / f5;
                f4 *= f / f5;
                player.push((double) f2, (double) f3, (double) f4);
                player.startAutoSpinAttack(20, 8.0F, stack);
                if (player.onGround()) {
                    float f6 = 1.1999999F;
                    player.move(MoverType.SELF, new Vec3((double) 0.0F, (double) 1.1999999F, (double) 0.0F));
                }

                level.playSound((Player) null, player, (SoundEvent) holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }

        }

        return InteractionResultHolder.consume(stack);
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    public int getEnchantmentValue() {
        return 1;
    }

//    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
//        ThrownTrident throwntrident = new ThrownTrident(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
//        throwntrident.pickup = AbstractArrow.Pickup.ALLOWED;
//        return throwntrident;
//    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility);
    }
}
