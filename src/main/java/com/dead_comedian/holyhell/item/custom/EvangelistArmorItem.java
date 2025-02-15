package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EvangelistArmorItem extends ArmorItem {

    HolyhellArmorMaterials armorMaterial;
    int protection;
    String protectionStr;
    int time = 2;
    int uhm =0;

    public EvangelistArmorItem(HolyhellArmorMaterials material, Type type, Properties settings) {
        super(material, type, settings);
        armorMaterial = material;

    }

    public void setTime(int timer) {
        time = timer;
    }


    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        time=time+1;
        if (time == 6000 * (uhm * 0.5) && time <=15001) {

            uhm = uhm +1;
            protection = protection + 1;
            armorMaterial.setProtection(type, protection);
        }else if(time <= 1){
            armorMaterial.setProtection(type, 0);
            protection = 0;
            uhm=0;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        super.appendHoverText(stack, world, tooltip, context);
        protection = armorMaterial.getDefenseForType(type);
        protectionStr = Integer.toString(protection);
        tooltip.add(Component.nullToEmpty("Protection: " + protectionStr));

    }
}