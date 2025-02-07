package com.dead_comedian.holyhell.entity.custom;

import com.dead_comedian.holyhell.registries.HolyHellEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class LightBeamEntity extends Entity {


    ///////////////
    // VARIABLES //
    ///////////////


    private static final TrackedData<Integer> LEVEL = DataTracker.registerData(LightBeamEntity.class, TrackedDataHandlerRegistry.INTEGER);

    int capacity;

    int current = 0;
    int[] mobSpawnIndex = new int[]{2, 3, 5};

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;


    /////////
    // NBT //
    /////////

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(LEVEL, 0);

    }

    public int getLevel() {
        return this.dataTracker.get(LEVEL);
    }

    public void setLevel(int i) {
        this.dataTracker.set(LEVEL, i);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setLevel(nbt.getInt("Level"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("Level", this.getLevel());
    }


    //////////
    // MISC //
    //////////


    public LightBeamEntity(EntityType<?> type, World world) {
        super(type, world);

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
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(20) + 40;

            this.idleAnimationState.start(this.age);


        } else {
            --this.idleAnimationTimeout;
        }


    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);
        capacity = 0;
        Box userHitbox = new Box(player.getBlockPos()).expand(50);

        List<PlayerEntity> list = this.getWorld().getNonSpectatingEntities(PlayerEntity.class, userHitbox);

        if (this.getLevel() == 0) {
            for (PlayerEntity i : list) {

                capacity = capacity + 20;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = (int)((current + mobSpawnIndex[a]));
                if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity*1.3 > current;

            } while (b);


            if (current > 20) {
                current = 0;
                this.kill();
            }

        } else if (this.getLevel() == 1) {
            for (PlayerEntity i : list) {

                capacity = capacity + 30;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity > current;
            } while (b);
            if (current > 30) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 2) {
            for (PlayerEntity i : list) {

                capacity = capacity + 35;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity > current;

            } while (b);
            if (current > 35) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 3) {
            for (PlayerEntity i : list) {

                capacity = capacity + 30;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity > current;

            } while (b);
            BlockPos blockPos = this.getBlockPos();
            if (!this.getWorld().isClient()) {

                PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.getWorld());
                this.getWorld().spawnEntity(palladinEntity);
                palladinEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), palladinEntity.getYaw(), palladinEntity.getPitch());
            }

            if (current > 30) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 4) {
            for (PlayerEntity i : list) {

                capacity = capacity + 35;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity > current;
            } while (b);

            int c = random.nextInt(2);
            BlockPos blockPos = this.getBlockPos();
            if (!this.getWorld().isClient()) {

                for (int i = 0; i <= c; i++) {


                    PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.getWorld());
                    this.getWorld().spawnEntity(palladinEntity);
                    palladinEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), palladinEntity.getYaw(), palladinEntity.getPitch());
                }
            }
            if (current > 35) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 5) {
            for (PlayerEntity i : list) {

                capacity = capacity + 40;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.getBlockPos();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(angelEntity);
                    angelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), angelEntity.getYaw(), angelEntity.getPitch());
                } else if (a == 1) {
                    BlockPos blockPos = this.getBlockPos();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.getWorld());
                    this.getWorld().spawnEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), kamikazeAngelEntity.getYaw(), kamikazeAngelEntity.getPitch());

                } else if (a == 2) {
                    BlockPos blockPos = this.getBlockPos();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.getWorld());
                    this.getWorld().spawnEntity(hailingHereticEntity);
                    hailingHereticEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), hailingHereticEntity.getYaw(), hailingHereticEntity.getPitch());
                }

                b = capacity > current;

            } while (b);

            int c = random.nextInt(2);
            BlockPos blockPos = this.getBlockPos();
            if (!this.getWorld().isClient()) {
                for (int i = 0; i <= c; i++) {

                    PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.getWorld());
                    this.getWorld().spawnEntity(palladinEntity);
                    palladinEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), palladinEntity.getYaw(), palladinEntity.getPitch());
                }
                BabOneEntity babEntity = new BabOneEntity(HolyHellEntities.BAB_ONE, this.getWorld());
                this.getWorld().spawnEntity(babEntity);
                babEntity.refreshPositionAndAngles(blockPos.offset(Direction.Axis.Z, -0).offset(Direction.Axis.Y, 2), babEntity.getYaw(), babEntity.getPitch());
            }

            if (current > capacity) {
                current = 0;
                this.kill();
            }
        }


    }
}
