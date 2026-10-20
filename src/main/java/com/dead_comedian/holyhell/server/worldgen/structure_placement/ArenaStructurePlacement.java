package com.dead_comedian.holyhell.server.worldgen.structure_placement;

import com.dead_comedian.holyhell.server.registries.HolyHellStructurePlacements;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.placement.FixedPlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;


public class ArenaStructurePlacement extends StructurePlacement {

    public static final MapCodec<ArenaStructurePlacement> CODEC = RecordCodecBuilder.mapCodec(instance ->
            placementCodec(instance)
                    .and(BlockPos.CODEC.fieldOf("position").forGetter(p -> p.position))
                    .apply(instance, ArenaStructurePlacement::new));

    private final BlockPos position;

   public ArenaStructurePlacement(Vec3i locateOffset,
                             StructurePlacement.FrequencyReductionMethod reductionMethod,
                             float frequency,
                             int salt,
                             Optional<StructurePlacement.ExclusionZone> exclusionZone,
                             BlockPos position) {
        super(locateOffset, reductionMethod, frequency, salt, exclusionZone);
        this.position = position;
    }

    @Override
    protected boolean isPlacementChunk(ChunkGeneratorStructureState state, int chunkX, int chunkZ) {
        return chunkX == (position.getX() >> 4) && chunkZ == (position.getZ() >> 4);
    }



    @Override
    public StructurePlacementType<?> type() {
        return HolyHellStructurePlacements.ARENA_STRUCTURE_PLACEMENT_TYPE.get();
    }
}
