package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class HolyHellSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Holyhell.MOD_ID);


    public static final DeferredHolder<SoundEvent,SoundEvent> HOLY_SHIELD_BLOCK = registerSoundEvents("holy_shield_block");

    public static final DeferredHolder<SoundEvent,SoundEvent> RINGS_HOLD = registerSoundEvents("rings_hold");
    public static final DeferredHolder<SoundEvent,SoundEvent> RINGS_INTRO = registerSoundEvents("rings_intro");
    public static final DeferredHolder<SoundEvent,SoundEvent> RINGS_OUTRO = registerSoundEvents("rings_outro");

    public static final DeferredHolder<SoundEvent,SoundEvent> CANDELABRA_PLACE = registerSoundEvents("candelabra_place");
    public static final DeferredHolder<SoundEvent,SoundEvent> CANDELABRA_LIGHT = registerSoundEvents("candelabra_light");
    public static final DeferredHolder<SoundEvent,SoundEvent> DIVINING_TABLE_INTERACT = registerSoundEvents("divining_table_interact");


    public static final DeferredHolder<SoundEvent,SoundEvent> SACRIFICE = registerSoundEvents("sacrifice");
    public static final DeferredHolder<SoundEvent,SoundEvent> SWORD_SLASH = registerSoundEvents("sword_slash");
    public static final DeferredHolder<SoundEvent,SoundEvent> STONE_CRACK = registerSoundEvents("stone_crack");



    public static final DeferredHolder<SoundEvent,SoundEvent> ANGEL_FLUTTER = registerSoundEvents("angel_flutter");
    public static final DeferredHolder<SoundEvent,SoundEvent> ANGEL_SHOOT = registerSoundEvents("angel_shoot");
    public static final DeferredHolder<SoundEvent,SoundEvent> ANGEL_HURT = registerSoundEvents("angel_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> ANGEL_IDLE = registerSoundEvents("angel_idle");

    public static final DeferredHolder<SoundEvent,SoundEvent> HERETIC_ATTACK = registerSoundEvents("heretic_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> HERETIC_DEATH = registerSoundEvents("heretic_death");
    public static final DeferredHolder<SoundEvent,SoundEvent> HERETIC_HURT = registerSoundEvents("heretic_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> HERETIC_IDLE = registerSoundEvents("heretic_idle");

    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_WALK = registerSoundEvents("bab_walk");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_LEG_WALK = registerSoundEvents("bab_leg_walk");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_DIE = registerSoundEvents("bab_die");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_HURT = registerSoundEvents("bab_hurt");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_IDLE = registerSoundEvents("bab_idle");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_2_ATTACK = registerSoundEvents("bab_2_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_3_ATTACK = registerSoundEvents("bab_3_attack");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_TAME = registerSoundEvents("bab_tame");
    public static final DeferredHolder<SoundEvent,SoundEvent> BAB_MERGE = registerSoundEvents("bab_merge");

    public static final DeferredHolder<SoundEvent,SoundEvent> BELL_RING = registerSoundEvents("bell_ring");
    public static final DeferredHolder<SoundEvent,SoundEvent> CHERUB_FLUTTER = registerSoundEvents("cherub_flutter");

    public static final DeferredHolder<SoundEvent,SoundEvent> HOLY_SPIRIT_DEATH = registerSoundEvents("holy_spirit_death");

    public static final DeferredHolder<SoundEvent,SoundEvent> STUN = registerSoundEvents("stun");
    public static final DeferredHolder<SoundEvent,SoundEvent> DIVINE_PROTECTION = registerSoundEvents("divine_prot");


    public static final SoundType CANDELABRA_SOUNDS = new SoundType(1f, 1f,
             SoundEvents.LANTERN_BREAK,  SoundEvents.LANTERN_STEP, HolyHellSound.CANDELABRA_PLACE.get(),
             SoundEvents.LANTERN_HIT,  SoundEvents.LANTERN_FALL);

    private static DeferredHolder<SoundEvent,SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Holyhell.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}