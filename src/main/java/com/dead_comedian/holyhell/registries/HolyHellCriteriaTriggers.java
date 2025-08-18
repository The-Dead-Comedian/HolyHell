package com.dead_comedian.holyhell.registries;

import com.dead_comedian.holyhell.Holyhell;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class HolyHellCriteriaTriggers extends CriteriaTriggers {
    public static PlayerTrigger KILLED_BY_CROSS;
    public static PlayerTrigger BAB_MERGE;
    public static PlayerTrigger KEBAB;


    public static void init() {

        KILLED_BY_CROSS= (PlayerTrigger) register("killed_by_cross", new PlayerTrigger());
        BAB_MERGE= (PlayerTrigger) register("bab_merge", new PlayerTrigger());
        KEBAB= (PlayerTrigger) register("kebab", new PlayerTrigger());
    }


}