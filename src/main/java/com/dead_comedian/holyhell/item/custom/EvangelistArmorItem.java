package com.dead_comedian.holyhell.item.custom;

import com.dead_comedian.holyhell.item.HolyhellArmorMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EvangelistArmorItem extends ArmorItem {

    HolyhellArmorMaterials armorMaterial;
    int protection;
    String protectionStr;
    int time = 2;
    int uhm =0;

    public EvangelistArmorItem(HolyhellArmorMaterials material, Type type, Settings settings) {
        super(material, type, settings);
        armorMaterial = material;

    }

    public void setTime(int timer) {
        time = timer;
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
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
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        protection = armorMaterial.getProtection(type);
        protectionStr = Integer.toString(protection);
        tooltip.add(Text.of("Protection: " + protectionStr));

    }
}
