package com.dead_comedian.holyhell.networking.packet;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
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
            if (context.player() != null) {
                ServerPlayer player = (ServerPlayer) context.player();
                if (player.hasEffect(HolyHellEffects.ANGELIC_VISION)) {
                    player.setData(HolyHellAttachments.ANGEL_VISION_TRANSITION_SYNCED_DATA, true);
                }
            }
        });
    }
}