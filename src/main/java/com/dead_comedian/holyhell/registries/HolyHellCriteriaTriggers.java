package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.HolyHell;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class HolyHellCriteriaTriggers extends CriteriaTriggers {
    public static PlayerTrigger KILLED_BY_CROSS;
    public static PlayerTrigger BAB_MERGE;
    public static PlayerTrigger KEBAB;


    public static void init() {
        KILLED_BY_CROSS = HolyHellCriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(HolyHell.MOD_ID, "killed_by_cross")));
        BAB_MERGE = HolyHellCriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(HolyHell.MOD_ID, "bab_merge")));
        KEBAB = HolyHellCriteriaTriggers.register(new PlayerTrigger(new ResourceLocation(HolyHell.MOD_ID, "kebab")));

    }
}