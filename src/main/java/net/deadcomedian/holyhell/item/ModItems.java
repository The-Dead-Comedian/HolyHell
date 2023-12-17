package net.deadcomedian.holyhell.item;

import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.entity.ModEntities;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HolyHell.MOD_ID);

    public static final RegistryObject<Item> HOLY_TEAR = ITEMS.register("holy_tear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAINT_EYE = ITEMS.register("saint_eye",
            () ->new Item(new Item.Properties()));

    public static final RegistryObject<Item> EVANGELIST_HELMET = ITEMS.register("evangelist_helmet",
            () ->new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET,new Item.Properties()));
    public static final RegistryObject<Item> EVANGELIST_CHESTPLATE = ITEMS.register("evangelist_chestplate",
            () ->new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
    public static final RegistryObject<Item> EVANGELIST_LEGGINGS = ITEMS.register("evangelist_leggings",
            () ->new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS,new Item.Properties()));
    public static final RegistryObject<Item> EVANGELIST_BOOTS = ITEMS.register("evangelist_boots",
            () ->new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS,new Item.Properties()));

    public static final RegistryObject<Item> ANGEL_SPAWN_EGG = ITEMS.register("angel_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.ANGEL, 0xc4c4c4, 0xffff59, new Item.Properties()));
    public static final RegistryObject<Item> ALL_SEER_SPAWN_EGG = ITEMS.register("all_seer_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.ALL_SEER, 0xc4c4c4, 0xffff59, new Item.Properties()));
    public static final RegistryObject<Item> HAILING_HERETIC_SPAWN_EGG = ITEMS.register("hailing_heretic_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.HAILING_HERETIC, 0xc4c4c4, 0xffff59, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
