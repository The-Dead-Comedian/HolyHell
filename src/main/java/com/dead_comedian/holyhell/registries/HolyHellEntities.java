package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.custom.*;
import com.dead_comedian.holyhell.entity.custom.other.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HolyHellEntities {




    //MOBS

    public static final EntityType<AngelEntity> ANGEL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "angel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AngelEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.5f)).build());
    public static final EntityType<KamikazeAngelEntity> KAMIKAZE_ANGEL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "kamikaze_angel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, KamikazeAngelEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());
    public static final EntityType<HailingHereticEntity> HAILING_HERETIC = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "hailing_heretic"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HailingHereticEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.2f)).build());
    public static final EntityType<PalladinEntity> PALLADIN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "palladin"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PalladinEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 2.5f)).build());

    public static final EntityType<BabOneEntity> BAB_ONE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "bab_one"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabOneEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.6f)).build());

    public static final EntityType<BabTwoEntity> BAB_TWO = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "bab_two"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabTwoEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());

    public static final EntityType<BabThreeEntity> BAB_THREE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "bab_three"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BabThreeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());


    //Projectiles

    public static final EntityType<FireBallEntity> FIREBALL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "fireball"),
            FabricEntityTypeBuilder.<FireBallEntity>create(SpawnGroup.CREATURE, FireBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.25f)).build());
    public static final EntityType<FallingSwordEntity> FALLING_SWORD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "falling_sword"),
            FabricEntityTypeBuilder.<FallingSwordEntity>create(SpawnGroup.CREATURE, FallingSwordEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());

    public static final EntityType<BlindingBombEntity> BLINDING_BOMB = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "blinding_bomb"),
            FabricEntityTypeBuilder.<BlindingBombEntity>create(SpawnGroup.CREATURE, BlindingBombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 2f)).build());

    //Other
    public static final EntityType<GlobularDomeEntity> GLOBULAR_DOME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "globular_dome"),
            FabricEntityTypeBuilder.<GlobularDomeEntity>create(SpawnGroup.CREATURE, GlobularDomeEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 3f)).build());

    public static final EntityType<TrappedStoneCrossEntity> TRAPPED_STONE_CROSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "trapped_stone_cross"),
            FabricEntityTypeBuilder.<TrappedStoneCrossEntity>create(SpawnGroup.CREATURE, TrappedStoneCrossEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 0.5f)).build());

    public static final EntityType<LightBeamEntity> LIGHT_BEAM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "light_beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LightBeamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 6.4f)).build());



    public static void registerModEntities() {
        Holyhell.LOGGER.info("Registering Entities for " + Holyhell.MOD_ID);
    }
}
