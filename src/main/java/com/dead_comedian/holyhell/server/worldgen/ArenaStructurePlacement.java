package com.dead_comedian.holyhell.server.worldgen;

import com.dead_comedian.holyhell.server.registries.HolyHellStructurePlacements;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class ArenaStructurePlacement extends StructurePlacement {

    public static final MapCodec<ArenaStructurePlacement> CODEC = RecordCodecBuilder.mapCodec(builder ->
            StructurePlacement.placementCodec(builder)
                    .apply(builder, ArenaStructurePlacement::new));

    protected ArenaStructurePlacement(Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
    }

    @Override
    protected boolean isPlacementChunk(ChunkGeneratorStructureState structureState, int x, int z) {
        structureState.ensureStructuresGenerated();
        return x == 15 && z == -20;
    }



    @Override
    public StructurePlacementType<?> type() {
        return HolyHellStructurePlacements.ARENA_STRUCTURE_PLACEMENT_TYPE.get();
    }
}
