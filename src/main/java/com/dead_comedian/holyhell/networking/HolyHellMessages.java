package com.dead_comedian.holyhell.networking;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.networking.packet.ServerboundAngelShaderAbilityPacket;
import com.dead_comedian.holyhell.networking.packet.ServerboundExplosionShaderAbilityPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.NetworkRegistry;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Holyhell.MOD_ID)
public class HolyHellMessages {
    private static final String PROTOCOL_VER = "1";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(PROTOCOL_VER);

        registrar.playToServer(
                ServerboundAngelShaderAbilityPacket.TYPE,
                ServerboundAngelShaderAbilityPacket.STREAM_CODEC,
                ServerboundAngelShaderAbilityPacket::handle
        );

        registrar.playToServer(
                ServerboundExplosionShaderAbilityPacket.TYPE,
                ServerboundExplosionShaderAbilityPacket.STREAM_CODEC,
                ServerboundExplosionShaderAbilityPacket::handle
        );

    }


}