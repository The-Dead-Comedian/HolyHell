package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HolyHell.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HOLYHELL_TAB = CREATIVE_MODE_TABS.register("holyhell_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HolyHellItems.SAINT_EYE.get()))
                    .title(Component.translatable("creativetab.holyhell_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(HolyHellItems.SAINT_EYE.get());
                        pOutput.accept(HolyHellItems.HOLY_TEAR.get());
                        pOutput.accept(HolyHellItems.BAPTIZED_PLATE.get());
                        pOutput.accept(HolyHellItems.ENHANCED_SILK.get());
                        pOutput.accept(HolyHellItems.KEBAB.get());

                        pOutput.accept(HolyHellItems.HOLY_GRAIL.get());
                        pOutput.accept(HolyHellItems.HOLY_SHIELD.get());
                        pOutput.accept(HolyHellItems.RELIGIOUS_RINGS.get());
                        pOutput.accept(HolyHellItems.GLOBULAR_DOME.get());
                        pOutput.accept(HolyHellItems.BLINDING_BOMB.get());

                        pOutput.accept(HolyHellBlocks.DIVINING_TABLE.get());
                        pOutput.accept(HolyHellBlocks.GLOBE.get());
                        pOutput.accept(HolyHellBlocks.CANDELABRA.get());
                        pOutput.accept(HolyHellBlocks.CANDLE_HOLDER.get());
                        pOutput.accept(HolyHellBlocks.STONE_CROSS.get());
                        pOutput.accept(HolyHellBlocks.FALLING_CROSS.get());

                        pOutput.accept(HolyHellItems.EVANGELIST_HELMET.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_CHESTPLATE.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_LEGGINGS.get());
                        pOutput.accept(HolyHellItems.EVANGELIST_BOOTS.get());

                        pOutput.accept(HolyHellItems.ANGEL_SPAWN_EGG.get());
                        pOutput.accept(HolyHellItems.KAMIKAZE_SPAWN_EGG.get());
                        pOutput.accept(HolyHellItems.HERETIC_SPAWN_EGG.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}