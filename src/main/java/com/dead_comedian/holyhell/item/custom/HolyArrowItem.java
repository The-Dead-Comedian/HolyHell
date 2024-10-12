package com.dead_comedian.holyhell.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HolyArrowItem extends ArrowItem {
    public HolyArrowItem(Settings settings) {
        super(settings);
    }
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new SpectralArrowEntity(world, shooter);
    }
}
