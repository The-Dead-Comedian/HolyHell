package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.worldgen.ArenaStructurePlacement;
import com.dead_comedian.holyhell.server.worldgen.OriginStructurePlacement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HolyHellStructurePlacements {

    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPES =
            DeferredRegister.create(BuiltInRegistries.STRUCTURE_PLACEMENT, HolyHell.MOD_ID);


    public static final DeferredHolder<StructurePlacementType<?>, StructurePlacementType<OriginStructurePlacement>> ORIGIN_STRUCTURE_PLACEMENT_TYPE =
            STRUCTURE_PLACEMENT_TYPES.register("origin", () -> () -> OriginStructurePlacement.CODEC);
    public static final DeferredHolder<StructurePlacementType<?>, StructurePlacementType<ArenaStructurePlacement>> ARENA_STRUCTURE_PLACEMENT_TYPE =
            STRUCTURE_PLACEMENT_TYPES.register("arena", () -> () -> ArenaStructurePlacement.CODEC);

    public static void register(IEventBus eventBus) {
        STRUCTURE_PLACEMENT_TYPES.register(eventBus);
    }

}
