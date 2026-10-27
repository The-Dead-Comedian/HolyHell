package com.dead_comedian.holyhell.server.registries;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.data.StoredInventory;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class HolyHellAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, HolyHell.MOD_ID);



    public static final Supplier<AttachmentType<Integer>> KNIGHTED = ATTACHMENT_TYPES.register(
            "knighted",
            () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT)
                    .sync(ByteBufCodecs.INT)
                    .build()
    );
    public static final Supplier<AttachmentType<String>> KNIGHTER = ATTACHMENT_TYPES.register(
            "knighter",
            () -> AttachmentType.builder(() -> "")
                    .serialize(Codec.STRING)
                    .sync(ByteBufCodecs.STRING_UTF8)
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> RENDER_RINGS = ATTACHMENT_TYPES.register(
            "render_rings",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );
    public static final Supplier<AttachmentType<Boolean>> RENDER_RINGS_BREAK = ATTACHMENT_TYPES.register(
            "render_rings_break",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );
    public static final Supplier<AttachmentType<Boolean>> TP_TO_ANGEL = ATTACHMENT_TYPES.register(
            "tp_to_angel",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );
    public static final Supplier<AttachmentType<Boolean>> FLASHBANG = ATTACHMENT_TYPES.register(
            "flashbang",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );
    public static final Supplier<AttachmentType<Boolean>> SHOULD_DISPLAY_TEXT = ATTACHMENT_TYPES.register(
            "should_display_text",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> ANGEL_VISION_TRANSITION = ATTACHMENT_TYPES.register(
            "angel_vision_transition",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> VISION_SHADER = ATTACHMENT_TYPES.register(
            "angel_vision_shader",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );


    public static final Supplier<AttachmentType<Boolean>> HAS_COFFIN = ATTACHMENT_TYPES.register(
            "has_coffin",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Boolean>> DIED = ATTACHMENT_TYPES.register(
            "died",
            () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .copyOnDeath()
                    .build()
    );


    public static final Supplier<AttachmentType<StoredInventory>> SAVED_INVENTORY =
            ATTACHMENT_TYPES.register(
                    "saved_inventory",
                    () -> AttachmentType.builder(StoredInventory::new)
                            .serialize(StoredInventory.MAP_CODEC.codec())
                            .build()
            );



    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
