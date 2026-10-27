package com.dead_comedian.holyhell.mixin;

import com.dead_comedian.holyhell.HolyHell;
import com.dead_comedian.holyhell.server.registries.HolyHellAttachments;
import com.dead_comedian.holyhell.server.registries.HolyHellEffects;
import com.dead_comedian.holyhell.server.registries.HolyHellItems;
import com.dead_comedian.holyhell.server.registries.HolyHellSounds;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //Holy Shield Sound
    @Inject(method = "handleEntityEvent", at = @At(value = "HEAD"), cancellable = true)
    private void holyhell$HandleEntityEventCustom(byte pId, CallbackInfo ci) {
        if (pId == 29) {
            LivingEntity that = ((LivingEntity) (Object) this);
            if (that.getMainHandItem().is(HolyHellItems.HOLY_SHIELD.get())) {
                that.playSound(HolyHellSounds.HOLY_SHIELD_BLOCK.get(), 1F, 0.8F + that.level().random.nextFloat() * 0.4F);
                ci.cancel();
                return;
            }
            return;
        }
        return;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void holyhell$tick(CallbackInfo ci) {
        LivingEntity that = ((LivingEntity) (Object) this);

        if (that instanceof Monster monster) {

            if (monster.getData(HolyHellAttachments.KNIGHTED) != null) {
                int count = monster.getData(HolyHellAttachments.KNIGHTED);
                if (count > 0) {
                    monster.setData(HolyHellAttachments.KNIGHTED, count - 1);
                } else {
                    monster.setData(HolyHellAttachments.KNIGHTER, "");
                    if (monster.getBrain() != null && monster.getBrain().checkMemory(MemoryModuleType.ATTACK_TARGET, MemoryStatus.REGISTERED)) {
                        monster.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, Optional.empty());
                    }
                    if (monster.getBrain() == null) {
                        monster.setTarget(null);
                    }
                }


                AABB searchBox = monster.getBoundingBox().inflate(10, 4, 10);
                List<Entity> nearbyEntities = monster.level().getEntities(monster, searchBox, entity1 -> entity1 instanceof LivingEntity livingEntity && !livingEntity.getStringUUID().equals(monster.getData(HolyHellAttachments.KNIGHTER)) && !livingEntity.isSpectator());
                nearbyEntities.sort((entityA, entityB) -> Float.compare(monster.distanceTo(entityA), monster.distanceTo(entityB)));

                if (monster.getData(HolyHellAttachments.KNIGHTER) != null && !monster.getData(HolyHellAttachments.KNIGHTER).isEmpty()) {

                    if (monster.getBrain() != null) {
                        if (monster.getBrain().checkMemory(MemoryModuleType.ATTACK_TARGET, MemoryStatus.REGISTERED)) {
                            if (monster.getBrain().checkMemory(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT) &&
                                    monster.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get().getStringUUID().equals(monster.getData(HolyHellAttachments.KNIGHTER))) {
                                monster.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, Optional.empty());
                            }
                            if (monster.getBrain().checkMemory(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT) && !nearbyEntities.isEmpty()) {
                                monster.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, (LivingEntity) nearbyEntities.getFirst());

                            }
                        }
                    } else {
                        if (monster.getTarget() != null && monster.getTarget().getStringUUID().equals(monster.getData(HolyHellAttachments.KNIGHTER))) {
                            monster.setTarget(null);
                        }
                        if (monster.getTarget() == null && !nearbyEntities.isEmpty()) {
                            monster.setTarget((LivingEntity) nearbyEntities.getFirst());
                        }
                    }

                }
            }
        }

    }
}
