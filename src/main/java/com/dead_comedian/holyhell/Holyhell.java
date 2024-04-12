package com.dead_comedian.holyhell;

import com.dead_comedian.holyhell.block.ModBlocks;
import com.dead_comedian.holyhell.item.ModItems;
import net.fabricmc.api.ModInitializer;

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
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		LOGGER.info("what is lovee? baby don't hurt mee, don't hurt mee, noo moree");
	}
}