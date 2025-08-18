package com.dead_comedian.holyhell.registries;



import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.item.HolyHellToolMaterial;
import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.item.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class HolyHellItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, Holyhell.MOD_ID);

    public static final DeferredHolder<Item,SaintEyeItem> SAINT_EYE = ITEMS.register("saint_eye",
            () -> new SaintEyeItem(new Item.Properties().food(HolyHellFood.SAINT_EYE)));

    public static final DeferredHolder<Item, SacrificialKatarItem> SACRIFICIAL_KATAR = ITEMS.register("sacrificial_katar",
            () -> new SacrificialKatarItem(HolyHellToolMaterial.GRAIL,6, -2F, new Item.Properties()));

    public static final DeferredHolder<Item,ShieldItem> HOLY_SHIELD = ITEMS.register("holy_shield",
            () -> new ShieldItem(new Item.Properties().durability(1000)));

    public static final DeferredHolder<Item,ReligiousRingsItem> RELIGIOUS_RINGS = ITEMS.register("religious_rings",
            () -> new ReligiousRingsItem(new Item.Properties()));

    public static final DeferredHolder<Item,GlobularDomeItem> GLOBULAR_DOME = ITEMS.register("globular_dome",
            () -> new GlobularDomeItem(new Item.Properties()));

    public static final DeferredHolder<Item,BlindingBombItem> BLINDING_BOMB = ITEMS.register("blinding_bomb",
            () -> new BlindingBombItem(new Item.Properties()));

    public static final DeferredHolder<Item,SpawnEggItem> ANGEL_SPAWN_EGG = ITEMS.register("angel_spawn_egg",
            () ->new SpawnEggItem(HolyHellEntities.ANGEL.get(), 0xffefbf, 0xd19822,new Item.Properties()));
    public static final DeferredHolder<Item,SpawnEggItem> KAMIKAZE_SPAWN_EGG = ITEMS.register("kamikaze_spawn_egg",
            () ->new SpawnEggItem(HolyHellEntities.KAMIKAZE.get(), 0xffefbf, 0xc9a932,new Item.Properties()));
    public static final DeferredHolder<Item,SpawnEggItem> HERETIC_SPAWN_EGG = ITEMS.register("heretic_spawn_egg",
            () ->new SpawnEggItem(HolyHellEntities.HERETIC.get(), 0xd5cf9f, 0x92813d,new Item.Properties()));
   public static final DeferredHolder<Item,SpawnEggItem> BAB_SPAWN_EGG = ITEMS.register("bab_spawn_egg",
            () ->new SpawnEggItem(HolyHellEntities.BAB_ONE.get(), 0xe2d8c0, 0xa87220,new Item.Properties()));


   //     public static final RegistryObject<Item> DEVOUT_SPAWN_EGG = ITEMS.register("devout_spawn_egg",
//            () ->new ForgeSpawnEggItem(HolyHellEntities.DEVOUT, 0xa6242b, 0xdad5ab,new Item.Properties()));
//    public static final RegistryObject<Item> PALLADIN_SPAWN_EGG = ITEMS.register("palladin_spawn_egg",
//            () ->new ForgeSpawnEggItem(HolyHellEntities.PALLADIN, 0xffefbf, 0xd19822,new Item.Properties()));
//    public static final RegistryObject<HolyGrailItem> HOLY_GRAIL = ITEMS.register("holy_grail",
//            () -> new HolyGrailItem(HolyHellToolMaterial.GRAIL,10, -2.6F, new Item.Properties()));


    public static final DeferredHolder<Item,Item> HOLY_TEAR = ITEMS.register("holy_tear",
        () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> BAPTIZED_PLATE = ITEMS.register("baptized_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> ENHANCED_SILK = ITEMS.register("enhanced_silk",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> KEBAB = ITEMS.register("kebab",
            () -> new Item(new Item.Properties().food(Foods.BEEF)));

    public static final DeferredHolder<Item,EvangelistArmorItem> EVANGELIST_HELMET = ITEMS.register("evangelist_helmet",
            () -> new EvangelistArmorItem((HolyhellArmorMaterials) HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET,new Item.Properties()));
    public static final DeferredHolder<Item,EvangelistArmorItem> EVANGELIST_CHESTPLATE = ITEMS.register("evangelist_chestplate",
            () -> new EvangelistArmorItem((HolyhellArmorMaterials) HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
    public static final DeferredHolder<Item,EvangelistArmorItem> EVANGELIST_LEGGINGS = ITEMS.register("evangelist_leggings",
            () -> new EvangelistArmorItem((HolyhellArmorMaterials) HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS,new Item.Properties()));
    public static final DeferredHolder<Item,EvangelistArmorItem> EVANGELIST_BOOTS = ITEMS.register("evangelist_boots",
            () -> new EvangelistArmorItem((HolyhellArmorMaterials) HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS,new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
