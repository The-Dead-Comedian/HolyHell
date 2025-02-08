package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class HolyHellItemGroup {
    public static final CreativeModeTab HOLYHELL = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(Holyhell.MOD_ID, "holyitems"),
            FabricItemGroup.builder().title(Component.translatable("itemGroup.holyhell.items"))
                    .icon(() -> new ItemStack(HolyHellItems.SAINT_EYE)).displayItems((displayContext, entries) -> {
                        entries.accept(HolyHellItems.SAINT_EYE);
                        entries.accept(HolyHellItems.BLINDING_BOMB);
                        entries.accept(HolyHellItems.GLOBULAR_DOME);
                        entries.accept(HolyHellItems.RELIGIOUS_RINGS);




                        entries.accept(HolyHellItems.HOLY_GRAIL);
                        entries.accept(HolyHellItems.HOLY_SHIELD);

                        entries.accept(HolyHellItems.HOLY_TEAR);
                        entries.accept(HolyHellItems.BAPTIZED_PLATE);
                        entries.accept(HolyHellItems.ENHANCED_SILK);

                        entries.accept(HolyHellItems.ANGEL_SPAWN_EGG);
                        entries.accept(HolyHellItems.KAMIKAZE_ANGEL_SPAWN_EGG);
                        entries.accept(HolyHellItems.HERETIC_SPAWN_EGG);
                        entries.accept(HolyHellItems.PALLADIN_SPAWN_EGG);
                        entries.accept(HolyHellItems.BAB_SPAWN_EGG);

                        entries.accept(HolyHellItems.EVANGELIST_BOOTS);
                        entries.accept(HolyHellItems.EVANGELIST_LEGGINGS);
                        entries.accept(HolyHellItems.EVANGELIST_CHESTPLATE);
                        entries.accept(HolyHellItems.EVANGELIST_HELMET);

                        entries.accept(HolyHellBlocks.DIVINING_TABLE);
                        entries.accept(HolyHellBlocks.GLOBE);
                        entries.accept(HolyHellBlocks.CANDELABRA);
                        entries.accept(HolyHellBlocks.CANDLEHOLDER);
                        entries.accept(HolyHellBlocks.STONE_CROSS);
                        entries.accept(HolyHellBlocks.FALLING_CROSS);







                    }).build());

    public static void registerItemGroups() {
        Holyhell.LOGGER.info("Registering Item Groups for " + Holyhell.MOD_ID);
    }

}
