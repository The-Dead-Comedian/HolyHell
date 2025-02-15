package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.item.custom.SaintEyeItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class HolyHellItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HolyHell.MOD_ID);

    public static final RegistryObject<Item> SAINT_EYE = ITEMS.register("saint_eye",
            () -> new SaintEyeItem(new Item.Properties().food(HolyHellFood.SAINT_EYE)));

    public static final RegistryObject<Item> HOLY_SHIELD = ITEMS.register("holy_shield",
            () -> new ShieldItem(new Item.Properties().durability(1000)));
//    public static final Item HOLY_GRAIL = registerItem("holy_grail", new HolyGrailItem(HolyhellToolMaterial.GRAIL,12, -2.6F, new FabricItemSettings()));
//    public static final Item HOLY_SHIELD = registerItem("holy_shield", new HolyShieldItem(new FabricItemSettings().maxDamage(1000)));
//    public static final Item BLINDING_BOMB = registerItem("blinding_bomb", new BlindingBombItem(new FabricItemSettings()));
//    public static final Item GLOBULAR_DOME = registerItem("globular_dome", new GlobularDomeItem(new FabricItemSettings()));
//
//
//
//    public static final Item RELIGIOUS_RINGS = registerItem("religious_rings", new ReligiousRingsItem(new FabricItemSettings()));
//    public static final Item PALLADIN_SPAWN_EGG = registerItem("palladin_spawn_egg", new SpawnEggItem(HolyHellEntities.PALLADIN, 0xffefbf, 0xd19822, new FabricItemSettings()));
//    public static final Item ANGEL_SPAWN_EGG = registerItem("angel_spawn_egg", new SpawnEggItem(HolyHellEntities.ANGEL, 0xffefbf, 0xd19822, new FabricItemSettings()));
//    public static final Item KAMIKAZE_ANGEL_SPAWN_EGG = registerItem("kamikaze_angel_spawn_egg", new SpawnEggItem(HolyHellEntities.KAMIKAZE_ANGEL, 0xffefbf, 0xd19822, new FabricItemSettings()));
//    public static final Item HERETIC_SPAWN_EGG = registerItem("heretic_spawn_egg", new SpawnEggItem(HolyHellEntities.HAILING_HERETIC, 0xffefbf, 0xf5c842, new FabricItemSettings()));
//    public static final Item BAB_SPAWN_EGG = registerItem("bab_spawn_egg", new SpawnEggItem(HolyHellEntities.BAB_ONE, 0xffefbf, 0xf5c842, new FabricItemSettings()));

    public static final RegistryObject<Item> HOLY_TEAR = ITEMS.register("holy_tear",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BAPTIZED_PLATE = ITEMS.register("baptized_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENHANCED_SILK = ITEMS.register("enhanced_silk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KEBAB = ITEMS.register("kebab",
            () -> new Item(new Item.Properties().food(Foods.BEEF)));


//    public static final Item EVANGELIST_HELMET = registerItem("evangelist_helmet", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET, new FabricItemSettings()));
//    public static final Item EVANGELIST_CHESTPLATE = registerItem("evangelist_chestplate", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
//    public static final Item EVANGELIST_LEGGINGS = registerItem("evangelist_leggings", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
//    public static final Item EVANGELIST_BOOTS = registerItem("evangelist_boots", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS, new FabricItemSettings()));
//



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
