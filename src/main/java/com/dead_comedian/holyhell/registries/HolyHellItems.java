package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.item.HolyHellToolMaterial;
import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.item.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class HolyHellItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Holyhell.MOD_ID);

    public static final Supplier<Item> SAINT_EYE = ITEMS.register("saint_eye", () ->
            new Item(new Item.Properties().food(HolyHellFoods.SAINT_EYE)));
    public static final Supplier<SacrificialKatarItem> SACRIFICIAL_KATAR = ITEMS.register("sacrificial_katar", (properties) -> new
            SacrificialKatarItem(HolyHellToolMaterial.GRAIL, new Item.Properties().attributes(SacrificialKatarItem.createAttributes(HolyHellToolMaterial.GRAIL, 6, -2F))));
    public static final Supplier<ShieldItem> HOLY_SHIELD = ITEMS.register("holy_shield", () -> new
            ShieldItem(new Item.Properties().durability(1000)));
    public static final Supplier<ReligiousRingsItem> RELIGIOUS_RINGS = ITEMS.register("religious_rings", () -> new
            ReligiousRingsItem(new Item.Properties()));
    public static final Supplier<GlobularDomeItem> GLOBULAR_DOME = ITEMS.register("globular_dome", () -> new
            GlobularDomeItem(new Item.Properties()));

    public static final Supplier<BlindingBombItem> BLINDING_BOMB = ITEMS.register("blinding_bomb", () -> new
            BlindingBombItem(new Item.Properties()));


    public static final Supplier<Item> ANGEL_SPAWN_EGG = ITEMS.register("angel_spawn_egg", () -> new
            DeferredSpawnEggItem(HolyHellEntities.ANGEL, 0xffefbf, 0xd19822, new Item.Properties()));
    public static final Supplier<Item> KAMIKAZE_SPAWN_EGG = ITEMS.register("kamikaze_spawn_egg", () -> new
            DeferredSpawnEggItem(HolyHellEntities.KAMIKAZE, 0xffefbf, 0xd19822, new Item.Properties()));
    public static final Supplier<Item> HERETIC_SPAWN_EGG = ITEMS.register("heretic_spawn_egg", () -> new
            DeferredSpawnEggItem(HolyHellEntities.HERETIC, 0xffefbf, 0xd19822, new Item.Properties()));
    public static final Supplier<Item> BAB_SPAWN_EGG = ITEMS.register("bab_spawn_egg", () -> new
            DeferredSpawnEggItem(HolyHellEntities.BAB_ONE, 0xffefbf, 0xd19822, new Item.Properties()));


    //     public static final RegistryObject<Item> DEVOUT_SPAWN_EGG = ITEMS.register("devout_spawn_egg",
//            () ->new ForgeSpawnEggItem(HolyHellEntities.DEVOUT, 0xa6242b, 0xdad5ab,new Item.Properties()));
//    public static final RegistryObject<Item> PALLADIN_SPAWN_EGG = ITEMS.register("palladin_spawn_egg",
//            () ->new ForgeSpawnEggItem(HolyHellEntities.PALLADIN, 0xffefbf, 0xd19822,new Item.Properties()));
//    public static final RegistryObject<HolyGrailItem> HOLY_GRAIL = ITEMS.register("holy_grail",
//            () -> new HolyGrailItem(HolyHellToolMaterial.GRAIL,10, -2.6F, new Item.Properties()));


    public static final Supplier<Item> HOLY_TEAR = ITEMS.register("holy_tear", () -> new
            Item(new Item.Properties()));
    public static final Supplier<Item> BAPTIZED_PLATE = ITEMS.register("baptized_plate", () -> new
            Item(new Item.Properties()));
    public static final Supplier<Item> ENHANCED_SILK = ITEMS.register("enhanced_silk", () -> new
            Item(new Item.Properties()));
    public static final Supplier<Item> KEBAB = ITEMS.register("kebab", () -> new
            Item(new Item.Properties().food(Foods.BEEF)));


    public static final Supplier<EvangelistArmorItem> EVANGELIST_HELMET = ITEMS.register("evangelist_helmet", () -> new
            EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET, (new Item.Properties()).durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final Supplier<EvangelistArmorItem> EVANGELIST_CHESTPLATE = ITEMS.register( "evangelist_chestplate", () -> new
            EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final Supplier<EvangelistArmorItem> EVANGELIST_LEGGINGS = ITEMS.register( "evangelist_leggings", () ->
            new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS, (new Item.Properties()).durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final Supplier<EvangelistArmorItem> EVANGELIST_BOOTS = ITEMS.register( "evangelist_boots", () -> new
            EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS, (new Item.Properties()).durability(ArmorItem.Type.HELMET.getDurability(5))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
