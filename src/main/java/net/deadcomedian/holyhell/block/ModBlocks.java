package net.deadcomedian.holyhell.block;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.block.custom.ModFlammableRotatedPillarBlock;
import net.deadcomedian.holyhell.block.custom.divining_table;
import net.deadcomedian.holyhell.item.ModItems;
import net.deadcomedian.holyhell.worldgen.tree.LevantiaTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HolyHell.MOD_ID);


    public static final RegistryObject<Block> DIVINING_TABLE =registerBlock("divining_table",
            () -> new divining_table(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.BONE_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> LEVANTIA_LOG =registerBlock("levantia_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(3f)));

    public static final RegistryObject<Block> STRIPPED_LEVANTIA_LOG =registerBlock("stripped_levantia_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD).noOcclusion()));


    public static final RegistryObject<Block> CARVED_LEVANTIA_LOG =registerBlock("carved_levantia_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(3f)));


    public static final RegistryObject<Block> LEVANTIA_SAPLING = registerBlock("levantia_sapling",
            () -> new SaplingBlock(new LevantiaTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    public static final RegistryObject<Block> LEVANTIA_PLANK = registerBlock("levantia_plank",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
               public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                return true;
               }


                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }






 );

    public static final RegistryObject<Block> LEVANTIA_LEAVES =registerBlock("levantia_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.FLOWERING_AZALEA_LEAVES).sound(SoundType.CHERRY_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;}

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    private static <T extends Block>RegistryObject<T>registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item>registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
    BLOCKS.register(eventBus);
    }
}
