package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HolyHellSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HolyHell.MOD_ID);


    public static final RegistryObject<SoundEvent> HOLY_SHIELD_BLOCK = registerSoundEvents("holy_shield_block");

    public static final RegistryObject<SoundEvent> RINGS_HOLD = registerSoundEvents("rings_hold");
    public static final RegistryObject<SoundEvent> RINGS_INTRO = registerSoundEvents("rings_intro");
    public static final RegistryObject<SoundEvent> RINGS_OUTRO = registerSoundEvents("rings_outro");

    public static final RegistryObject<SoundEvent> CANDELABRA_PLACE = registerSoundEvents("candelabra_place");
    public static final RegistryObject<SoundEvent> CANDELABRA_LIGHT = registerSoundEvents("candelabra_light");
    public static final RegistryObject<SoundEvent> DIVINING_TABLE_INTERACT = registerSoundEvents("divining_table_interact");


    public static final RegistryObject<SoundEvent> SACRIFICE = registerSoundEvents("sacrifice");
    public static final RegistryObject<SoundEvent> SWORD_SLASH = registerSoundEvents("sword_slash");
    public static final RegistryObject<SoundEvent> STONE_CRACK = registerSoundEvents("stone_crack");



    public static final RegistryObject<SoundEvent> ANGEL_FLUTTER = registerSoundEvents("angel_flutter");
    public static final RegistryObject<SoundEvent> ANGEL_SHOOT = registerSoundEvents("angel_shoot");
    public static final RegistryObject<SoundEvent> ANGEL_HURT = registerSoundEvents("angel_hurt");
    public static final RegistryObject<SoundEvent> ANGEL_IDLE = registerSoundEvents("angel_idle");

    public static final RegistryObject<SoundEvent> HERETIC_ATTACK = registerSoundEvents("heretic_attack");
    public static final RegistryObject<SoundEvent> HERETIC_DEATH = registerSoundEvents("heretic_death");
    public static final RegistryObject<SoundEvent> HERETIC_HURT = registerSoundEvents("heretic_hurt");
    public static final RegistryObject<SoundEvent> HERETIC_IDLE = registerSoundEvents("heretic_idle");

    public static final RegistryObject<SoundEvent> BAB_WALK = registerSoundEvents("bab_walk");
    public static final RegistryObject<SoundEvent> BAB_LEG_WALK = registerSoundEvents("bab_leg_walk");
    public static final RegistryObject<SoundEvent> BAB_DIE = registerSoundEvents("bab_die");
    public static final RegistryObject<SoundEvent> BAB_HURT = registerSoundEvents("bab_hurt");
    public static final RegistryObject<SoundEvent> BAB_IDLE = registerSoundEvents("bab_idle");
    public static final RegistryObject<SoundEvent> BAB_2_ATTACK = registerSoundEvents("bab_2_attack");
    public static final RegistryObject<SoundEvent> BAB_3_ATTACK = registerSoundEvents("bab_3_attack");
    public static final RegistryObject<SoundEvent> BAB_TAME = registerSoundEvents("bab_tame");
    public static final RegistryObject<SoundEvent> BAB_MERGE = registerSoundEvents("bab_merge");

    public static final RegistryObject<SoundEvent> BELL_RING = registerSoundEvents("bell_ring");
    public static final RegistryObject<SoundEvent> CHERUB_FLUTTER = registerSoundEvents("cherub_flutter");

    public static final RegistryObject<SoundEvent> HOLY_SPIRIT_DEATH = registerSoundEvents("holy_spirit_death");

    public static final RegistryObject<SoundEvent> STUN = registerSoundEvents("stun");
    public static final RegistryObject<SoundEvent> DIVINE_PROTECTION = registerSoundEvents("divine_prot");


    public static final ForgeSoundType CANDELABRA_SOUNDS = new ForgeSoundType(1f, 1f,
            () -> SoundEvents.LANTERN_BREAK, () -> SoundEvents.LANTERN_STEP, HolyHellSound.CANDELABRA_PLACE,
            () -> SoundEvents.LANTERN_HIT, () -> SoundEvents.LANTERN_FALL);

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HolyHell.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}