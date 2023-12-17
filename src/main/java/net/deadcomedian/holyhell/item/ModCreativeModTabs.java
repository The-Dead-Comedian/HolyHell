package net.deadcomedian.holyhell.item;

import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HolyHell.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HOLY_TAB = CREATIVE_MODE_TAB.register("holy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAINT_EYE.get()))
                    .title(Component.translatable("creativetab:holy_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.HOLY_TEAR.get());
                        pOutput.accept(ModItems.SAINT_EYE.get());
                        pOutput.accept(ModItems.ANGEL_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ALL_SEER_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HAILING_HERETIC_SPAWN_EGG.get());

                        pOutput.accept(ModItems.EVANGELIST_HELMET.get());
                        pOutput.accept(ModItems.EVANGELIST_CHESTPLATE.get());
                        pOutput.accept(ModItems.EVANGELIST_LEGGINGS.get());
                        pOutput.accept(ModItems.EVANGELIST_BOOTS.get());

                        pOutput.accept(ModBlocks.DIVINING_TABLE.get());
                        pOutput.accept(ModBlocks.DREAM_CATCHER.get());

                        pOutput.accept(ModBlocks.LEVANTIA_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_LEVANTIA_LOG.get());
                        pOutput.accept(ModBlocks.CARVED_LEVANTIA_LOG.get());
                        pOutput.accept(ModBlocks.LEVANTIA_PLANK.get());
                        pOutput.accept(ModBlocks.LEVANTIA_LEAVES.get());
                        pOutput.accept(ModBlocks.LEVANTIA_SAPLING.get());

                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
