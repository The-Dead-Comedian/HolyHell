package com.dead_comedian.holyhell.server.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class StoredInventory {

    public ItemStack[] items = new ItemStack[36];
    public ItemStack[] armor = new ItemStack[4];
    public ItemStack[] offhand = new ItemStack[1];

    public StoredInventory() {
        Arrays.fill(items, ItemStack.EMPTY);
        Arrays.fill(armor, ItemStack.EMPTY);
        Arrays.fill(offhand, ItemStack.EMPTY);
    }

    public static final Codec<StoredInventory> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    ItemStack.CODEC.listOf().fieldOf("items").forGetter(i -> List.of(i.items)),
                    ItemStack.CODEC.listOf().fieldOf("armor").forGetter(i -> List.of(i.armor)),
                    ItemStack.CODEC.listOf().fieldOf("offhand").forGetter(i -> List.of(i.offhand))
            ).apply(inst, (items, armor, offhand) -> {
                StoredInventory inv = new StoredInventory();

                for (int i = 0; i < Math.min(items.size(), inv.items.length); i++) {
                    inv.items[i] = items.get(i);
                }

                for (int i = 0; i < Math.min(armor.size(), inv.armor.length); i++) {
                    inv.armor[i] = armor.get(i);
                }

                for (int i = 0; i < Math.min(offhand.size(), inv.offhand.length); i++) {
                    inv.offhand[i] = offhand.get(i);
                }

                return inv;
            }));

}
