package com.dead_comedian.holyhell.event;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.entity.RevenantEntity;
import com.dead_comedian.holyhell.registries.HolyHellBlocks;
import com.dead_comedian.holyhell.registries.HolyhellTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = HolyHell.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HolyhellForgeEvents {
    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {

        if (event.getLevel().getBlockState(event.getPos()).is(HolyhellTags.Blocks.REVENANT_PROTECTS)) {
            Player player = event.getPlayer();
            List<RevenantEntity> nearbyRevenant = event.getLevel()
                    .getEntitiesOfClass(RevenantEntity.class,
                            new AABB(player.getX() + 20, player.getY() + 4, player.getZ() + 20, player.getX() - 20, player.getY() - 4, player.getZ() - 20));

            for (RevenantEntity entity : nearbyRevenant) {
                if (!player.isCreative() && !player.isSpectator()) {
                    entity.setTarget(player);
                }
            }

        }
    }

    @SubscribeEvent
    public static void changePumpkinFace(PlayerInteractEvent.RightClickBlock event) {
        Block pumpkin = event.getLevel().getBlockState(event.getPos()).getBlock();
        if (event.getItemStack().is(Tags.Items.SHEARS)) {
            event.getLevel().playSound((Player) null, event.getPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (pumpkin == Blocks.CARVED_PUMPKIN) {
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.CARVED_PUMPKIN_EYE.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.CARVED_PUMPKIN_EYE.get()) {
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.CARVED_PUMPKIN_CROSS.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.CARVED_PUMPKIN_CROSS.get()) {
                event.getLevel().setBlock(event.getPos(), Blocks.CARVED_PUMPKIN.defaultBlockState(), 11);
            }

            if (pumpkin == Blocks.JACK_O_LANTERN) {
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.JACK_O_LANTERN_EYE.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.JACK_O_LANTERN_EYE.get()) {
                event.getLevel().setBlock(event.getPos(), HolyHellBlocks.JACK_O_LANTERN_CROSS.get().defaultBlockState(), 11);
            }
            if (pumpkin == HolyHellBlocks.JACK_O_LANTERN_CROSS.get()) {
                event.getLevel().setBlock(event.getPos(), Blocks.JACK_O_LANTERN.defaultBlockState(), 11);
            }
        }
    }
}
