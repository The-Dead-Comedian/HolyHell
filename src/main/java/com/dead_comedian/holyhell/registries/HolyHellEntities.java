package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.AngelEntity;
import com.dead_comedian.holyhell.entity.HereticEntity;
import com.dead_comedian.holyhell.entity.KamikazeEntity;
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

// NON LIVING
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
//    public static final EntityType<PalladinEntity> PALLADIN = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "palladin"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, PalladinEntity::new)
//                    .dimensions(EntityDimensions.fixed(1.5f, 2.5f)).build());
//
//    public static final EntityType<BabOneEntity> BAB_ONE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "bab_one"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, BabOneEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 0.6f)).build());
//
//    public static final EntityType<BabTwoEntity> BAB_TWO = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "bab_two"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, BabTwoEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
//
//    public static final EntityType<BabThreeEntity> BAB_THREE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "bab_three"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, BabThreeEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
//
//    public static final EntityType<HolySpiritEntity> HOLY_SPIRIT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "holy_sprit"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, HolySpiritEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
//


//    public static final EntityType<SwordCrossEntity> SWORD_CROSS = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "sword_cross"),
//            FabricEntityTypeBuilder.<SwordCrossEntity>create(MobCategory.CREATURE, SwordCrossEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());
//




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}