package com.dead_comedian.holyhell.server.registries;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class HolyHellKeyBinds {
    public static final String KEY_CATEGORY_TUTORIAL = "key.categories.misc";
    public static final String VISION_ABILITY = "key.holyhell.vision_ability";
    public static final KeyMapping VISION_ABILITY_KEY = new KeyMapping(VISION_ABILITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT, KEY_CATEGORY_TUTORIAL);

    public static final String RING_ABILITY = "key.holyhell.ring_ability";
    public static final KeyMapping RING_ABILITY_KEY = new KeyMapping(RING_ABILITY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT, KEY_CATEGORY_TUTORIAL);

}
