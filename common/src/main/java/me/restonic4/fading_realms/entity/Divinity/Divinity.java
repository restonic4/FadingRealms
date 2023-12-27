package me.restonic4.fading_realms.entity.Divinity;

import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.sound.SoundsRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Divinity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();

    private static final SoundEvent ambient = SoundsRegistry.DivinityAmbient.get().get();

    int ticks = 0;

    public Divinity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setNoAi(boolean bl) {
        super.setNoAi(bl);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(14, new WaterAvoidingRandomStrollGoal(this, 1.0));
    }

    @Override
    public void tick() {
        ticks++;

        if (level().isClientSide()) {
            this.idleAnimationState.animateWhen(true, this.tickCount);
        }
        else {
            if (ticks >= 6 * 20) {
                ticks = 0;
                this.level().playSound(null, this.getX(), this.getY() + 15, this.getZ() + 48, ambient, this.getSoundSource(), 1.0f, 1.0f);
            }
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
