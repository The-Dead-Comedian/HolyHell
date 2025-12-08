package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HolyHellCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Holyhell.MOD_ID);

    public static final Supplier<CreativeModeTab> HOLY_TRINKETS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HolyHellItems.SAINT_EYE.get()))
                    .title(Component.translatable("creativetab.holyhell_tab"))
                    .displayItems((pParameters, pOutput) -> {
                            pOutput.accept(HolyHellItems.SAINT_EYE.get());
                        pOutput.accept(HolyHellItems.HOLY_TEAR.get());
                        pOutput.accept(HolyHellItems.BAPTIZED_PLATE.get());
                        pOutput.accept(HolyHellItems.ENHANCED_SILK.get());

                        pOutput.accept(HolyHellItems.SACRIFICIAL_KATAR.get());
                        pOutput.accept(HolyHellItems.HOLY_GRAIL.get());
                        pOutput.accept(HolyHellItems.HOLY_SHIELD.get());
                        pOutput.accept(HolyHellItems.RELIGIOUS_RINGS.get());
                        pOutput.accept(HolyHellItems.GLOBULAR_DOME.get());
                        pOutput.accept(HolyHellItems.BLINDING_BOMB.get());

                        pOutput.accept(HolyHellBlocks.DIVINING_TABLE.get());
                        pOutput.accept(HolyHellBlocks.CANDELABRA.get());
                        pOutput.accept(HolyHellBlocks.TALL_CANDELABRA.get());
                        pOutput.accept(HolyHellBlocks.CANDLEHOLDER.get());
                        pOutput.accept(HolyHellBlocks.TALL_CANDLEHOLDER.get());
                        pOutput.accept(HolyHellBlocks.CHANDELIER.get());
                        pOutput.accept(HolyHellBlocks.STONE_CROSS.get());
                        pOutput.accept(HolyHellBlocks.FALLING_CROSS.get());

                        pOutput.accept(HolyHellItems.EVANGELIST_HELMET.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_CHESTPLATE.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_LEGGINGS.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_BOOTS.get());

                        pOutput.accept(HolyHellItems.ANGEL_SPAWN_EGG.get());
                        pOutput.accept(HolyHellItems.KAMIKAZE_SPAWN_EGG.get());
                        pOutput.accept(HolyHellItems.HERETIC_SPAWN_EGG.get());
                        pOutput.accept(HolyHellItems.BAB_SPAWN_EGG.get());

                        pOutput.accept(HolyHellBlocks.COBBLED_MARBLE.get());
                        pOutput.accept(HolyHellBlocks.COBBLED_MARBLE_WALL.get());
                        pOutput.accept(HolyHellBlocks.COBBLED_MARBLE_SLAB.get());
                        pOutput.accept(HolyHellBlocks.COBBLED_MARBLE_STAIRS.get());
                        pOutput.accept(HolyHellBlocks.MARBLE.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_WALL.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_SLAB.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_STAIRS.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_BRICKS.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_BRICK_WALL.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_BRICK_SLAB.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_BRICK_STAIRS.get());
                        pOutput.accept(HolyHellBlocks.CRACKED_MARBLE_BRICKS.get());
                        pOutput.accept(HolyHellBlocks.CRACKED_MARBLE_BRICK_WALL.get());
                        pOutput.accept(HolyHellBlocks.CRACKED_MARBLE_BRICK_SLAB.get());
                        pOutput.accept(HolyHellBlocks.CRACKED_MARBLE_BRICK_STAIRS.get());
                        pOutput.accept(HolyHellBlocks.MARBLE_COLLUMN.get());

                        pOutput.accept(HolyHellBlocks.ATLAS_STATUE.get());
                        pOutput.accept(HolyHellBlocks.BAPHOMET_STATUE.get());
                        pOutput.accept(HolyHellBlocks.ICARUS_STATUE.get());
                        pOutput.accept(HolyHellBlocks.DOOMSLAYER_STATUE.get());
                        pOutput.accept(HolyHellBlocks.KRATOS_STATUE.get());
                        pOutput.accept(HolyHellBlocks.V1_STATUE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}