package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class HolyHellSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Holyhell.MOD_ID);


    public static final Supplier<SoundEvent> HOLY_SHIELD_BLOCK = registerSoundEvents("holy_shield_block");

    public static final Supplier<SoundEvent> RINGS_HOLD = registerSoundEvents("rings_hold");
    public static final Supplier<SoundEvent> RINGS_INTRO = registerSoundEvents("rings_intro");
    public static final Supplier<SoundEvent> RINGS_OUTRO = registerSoundEvents("rings_outro");

    public static final Supplier<SoundEvent> CANDELABRA_PLACE = registerSoundEvents("candelabra_place");
    public static final Supplier<SoundEvent> CANDELABRA_LIGHT = registerSoundEvents("candelabra_light");
    public static final Supplier<SoundEvent> DIVINING_TABLE_INTERACT = registerSoundEvents("divining_table_interact");


    public static final Supplier<SoundEvent> SACRIFICE = registerSoundEvents("sacrifice");
    public static final Supplier<SoundEvent> SWORD_SLASH = registerSoundEvents("sword_slash");
    public static final Supplier<SoundEvent> STONE_CRACK = registerSoundEvents("stone_crack");



    public static final Supplier<SoundEvent> ANGEL_FLUTTER = registerSoundEvents("angel_flutter");
    public static final Supplier<SoundEvent> ANGEL_SHOOT = registerSoundEvents("angel_shoot");
    public static final Supplier<SoundEvent> ANGEL_HURT = registerSoundEvents("angel_hurt");
    public static final Supplier<SoundEvent> ANGEL_IDLE = registerSoundEvents("angel_idle");

    public static final Supplier<SoundEvent> HERETIC_ATTACK = registerSoundEvents("heretic_attack");
    public static final Supplier<SoundEvent> HERETIC_DEATH = registerSoundEvents("heretic_death");
    public static final Supplier<SoundEvent> HERETIC_HURT = registerSoundEvents("heretic_hurt");
    public static final Supplier<SoundEvent> HERETIC_IDLE = registerSoundEvents("heretic_idle");

    public static final Supplier<SoundEvent> BAB_WALK = registerSoundEvents("bab_walk");
    public static final Supplier<SoundEvent> BAB_LEG_WALK = registerSoundEvents("bab_leg_walk");
    public static final Supplier<SoundEvent> BAB_DIE = registerSoundEvents("bab_die");
    public static final Supplier<SoundEvent> BAB_HURT = registerSoundEvents("bab_hurt");
    public static final Supplier<SoundEvent> BAB_IDLE = registerSoundEvents("bab_idle");
    public static final Supplier<SoundEvent> BAB_2_ATTACK = registerSoundEvents("bab_2_attack");
    public static final Supplier<SoundEvent> BAB_3_ATTACK = registerSoundEvents("bab_3_attack");
    public static final Supplier<SoundEvent> BAB_TAME = registerSoundEvents("bab_tame");
    public static final Supplier<SoundEvent> BAB_MERGE = registerSoundEvents("bab_merge");

    public static final Supplier<SoundEvent> BELL_RING = registerSoundEvents("bell_ring");
    public static final Supplier<SoundEvent> CHERUB_FLUTTER = registerSoundEvents("cherub_flutter");

    public static final Supplier<SoundEvent> HOLY_SPIRIT_DEATH = registerSoundEvents("holy_spirit_death");

    public static final Supplier<SoundEvent> STUN = registerSoundEvents("stun");
    public static final Supplier<SoundEvent> DIVINE_PROTECTION = registerSoundEvents("divine_prot");

    public static final Supplier<SoundEvent> FLASHBANG = registerSoundEvents("flashbang");

    public static final Supplier<SoundEvent> REVENANT_WALK = registerSoundEvents("revenant_walk");
    public static final Supplier<SoundEvent> REVENANT_RISE = registerSoundEvents("revenant_rise");
    public static final Supplier<SoundEvent> REVENANT_ATTACK = registerSoundEvents("revenant_attack");
    public static final Supplier<SoundEvent> METAL_HURT = registerSoundEvents("metal_hurt");

    public static final Supplier<SoundEvent> ULULU = registerSoundEvents("ululu");
    public static final Supplier<SoundEvent> MOB_PASSES = registerSoundEvents("mob_passes");
    public static final Supplier<SoundEvent> PERISH = registerSoundEvents("perish");
    public static final Supplier<SoundEvent> DISSAPEAR = registerSoundEvents("dissapear");

    public static final Supplier<SoundEvent> COFFIN_LID = registerSoundEvents("coffin_lid");


    public static final Supplier<SoundEvent> STATIC_AMBIENT = registerSoundEvents("static_ambient");
    public static final Supplier<SoundEvent> BLINK = registerSoundEvents("blink");

    public static final Supplier<SoundEvent> HEAVENS_VOICE = registerSoundEvents("heavens_voice");

    public static final SoundType CANDELABRA_SOUNDS = new SoundType(1f, 1f,
             SoundEvents.LANTERN_BREAK,  SoundEvents.LANTERN_STEP, SoundEvents.LANTERN_PLACE,
             SoundEvents.LANTERN_HIT,  SoundEvents.LANTERN_FALL);


    private static Supplier<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}