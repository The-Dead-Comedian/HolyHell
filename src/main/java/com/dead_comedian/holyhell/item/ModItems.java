package com.dead_comedian.holyhell.item;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.ModEntities;
import com.dead_comedian.holyhell.entity.custom.ModFoodComponents;
import com.dead_comedian.holyhell.item.custom.SaintEyeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item SAINT_EYE = registerItem("saint_eye", new SaintEyeItem(new FabricItemSettings().food(ModFoodComponents.SAINT_EYE)));
    public static final Item HOLY_GRAIL= registerItem("holy_grail", new SwordItem(ToolMaterials.NETHERITE, 69, 0.3F, new FabricItemSettings()));
    public static final Item RELIGIOUS_RINGS= registerItem("religious_rings", new Item(new FabricItemSettings()));
    public static final Item ANGEL_SPAWN_EGG = registerItem("angel_spawn_egg", new SpawnEggItem(ModEntities.ANGEL,0xc4c4c4,0xffff59, new FabricItemSettings()));
    public static final Item HOLY_TEAR = registerItem("holy_tear", new Item(new FabricItemSettings()));
    public static final Item EVANGELIST_HELMET= registerItem("evangelist_helmet", new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.HELMET,new FabricItemSettings()));
    public static final Item EVANGELIST_CHESTPLATE= registerItem("evangelist_chestplate", new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.CHESTPLATE,new FabricItemSettings()));
    public static final Item EVANGELIST_LEGGINGS= registerItem("evangelist_leggings", new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.LEGGINGS,new FabricItemSettings()));
    public static final Item EVANGELIST_BOOTS= registerItem("evangelist_boots", new ArmorItem(ModArmorMaterials.EVANGELIST, ArmorItem.Type.BOOTS,new FabricItemSettings()));;;
    private static void addToIngredientGroup(FabricItemGroupEntries entries) {
        entries.add(SAINT_EYE);
        entries.add(HOLY_TEAR);
        entries.add(RELIGIOUS_RINGS);
        entries.add(EVANGELIST_HELMET);
        entries.add(EVANGELIST_CHESTPLATE);
        entries.add(EVANGELIST_LEGGINGS);
        entries.add(EVANGELIST_BOOTS);
        entries.add(ANGEL_SPAWN_EGG);


    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Holyhell.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Holyhell.LOGGER.info("registering items for" + Holyhell.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToIngredientGroup);
    }

}
