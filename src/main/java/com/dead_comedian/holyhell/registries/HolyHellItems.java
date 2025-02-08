package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import com.dead_comedian.holyhell.item.HolyhellToolMaterial;
import com.dead_comedian.holyhell.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;


public class HolyHellItems {
    public static final Item SAINT_EYE = registerItem("saint_eye", new SaintEyeItem(new FabricItemSettings().food(HolyHellFoodComponents.SAINT_EYE)));
    public static final Item HOLY_GRAIL = registerItem("holy_grail", new HolyGrailItem(HolyhellToolMaterial.GRAIL,12, -2.6F, new FabricItemSettings()));
    public static final Item HOLY_SHIELD = registerItem("holy_shield", new HolyShieldItem(new FabricItemSettings().durability(1000)));
    public static final Item BLINDING_BOMB = registerItem("blinding_bomb", new BlindingBombItem(new FabricItemSettings()));
    public static final Item GLOBULAR_DOME = registerItem("globular_dome", new GlobularDomeItem(new FabricItemSettings()));



    public static final Item RELIGIOUS_RINGS = registerItem("religious_rings", new ReligiousRingsItem(new FabricItemSettings()));
    public static final Item PALLADIN_SPAWN_EGG = registerItem("palladin_spawn_egg", new SpawnEggItem(HolyHellEntities.PALLADIN, 0xffefbf, 0xd19822, new FabricItemSettings()));
    public static final Item ANGEL_SPAWN_EGG = registerItem("angel_spawn_egg", new SpawnEggItem(HolyHellEntities.ANGEL, 0xffefbf, 0xd19822, new FabricItemSettings()));
    public static final Item KAMIKAZE_ANGEL_SPAWN_EGG = registerItem("kamikaze_angel_spawn_egg", new SpawnEggItem(HolyHellEntities.KAMIKAZE_ANGEL, 0xffefbf, 0xd19822, new FabricItemSettings()));
    public static final Item HERETIC_SPAWN_EGG = registerItem("heretic_spawn_egg", new SpawnEggItem(HolyHellEntities.HAILING_HERETIC, 0xffefbf, 0xf5c842, new FabricItemSettings()));
    public static final Item BAB_SPAWN_EGG = registerItem("bab_spawn_egg", new SpawnEggItem(HolyHellEntities.BAB_ONE, 0xffefbf, 0xf5c842, new FabricItemSettings()));


    public static final Item HOLY_TEAR = registerItem("holy_tear", new Item(new FabricItemSettings()));
    public static final Item BAPTIZED_PLATE = registerItem("baptized_plate", new Item(new FabricItemSettings()));
    public static final Item ENHANCED_SILK = registerItem("enhanced_silk", new Item(new FabricItemSettings()));
    public static final Item KEBAB = registerItem("kebab", new Item(new FabricItemSettings().food(Foods.BEEF)));


    public static final Item EVANGELIST_HELMET = registerItem("evangelist_helmet", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item EVANGELIST_CHESTPLATE = registerItem("evangelist_chestplate", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item EVANGELIST_LEGGINGS = registerItem("evangelist_leggings", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item EVANGELIST_BOOTS = registerItem("evangelist_boots", new EvangelistArmorItem(HolyhellArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS, new FabricItemSettings()));




    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Holyhell.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Holyhell.LOGGER.info("registering items for" + Holyhell.MOD_ID);
    }

}
