package com.dead_comedian.holyhell.item;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item SAINT_EYE = registerItem("saint_eye", new Item(new FabricItemSettings()));
    public static final Item HOLY_TEAR = registerItem("holy_tear", new Item(new FabricItemSettings()));
    private static void addToIngredientGroup(FabricItemGroupEntries entries) {
        entries.add(SAINT_EYE);
        entries.add(HOLY_TEAR);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Holyhell.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Holyhell.LOGGER.info("registering otems for" + Holyhell.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addToIngredientGroup);
    }

}
