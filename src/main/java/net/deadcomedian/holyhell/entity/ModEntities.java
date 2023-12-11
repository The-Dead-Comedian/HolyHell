package net.deadcomedian.holyhell.entity;

import net.deadcomedian.holyhell.HolyHell;
import net.deadcomedian.holyhell.entity.custom.AllSeerEntity;
import net.deadcomedian.holyhell.entity.custom.AngelEntity;
import net.deadcomedian.holyhell.entity.custom.HailingHereticEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HolyHell.MOD_ID);

    public static final RegistryObject<EntityType<AngelEntity>> ANGEL =
            ENTITY_TYPES.register("angel", () -> EntityType.Builder.of(AngelEntity::new, MobCategory.CREATURE)
                    .sized( 1f,1.5f).build("angel"));

    public static final RegistryObject<EntityType<AllSeerEntity>> ALL_SEER =
            ENTITY_TYPES.register("all_seer", () -> EntityType.Builder.of(AllSeerEntity::new, MobCategory.CREATURE)
                    .sized(3, 3).build("all_seer"));

    public static final RegistryObject<EntityType<HailingHereticEntity>> HAILING_HERETIC =
            ENTITY_TYPES.register("hailing_heretic", () -> EntityType.Builder.of(HailingHereticEntity::new, MobCategory.CREATURE)
                    .sized(1, 1).build("hailing_heretic"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
