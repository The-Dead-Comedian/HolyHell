package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HolyHellItemGroup {
    public static final ItemGroup HOLYHELL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Holyhell.MOD_ID, "holyitems"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.holyhell.items"))
                    .icon(() -> new ItemStack(HolyHellItems.SAINT_EYE)).entries((displayContext, entries) -> {
                        entries.add(HolyHellItems.SAINT_EYE);
                        entries.add(HolyHellItems.HOLY_TEAR);
                        entries.add(HolyHellItems.BLINDING_BOMB);
                        entries.add(HolyHellItems.GLOBULAR_DOME);
                        entries.add(HolyHellItems.BOUNCY_ARROW);
                        entries.add(HolyHellItems.HOLY_ARROW);
                        entries.add(HolyHellItems.HOLY_BOUNCY_ARROW);

                        entries.add(HolyHellItems.RELIGIOUS_RINGS);
                        entries.add(HolyHellItems.HOLY_GRAIL);

                        entries.add(HolyHellItems.ANGEL_SPAWN_EGG);
                        entries.add(HolyHellItems.KAMIKAZE_ANGEL_SPAWN_EGG);
                        entries.add(HolyHellItems.HERETIC_SPAWN_EGG);

                        entries.add(HolyHellItems.EVANGELIST_BOOTS);
                        entries.add(HolyHellItems.EVANGELIST_LEGGINGS);
                        entries.add(HolyHellItems.EVANGELIST_CHESTPLATE);
                        entries.add(HolyHellItems.EVANGELIST_HELMET);

                        entries.add(HolyHellBlocks.DIVINING_TABLE);
                        entries.add(HolyHellBlocks.GLOBE);



                    }).build());

    public static void registerItemGroups() {
        Holyhell.LOGGER.info("Registering Item Groups for " + Holyhell.MOD_ID);
    }

}
