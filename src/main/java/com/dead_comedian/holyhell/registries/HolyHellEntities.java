package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.*;
import com.dead_comedian.holyhell.entity.non_living.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class HolyHellEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HolyHell.MOD_ID);





//    MOBS
public static final RegistryObject<EntityType<AngelEntity>> ANGEL =
        ENTITY_TYPES.register("angel", () -> EntityType.Builder.of(AngelEntity::new, MobCategory.CREATURE)
                .sized(0.8f, 1.5f).build("angel"));

    public static final RegistryObject<EntityType<KamikazeEntity>> KAMIKAZE     =
            ENTITY_TYPES.register("kamikaze", () -> EntityType.Builder.of(KamikazeEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.5f).build("kamikaze"));

    public static final RegistryObject<EntityType<HereticEntity>> HERETIC     =
            ENTITY_TYPES.register("heretic", () -> EntityType.Builder.of(HereticEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.2f).build("heretic"));

    public static final RegistryObject<EntityType<PalladinEntity>> PALLADIN     =
            ENTITY_TYPES.register("palladin", () -> EntityType.Builder.of(PalladinEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 2.5f).build("palladin"));

    public static final RegistryObject<EntityType<BabOneEntity>> BAB_ONE     =
            ENTITY_TYPES.register("bab_one", () -> EntityType.Builder.of(BabOneEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f).build("bab_one"));
    public static final RegistryObject<EntityType<BabTwoEntity>> BAB_TWO     =
            ENTITY_TYPES.register("bab_two", () -> EntityType.Builder.of(BabTwoEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("bab_two"));
    public static final RegistryObject<EntityType<BabThreeEntity>> BAB_THREE     =
            ENTITY_TYPES.register("bab_three", () -> EntityType.Builder.of(BabThreeEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("bab_three"));
    public static final RegistryObject<EntityType<HolySpiritEntity>> HOLY_SPIRIT     =
            ENTITY_TYPES.register("holy_spirit", () -> EntityType.Builder.of(HolySpiritEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.8f).build("holy_spirit"));
    public static final RegistryObject<EntityType<CherubEntity>> CHERUB     =
            ENTITY_TYPES.register("cherub", () -> EntityType.Builder.of(CherubEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.1f).build("cherub"));
    public static final RegistryObject<EntityType<HolyCowEntity>> HOLY_COW     =
            ENTITY_TYPES.register("holy_cow", () -> EntityType.Builder.of(HolyCowEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.1f).build("holy_cow"));

    public static final RegistryObject<EntityType<DevoutEntity>> DEVOUT     =
            ENTITY_TYPES.register("devout", () -> EntityType.Builder.of(DevoutEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 3f).build("devout"));


    // NON LIVING
public static final RegistryObject<EntityType<GateEntity>> GATE =
        ENTITY_TYPES.register("gate", () -> EntityType.Builder.of(GateEntity::new, MobCategory.MISC)
                .sized(1f, 1f).build("gate"));

    public static final RegistryObject<EntityType<GlobularDomeEntity>> GLOBULAR_DOME =
            ENTITY_TYPES.register("globular_dome", () -> EntityType.Builder.of(GlobularDomeEntity::new, MobCategory.MISC)
                    .sized(3f, 3f).build("globular_dome"));
    public static final RegistryObject<EntityType<BlindingBombEntity>> BLINDING_BOMB =
            ENTITY_TYPES.register("blinding_bomb", () -> EntityType.Builder.<BlindingBombEntity>of(BlindingBombEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.2f).build("blinding_bomb"));
    public static final RegistryObject<EntityType<FallingSwordEntity>> FALLING_SWORD =
            ENTITY_TYPES.register("falling_sword", () -> EntityType.Builder.<FallingSwordEntity>of(FallingSwordEntity::new, MobCategory.MISC)
                    .sized(0.5f, 2f).build("falling_sword"));
    public static final RegistryObject<EntityType<FireBallEntity>> FIREBALL =
            ENTITY_TYPES.register("fireball", () -> EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.25f).build("fireball"));
    public static final RegistryObject<EntityType<SwordCrossEntity>> SWORD_CROSS =
            ENTITY_TYPES.register("sword_cross", () -> EntityType.Builder.<SwordCrossEntity>of(SwordCrossEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.25f).build("sword_cross"));




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}