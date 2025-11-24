package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.block.*;

import com.dead_comedian.holyhell.item.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class HolyHellBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HolyHell.MOD_ID);

    public static final RegistryObject<Block> COFFIN = registerCoffinItem("coffin",
            () -> new CoffinBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));


    public static final RegistryObject<Block> DIVINING_TABLE = registerBlock("divining_table",
            () -> new DiviningTableBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));

    public static final RegistryObject<Block> STONE_CROSS = registerCrossBlock("stone_cross",
            () -> new StoneCrossBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));


    public static final RegistryObject<Block> CANDELABRA = registerBlock("candelabra",
            () -> new CandelabraBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .noCollission()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(HolyHellSound.CANDELABRA_SOUNDS)
                    .lightLevel(CandelabraBlock.LIGHT_EMISSION)
                    .pushReaction(PushReaction.DESTROY)
                    , ParticleTypes.FLAME));

    public static final RegistryObject<Block> CANDLE_HOLDER = registerBlock("candleholder",
            () -> new CandleholderBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .noCollission()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(HolyHellSound.CANDELABRA_SOUNDS)
                    .lightLevel(CandleholderBlock.LIGHT_EMISSION)
                    .pushReaction(PushReaction.DESTROY)
                    , ParticleTypes.FLAME));

    public static final RegistryObject<Block> FALLING_CROSS = registerBlock("falling_cross",
            () -> new FallingCrossBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));


    public static final RegistryObject<Block> BONE_CHANDELIER = registerBlock("bone_chandelier",
            () -> new BoneChandelierBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(1.0F)
                    .sound(SoundType.BONE_BLOCK)
                    .noCollission()
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .lightLevel(BoneChandelierBlock.LIGHT_EMISSION)
                    .pushReaction(PushReaction.DESTROY),ParticleTypes.FLAME));

    public static final RegistryObject<Block> CARVED_PUMPKIN_EYE = carvedPumpkinEye("carved_pumpkin_eye",
            () -> new EquipableCarvedPumpkinBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> CARVED_PUMPKIN_CROSS = carvedPumpkinCross("carved_pumpkin_cross",
            () -> new EquipableCarvedPumpkinBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> JACK_O_LANTERN_EYE = jackOLanternEye("jack_o_lantern_eye",
            () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel((p_50870_) -> {
                        return 15;
                    }).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> JACK_O_LANTERN_CROSS = jackOLanternCross("jack_o_lantern_cross",
            () -> new CarvedPumpkinBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel((p_50870_) -> {
                        return 15;
                    }).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SKULL_PILE = registerBlock("skull_pile",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(1.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<Block> BONE_PILE = registerBlock("bone_pile",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(1.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BONE_BLOCK)));

    private static <T extends Block> RegistryObject<Item> registerCrossBlockItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new StoneCrossItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> RegistryObject<T> registerCoffinItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        CoffinItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerCrossBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerCrossBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> carvedPumpkinEye(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        CarvedPumpkinEyeItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> carvedPumpkinCross(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        CarvedPumpkinCrossItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> jackOLanternEye(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        JackOLanternEyeItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> jackOLanternCross(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        JackOLanternCrossItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> CoffinItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new CoffinItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> RegistryObject<Item> CarvedPumpkinEyeItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new CarvedPumpkinEyeItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> RegistryObject<Item> CarvedPumpkinCrossItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new CarvedPumpkinCrossItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<Item> JackOLanternEyeItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new JackOLanternEyeItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> RegistryObject<Item> JackOLanternCrossItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new JackOLanternCrossItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return HolyHellItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
