package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.*;
import com.dead_comedian.holyhell.entity.non_living.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.neoforged.bus.api.IEventBus;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class HolyHellEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Holyhell.MOD_ID);


    //    MOBS
    public static final Supplier<EntityType<AngelEntity>> ANGEL =
            ENTITY_TYPES.register("angel", () -> EntityType.Builder.of(AngelEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.5f).build("angel"));

    public static final Supplier<EntityType<KamikazeEntity>> KAMIKAZE =
            ENTITY_TYPES.register("kamikaze", () -> EntityType.Builder.of(KamikazeEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("kamikaze"));
    public static final Supplier<EntityType<HereticEntity>> HERETIC =
            ENTITY_TYPES.register("heretic", () -> EntityType.Builder.of(HereticEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.2f).build("heretic"));
    public static final Supplier<EntityType<BabOneEntity>> BAB_ONE =
            ENTITY_TYPES.register("bab_one", () -> EntityType.Builder.of(BabOneEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f).build("bab_one"));
    public static final Supplier<EntityType<BabTwoEntity>> BAB_TWO =
            ENTITY_TYPES.register("bab_two", () -> EntityType.Builder.of(BabTwoEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("bab_two"));
    public static final Supplier<EntityType<BabThreeEntity>> BAB_THREE =
            ENTITY_TYPES.register("bab_three", () -> EntityType.Builder.of(BabThreeEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("bab_three"));
    public static final Supplier<EntityType<HolySpiritEntity>> HOLY_SPIRIT =
            ENTITY_TYPES.register("holy_spirit", () -> EntityType.Builder.of(HolySpiritEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("holy_spirit"));
    public static final Supplier<EntityType<CherubEntity>> CHERUB =
            ENTITY_TYPES.register("cherub", () -> EntityType.Builder.of(CherubEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.1f).build("cherub"));
    public static final Supplier<EntityType<HolyCowEntity>> HOLY_COW =
            ENTITY_TYPES.register("holy_cow", () -> EntityType.Builder.of(HolyCowEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.1f).build("holy_cow"));


    public static final Supplier<EntityType<AllSeerEntity>> ALL_SEER =
            ENTITY_TYPES.register("all_seer", () -> EntityType.Builder.of(AllSeerEntity::new, MobCategory.CREATURE)
                    .sized(60f, 30f).build("all_seer"));



    // NON LIVING

    public static final Supplier<EntityType<GlobularDomeEntity>> GLOBULAR_DOME =
            ENTITY_TYPES.register("globular_dome", () -> EntityType.Builder.of(GlobularDomeEntity::new, MobCategory.MISC)
                    .sized(3f, 3f).build("globular_dome"));
    public static final Supplier<EntityType<BlindingBombEntity>> BLINDING_BOMB =
            ENTITY_TYPES.register("blinding_bomb", () -> EntityType.Builder.<BlindingBombEntity>of(BlindingBombEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.7f).build("blinding_bomb"));

    public static final Supplier<EntityType<FireBallEntity>> FIREBALL =
            ENTITY_TYPES.register("fireball", () -> EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.3f).build("fireball"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}