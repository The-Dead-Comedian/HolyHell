package com.dead_comedian.holyhell.entity.custom;

import com.dead_comedian.holyhell.registries.HolyHellEntities;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class LightBeamEntity extends Entity {


    ///////////////
    // VARIABLES //
    ///////////////


    private static final EntityDataAccessor<Integer> LEVEL = SynchedEntityData.defineId(LightBeamEntity.class, EntityDataSerializers.INT);

    int capacity;

    int current = 0;
    int[] mobSpawnIndex = new int[]{2, 3, 5};

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;


    /////////
    // NBT //
    /////////

    @Override
    protected void defineSynchedData() {
        this.entityData.define(LEVEL, 0);

    }

    public int getLevel() {
        return this.entityData.get(LEVEL);
    }

    public void setLevel(int i) {
        this.entityData.set(LEVEL, i);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.setLevel(nbt.getInt("Level"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("Level", this.getLevel());
    }


    //////////
    // MISC //
    //////////


    public LightBeamEntity(EntityType<?> type, Level world) {
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

            this.idleAnimationState.start(this.tickCount);


        } else {
            --this.idleAnimationTimeout;
        }


    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        capacity = 0;
        AABB userHitbox = new AABB(player.blockPosition()).inflate(50);

        List<Player> list = this.level().getEntitiesOfClass(Player.class, userHitbox);

        if (this.getLevel() == 0) {
            for (Player i : list) {

                capacity = capacity + 20;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = (int)((current + mobSpawnIndex[a]));
                if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity*1.3 > current;

            } while (b);


            if (current > 20) {
                current = 0;
                this.kill();
            }

        } else if (this.getLevel() == 1) {
            for (Player i : list) {

                capacity = capacity + 30;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.level());
                    this.level().addFreshEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), kamikazeAngelEntity.getYRot(), kamikazeAngelEntity.getXRot());

                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity > current;
            } while (b);
            if (current > 30) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 2) {
            for (Player i : list) {

                capacity = capacity + 35;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.level());
                    this.level().addFreshEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), kamikazeAngelEntity.getYRot(), kamikazeAngelEntity.getXRot());

                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity > current;

            } while (b);
            if (current > 35) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 3) {
            for (Player i : list) {

                capacity = capacity + 30;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.level());
                    this.level().addFreshEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), kamikazeAngelEntity.getYRot(), kamikazeAngelEntity.getXRot());

                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity > current;

            } while (b);
            BlockPos blockPos = this.blockPosition();
            if (!this.level().isClientSide()) {

                PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.level());
                this.level().addFreshEntity(palladinEntity);
                palladinEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), palladinEntity.getYRot(), palladinEntity.getXRot());
            }

            if (current > 30) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 4) {
            for (Player i : list) {

                capacity = capacity + 35;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.level());
                    this.level().addFreshEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), kamikazeAngelEntity.getYRot(), kamikazeAngelEntity.getXRot());

                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity > current;
            } while (b);

            int c = random.nextInt(2);
            BlockPos blockPos = this.blockPosition();
            if (!this.level().isClientSide()) {

                for (int i = 0; i <= c; i++) {


                    PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.level());
                    this.level().addFreshEntity(palladinEntity);
                    palladinEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), palladinEntity.getYRot(), palladinEntity.getXRot());
                }
            }
            if (current > 35) {
                current = 0;
                this.kill();
            }


        } else if (this.getLevel() == 5) {
            for (Player i : list) {

                capacity = capacity + 40;
            }

            boolean b = current > capacity;

            do {
                int a = random.nextInt(3);
                current = current + mobSpawnIndex[a];
                if (a == 0) {
                    BlockPos blockPos = this.blockPosition();
                    AngelEntity angelEntity = new AngelEntity(HolyHellEntities.ANGEL, this.level());
                    this.level().addFreshEntity(angelEntity);
                    angelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), angelEntity.getYRot(), angelEntity.getXRot());
                } else if (a == 1) {
                    BlockPos blockPos = this.blockPosition();
                    KamikazeAngelEntity kamikazeAngelEntity = new KamikazeAngelEntity(HolyHellEntities.KAMIKAZE_ANGEL, this.level());
                    this.level().addFreshEntity(kamikazeAngelEntity);
                    kamikazeAngelEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), kamikazeAngelEntity.getYRot(), kamikazeAngelEntity.getXRot());

                } else if (a == 2) {
                    BlockPos blockPos = this.blockPosition();
                    HailingHereticEntity hailingHereticEntity = new HailingHereticEntity(HolyHellEntities.HAILING_HERETIC, this.level());
                    this.level().addFreshEntity(hailingHereticEntity);
                    hailingHereticEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), hailingHereticEntity.getYRot(), hailingHereticEntity.getXRot());
                }

                b = capacity > current;

            } while (b);

            int c = random.nextInt(2);
            BlockPos blockPos = this.blockPosition();
            if (!this.level().isClientSide()) {
                for (int i = 0; i <= c; i++) {

                    PalladinEntity palladinEntity = new PalladinEntity(HolyHellEntities.PALLADIN, this.level());
                    this.level().addFreshEntity(palladinEntity);
                    palladinEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), palladinEntity.getYRot(), palladinEntity.getXRot());
                }
                BabOneEntity babEntity = new BabOneEntity(HolyHellEntities.BAB_ONE, this.level());
                this.level().addFreshEntity(babEntity);
                babEntity.moveTo(blockPos.relative(Direction.Axis.Z, -0).relative(Direction.Axis.Y, 2), babEntity.getYRot(), babEntity.getXRot());
            }

            if (current > capacity) {
                current = 0;
                this.kill();
            }
        }


    }
}
