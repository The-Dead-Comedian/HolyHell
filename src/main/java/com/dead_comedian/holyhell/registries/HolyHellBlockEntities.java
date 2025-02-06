package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HolyHellBlockEntities {
    public static final BlockEntityType<FallingCrossBlockEntity> FALLING_CROSS_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Holyhell.MOD_ID, "falling_cross_block_entity"),
                    FabricBlockEntityTypeBuilder.create(FallingCrossBlockEntity::new,
                            HolyHellBlocks.FALLING_CROSS).build(null));


    public static void registerBlockEntities() {
        Holyhell.LOGGER.info("Registering Block Entities for " + Holyhell.MOD_ID);

      }
}