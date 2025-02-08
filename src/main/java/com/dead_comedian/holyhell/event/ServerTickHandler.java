package com.dead_comedian.holyhell.event;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.dead_comedian.holyhell.block.DiviningTableBlock;
import com.dead_comedian.holyhell.entity.custom.HailingHereticEntity;
import com.dead_comedian.holyhell.entity.custom.LightBeamEntity;
import com.dead_comedian.holyhell.entity.custom.other.FallingSwordEntity;
import com.dead_comedian.holyhell.registries.HolyHellEffects;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;


public class ServerTickHandler implements ServerTickEvents.EndWorldTick {
    int tick = 0;
    Random rand = new Random();
    @Override
    public void onEndTick(ServerLevel world) {



        List<ServerPlayer> list = world.players();
        for (Player i : list) {

                tick++;
                if (tick >= 300) {

                    if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 1) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        i.level().addFreshEntity(swordEntity);
                        swordEntity.setLevel(1);
                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }
                    else if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 2) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        i.level().addFreshEntity(swordEntity);
                        swordEntity.setLevel(2);
                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }
                    else if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 3) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        i.level().addFreshEntity(swordEntity);
                        swordEntity.setLevel(3);
                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }
                    else if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 4) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        i.level().addFreshEntity(swordEntity);
                        swordEntity.setLevel(4);
                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }
                    else if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 5) {
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        swordEntity.setLevel(5);
                        i.level().addFreshEntity(swordEntity);

                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }
                    else if (i.hasEffect(HolyHellEffects.ENLIGHTENED) && Objects.requireNonNull(i.getEffect(HolyHellEffects.ENLIGHTENED)).getAmplifier() == 0){
                        double d = i.getX() + (rand.nextInt(5) - 0.5) * 10.0;
                        double e = i.getY();
                        double f = i.getZ() + (rand.nextInt(5) - 0.5) * 10.0;


                        LightBeamEntity swordEntity = new LightBeamEntity(HolyHellEntities.LIGHT_BEAM, i.level());
                        i.level().addFreshEntity(swordEntity);
                        swordEntity.setLevel(0);
                        swordEntity.moveTo(d, e, f, i.getYRot(), i.getXRot());
                        tick = 0;
                    }

                }
            }


    }
}


