package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;


public class HolyHellEnchantments {

    public static final ResourceKey<Enchantment> REBOUND = register("rebound");


    private static ResourceKey<Enchantment> register(String key) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(HolyHell.MOD_ID, key));
    }

}
