package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.block.entity.FallingCrossBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class HolyHellBlockEntities {
    public static final BlockEntityType<FallingCrossBlockEntity> FALLING_CROSS_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(Holyhell.MOD_ID, "falling_cross_block_entity"),
                    FabricBlockEntityTypeBuilder.create(FallingCrossBlockEntity::new,
                            HolyHellBlocks.FALLING_CROSS).build(null));


    public static void registerBlockEntities() {
        Holyhell.LOGGER.info("Registering Block Entities for " + Holyhell.MOD_ID);

      }
}