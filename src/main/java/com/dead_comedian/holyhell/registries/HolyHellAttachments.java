package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class HolyHellAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Holyhell.MOD_ID);


    public static final Supplier<AttachmentType<Boolean>> CAN_TP_TO_ANGEL = ATTACHMENT_TYPES.register(
            "can_tp_to_angel",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> FLASHBANG = ATTACHMENT_TYPES.register(
            "flashbang",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );
    public static final Supplier<AttachmentType<Float>> DAMAGE_ABSORBED = ATTACHMENT_TYPES.register(
            "damage_absorbed",
            () -> AttachmentType.builder(() -> 0F)
                    .serialize(Codec.FLOAT)
                    .sync(ByteBufCodecs.FLOAT)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> SHOULD_EXPLODE = ATTACHMENT_TYPES.register(
            "should_explode",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> SHOULD_DISPLAY_TEXT = ATTACHMENT_TYPES.register(
            "should_display_text",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> ANGEL_VISION_TRANSITION = ATTACHMENT_TYPES.register(
            "angel_vision_transition",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> VISION_SHADER = ATTACHMENT_TYPES.register(
            "angel_vision_shader",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    // Register the DeferredRegister to your mod bus in your mod constructor
    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
