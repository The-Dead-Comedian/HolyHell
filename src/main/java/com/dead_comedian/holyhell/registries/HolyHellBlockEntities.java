package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;

import com.dead_comedian.holyhell.block.entity.DiviningTableBlockEntity;
import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import com.dead_comedian.holyhell.block.entity.GlobeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HolyHell.MOD_ID);

    public static final RegistryObject<BlockEntityType<FallingCrossBlockEntity>> FALLING_CROSS_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("falling_cross_block_entity", () ->
                    BlockEntityType.Builder.of(FallingCrossBlockEntity::new,
                            HolyHellBlocks.FALLING_CROSS.get()).build(null));


    public static final RegistryObject<BlockEntityType<GlobeBlockEntity>> GLOBE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("globe_block_entity", () ->
                    BlockEntityType.Builder.of(GlobeBlockEntity::new,
                            HolyHellBlocks.GLOBE.get()).build(null));

    public static final RegistryObject<BlockEntityType<DiviningTableBlockEntity>> DIVINING_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("divining_table_block_entity", () ->
                    BlockEntityType.Builder.of(DiviningTableBlockEntity::new,
                            HolyHellBlocks.DIVINING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}