package me.restonic4.fading_realms.entity.DivinityPortal;

import me.restonic4.fading_realms.entity.EntityManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DivinityPortal extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();

    public DivinityPortal(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setNoAi(boolean bl) {
        super.setNoAi(bl);
    }

    @Override
    public void tick() {
        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(true, this.tickCount);
        }

        super.tick();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1000.0).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityManager.DIVINITY.get().create(serverLevel);
    }
}
