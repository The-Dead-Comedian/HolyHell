package com.dead_comedian.holyhell.registries;


import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.non_living.GlobularDomeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class HolyHellEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HolyHell.MOD_ID);





//    //MOBS
//
// NON LIVING
    public static final RegistryObject<EntityType<GlobularDomeEntity>> GLOBULAR_DOME =
            ENTITY_TYPES.register("globular_dome", () -> EntityType.Builder.of(GlobularDomeEntity::new, MobCategory.MISC)
                    .sized(3f, 3f).build("globular_dome"));

//    public static final EntityType<AngelEntity> ANGEL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "angel"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, AngelEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 1.5f)).build());
//    public static final EntityType<KamikazeAngelEntity> KAMIKAZE_ANGEL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "kamikaze_angel"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, KamikazeAngelEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
//    public static final EntityType<HailingHereticEntity> HAILING_HERETIC = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "hailing_heretic"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, HailingHereticEntity::new)
//                    .dimensions(EntityDimensions.fixed(1.5f, 1.2f)).build());
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
//    //Projectiles
//
//    public static final EntityType<FireBallEntity> FIREBALL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "fireball"),
//            FabricEntityTypeBuilder.<FireBallEntity>create(MobCategory.CREATURE, FireBallEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.5f, 0.25f)).build());
//    public static final EntityType<FallingSwordEntity> FALLING_SWORD = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "falling_sword"),
//            FabricEntityTypeBuilder.<FallingSwordEntity>create(MobCategory.CREATURE, FallingSwordEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());
//    public static final EntityType<SwordCrossEntity> SWORD_CROSS = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "sword_cross"),
//            FabricEntityTypeBuilder.<SwordCrossEntity>create(MobCategory.CREATURE, SwordCrossEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());
//
//    public static final EntityType<BlindingBombEntity> BLINDING_BOMB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "blinding_bomb"),
//            FabricEntityTypeBuilder.<BlindingBombEntity>create(MobCategory.CREATURE, BlindingBombEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());
//
//    //Other
//    public static final EntityType<GlobularDomeEntity> GLOBULAR_DOME = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "globular_dome"),
//            FabricEntityTypeBuilder.<GlobularDomeEntity>create(MobCategory.CREATURE, GlobularDomeEntity::new)
//                    .dimensions(EntityDimensions.fixed(3f, 3f)).build());
//
//    public static final EntityType<LightBeamEntity> LIGHT_BEAM = Registry.register(BuiltInRegistries.ENTITY_TYPE,
//            new ResourceLocation(Holyhell.MOD_ID, "light_beam"),
//            FabricEntityTypeBuilder.create(MobCategory.CREATURE, LightBeamEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.9f, 6.4f)).build());




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}