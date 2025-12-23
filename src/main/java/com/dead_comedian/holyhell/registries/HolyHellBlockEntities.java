package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;

import com.dead_comedian.holyhell.block.entity.CoffinBlockEntity;
import com.dead_comedian.holyhell.block.entity.DiviningTableBlockEntity;
import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HolyHellBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Holyhell.MOD_ID);

    public static final Supplier<BlockEntityType<FallingCrossBlockEntity>> FALLING_CROSS_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("falling_cross_block_entity", () -> BlockEntityType.Builder.of(
                    FallingCrossBlockEntity::new, HolyHellBlocks.FALLING_CROSS.get()).build(null));

    public static final Supplier<BlockEntityType<DiviningTableBlockEntity>> DIVINING_TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("divining_table_block_entity", () -> BlockEntityType.Builder.of(
                    DiviningTableBlockEntity::new, HolyHellBlocks.DIVINING_TABLE.get()).build(null));
    public static final Supplier<BlockEntityType<CoffinBlockEntity>> COFFIN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("coffin_block_entity", () ->
                    BlockEntityType.Builder.of(CoffinBlockEntity::new,
                            HolyHellBlocks.COFFIN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}