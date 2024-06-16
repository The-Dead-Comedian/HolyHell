package com.dead_comedian.holyhell.item;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup HOLYHELL = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(Holyhell.MOD_ID, "holyitems"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.holyhell.items"))
                    .icon(()-> new ItemStack(Items.CHAIN))
                    .entries(((displayContext, entries) -> {
                        for (int n = 0; n < ModItems.ItemsArray.length; n++){
                               entries.add(ModItems.ItemsArray[n]);
                        }
                    }))
                    .build()
    );
    public static void registerItemGroups() {
        Holyhell.LOGGER.info("Registering Item Groups for " + Holyhell.MOD_ID);
    }

}
