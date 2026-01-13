package com.dead_comedian.holyhell.networking;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellEffects;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record ServerboundAngelShaderAbilityPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ServerboundAngelShaderAbilityPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, "angel_shader_ability"));

    public static final StreamCodec<ByteBuf, ServerboundAngelShaderAbilityPacket> STREAM_CODEC =
            StreamCodec.unit(new ServerboundAngelShaderAbilityPacket());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ServerboundAngelShaderAbilityPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {

            ServerPlayer player = (ServerPlayer) context.player();
            if (player.hasEffect(HolyHellEffects.ANGELIC_VISION)) {
                context.player().level().playSound(context.player(),context.player().blockPosition(), HolyHellSounds.BLINK.get(), SoundSource.AMBIENT, 0.7F, 1);
                player.setData(HolyHellAttachments.ANGEL_VISION_TRANSITION, true);
            }

        });
    }
}