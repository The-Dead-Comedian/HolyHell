package com.dead_comedian.holyhell.entity.custom;

import com.dead_comedian.holyhell.Holyhell;
import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LightBeamEntity extends Entity {


    ///////////////
    // VARIABLES //
    ///////////////
    int capacity ;

    int current = 0;
    int[] mobSpawnIndex = new int[]{ 2, 3, 5};


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    //////////
    // MISC //
    //////////


    public LightBeamEntity(EntityType<?> type, World world) {
        super(type, world);

    }
    @Override
    protected void initDataTracker() {

    }
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
    @Override
    public void tick() {
        super.tick();
        setupAnimationStates();

    }


    ///////////////
    // ANIMATION //
    ///////////////


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 ) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.age);



        } else {
            --this.idleAnimationTimeout;
        }


    }


    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);
        Box userHitbox = new Box(player.getBlockPos()).expand(50);

        List<PlayerEntity> list = this.getWorld().getNonSpectatingEntities(PlayerEntity.class, userHitbox);
        for(PlayerEntity i : list){

            capacity = capacity + 20;
        }

        boolean b =  current > capacity;

        do {
            int a = random.nextInt(3);
            current = current + mobSpawnIndex[a];
            if(a == 0){
                BlockPos blockPos = this.getBlockPos();
                AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                this.getWorld().spawnEntity(angelEntity);
                angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0), angelEntity.getYaw(), angelEntity.getPitch());
            } else if(a == 1){
                BlockPos blockPos = this.getBlockPos();
                KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                this.getWorld().spawnEntity(kamikazeAngelEntity);
                kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

            }else if(a == 2){
                BlockPos blockPos = this.getBlockPos();
                HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                this.getWorld().spawnEntity(hailingHereticEntity);
                hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
            }

            b = capacity > current;
            System.out.println(current);
        }while (b);
        if(current > 20){
            current = 0;
            this.kill();
        }
    }
}
