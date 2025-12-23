package com.dead_comedian.holyhell.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;

import java.util.Optional;

public class PlayerCoffinStatus {

    public boolean active;
    public BlockPos coffinPos;

    public PlayerCoffinStatus() {
        this.active = false;
        this.coffinPos = BlockPos.ZERO;
    }

    public void update(boolean active, BlockPos pos) {
        this.active = active;
        this.coffinPos = pos;
    }


    public static final Codec<PlayerCoffinStatus> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    Codec.BOOL.fieldOf("active").forGetter(s -> s.active),
                    BlockPos.CODEC.optionalFieldOf("pos")
                            .forGetter(s -> Optional.ofNullable(s.coffinPos))
            ).apply(inst, (active, pos) -> {
                PlayerCoffinStatus s = new PlayerCoffinStatus();
                s.active = active;
                s.coffinPos = pos.orElse(BlockPos.ZERO);
                return s;
            }));
}
