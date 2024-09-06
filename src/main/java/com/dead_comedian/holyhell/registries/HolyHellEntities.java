package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.entity.custom.spells.AreaSpellEntity;
import com.dead_comedian.holyhell.entity.custom.spells.ChristianCrossEntity;
import com.dead_comedian.holyhell.entity.custom.spells.LastPrayerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HolyHellEntities {

    public static final EntityType<LightBeamEntity> LIGHT_BEAM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "light_beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LightBeamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 6.4f)).build());

    public static final EntityType<AngelEntity> ANGEL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "angel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AngelEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.5f)).build());

    public static final EntityType<KamikazeAngelEntity> KAMIKAZE_ANGEL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "kamikaze_angel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, KamikazeAngelEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.5f)).build());

    public static final EntityType<HailingHereticEntity> HAILING_HERETIC = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "hailing_heretic"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HailingHereticEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.2f)).build());

    public static final EntityType<LastPrayerEntity> LASTPRAYER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "last_prayer"),
            FabricEntityTypeBuilder.<LastPrayerEntity>create(SpawnGroup.CREATURE, LastPrayerEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 4f)).build());
    public static final EntityType<ChristianCrossEntity> CHRISTIANCROSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "christian_cross"),
            FabricEntityTypeBuilder.<ChristianCrossEntity>create(SpawnGroup.CREATURE, ChristianCrossEntity::new)
                    .dimensions(EntityDimensions.fixed(4f, 3f)).build());
    public static final EntityType<AreaSpellEntity> AREASPELL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Holyhell.MOD_ID, "area_spell"),
            FabricEntityTypeBuilder.<AreaSpellEntity>create(SpawnGroup.CREATURE, AreaSpellEntity::new)
                    .dimensions(EntityDimensions.fixed(15.125f, 5f)).build());




    public static void registerModEntities() {
        Holyhell.LOGGER.info("Registering Entities for " + Holyhell.MOD_ID);
    }
}
