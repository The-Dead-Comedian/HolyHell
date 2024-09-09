package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.KamikazeAngelEntity;
import com.dead_comedian.holyhell.event.ServerTickHandler;
import com.dead_comedian.holyhell.registries.*;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Holyhell implements ModInitializer {
	public static final String MOD_ID = "holyhell";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		HolyHellItemGroup.registerItemGroups();
		HolyHellItems.registerModItems();
		HolyHellBlocks.registerModBlocks();
		HolyHellEntities.registerModEntities();
		HolyHellEffects.registerEffects();
		HolyHellSounds.registerSounds();
		FabricDefaultAttributeRegistry.register(HolyHellEntities.ANGEL, AngelEntity.createAngelAttributes());
		FabricDefaultAttributeRegistry.register(HolyHellEntities.HAILING_HERETIC, HailingHereticEntity.createHereticAttributes());
		FabricDefaultAttributeRegistry.register(HolyHellEntities.KAMIKAZE_ANGEL, KamikazeAngelEntity.createAngelAttributes());


		ServerTickEvents.END_WORLD_TICK.register(new ServerTickHandler());

		LOGGER.info("what is lovee? baby don't hurt mee, don't hurt mee, noo moree");
	}
}