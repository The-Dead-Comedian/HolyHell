package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.block.ModBlocks;

import com.dead_comedian.holyhell.effect.ModEffects;
import com.dead_comedian.holyhell.entity.ModEntities;
import com.dead_comedian.holyhell.entity.custom.AngelEntity;
import com.dead_comedian.holyhell.entity.custom.LastPrayerEntity;
import com.dead_comedian.holyhell.item.ModItemGroup;
import com.dead_comedian.holyhell.item.ModItems;

import com.dead_comedian.holyhell.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

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
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModEffects.registerEffects();
		ModSounds.registerSounds();
		FabricDefaultAttributeRegistry.register(ModEntities.ANGEL, AngelEntity.createAngelAttributes());


		LOGGER.info("what is lovee? baby don't hurt mee, don't hurt mee, noo moree");
	}
}