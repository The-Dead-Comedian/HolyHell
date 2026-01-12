package com.dead_comedian.holyhell.networking.packet;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record ServerboundExplosionShaderAbilityPacket() implements CustomPacketPayload {

    public static final Type<ServerboundExplosionShaderAbilityPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "explosion_ability"));

    public static final StreamCodec<ByteBuf, ServerboundExplosionShaderAbilityPacket> STREAM_CODEC =
            StreamCodec.unit(new ServerboundExplosionShaderAbilityPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ServerboundExplosionShaderAbilityPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
                ServerPlayer player = (ServerPlayer) context.player();
                if (player.hasEffect(HolyHellEffects.JESISTANCE)) {
                    player.setData(HolyHellAttachments.SHOULD_EXPLODE, true);
                }

        });
    }
}