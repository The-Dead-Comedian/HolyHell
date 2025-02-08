package com.dead_comedian.holyhell.block;

import com.dead_comedian.holyhell.registries.HolyHellEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

public class DiviningTableBlock extends HorizontalDirectionalBlock {


    public int nuh = 0;
    public final List<BlockPos> POWER_PROVIDER_OFFSETS = BlockPos.betweenClosedStream(-2, 0, -2, 2, 1, 2).filter((pos) -> {
        return Math.abs(pos.getX()) == 2 || Math.abs(pos.getZ()) == 2;
    }).map(BlockPos::immutable).toList();


    public DiviningTableBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public static boolean canAccessPowerProvider(Level world, BlockPos tablePos, BlockPos providerOffset) {
        return world.getBlockState(tablePos.offset(providerOffset)).is(byItem(Raid.getLeaderBannerInstance().getItem())) && world.getBlockState(tablePos.offset(providerOffset.getX() / 2, providerOffset.getY(), providerOffset.getZ() / 2)).is(BlockTags.ENCHANTMENT_POWER_TRANSMITTER);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);

        for (BlockPos blockPos : POWER_PROVIDER_OFFSETS) {

            if (random.nextInt(10) != 0 || !DiviningTableBlock.canAccessPowerProvider(world, pos, blockPos)) continue;
            for (int i = 0; i <= 20; i++) {
                world.addParticle(ParticleTypes.ENCHANT, (double) pos.getX() + 0.5, (double) pos.getY() + 2.0, (double) pos.getZ() + 0.5, (double) ((float) blockPos.getX() + random.nextFloat()) - 0.5, (float) blockPos.getY() - random.nextFloat() - 1.0f, (double) ((float) blockPos.getZ() + random.nextFloat()) - 0.5);
            }


        }
    }


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

            Iterator var5 = POWER_PROVIDER_OFFSETS.iterator();

            while (var5.hasNext()) {
                BlockPos blockPos = (BlockPos) var5.next();
                if (canAccessPowerProvider(world, pos, blockPos)) {
                    nuh++;
                }
            }

        System.out.println(nuh);
        if(nuh < 2){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,0));
        }
        else if(nuh < 4){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,1));
        }
        else if(nuh < 6){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,2));
        }
        else if(nuh < 8){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,3));
        }
        else if(nuh < 10){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,4));
        }
        else if(nuh < 12){
            player.addEffect(new MobEffectInstance(HolyHellEffects.ENLIGHTENED, 2400,5));
        }


        world.playLocalSound(pos, SoundEvents.ENDER_DRAGON_GROWL, SoundSource.BLOCKS, 1, 1, false);
        nuh = 0;
        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
