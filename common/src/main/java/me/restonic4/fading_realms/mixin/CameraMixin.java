package me.restonic4.fading_realms.mixin;

import me.restonic4.fading_realms.gui.BlackBarsScreen;
import me.restonic4.fading_realms.util.Camera.Cutscene.Cutscene;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import me.restonic4.fading_realms.util.UtilMethods;
import me.restonic4.restapi.RestApi;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;

@Mixin(value = Camera.class, priority = 1001)
public abstract class CameraMixin implements ICameraMixin {
    @Shadow private boolean initialized;
    @Shadow private BlockGetter level;
    @Shadow private Entity entity;
    @Shadow private Vec3 position = Vec3.ZERO;;
    @Final @Shadow private final Vector3f forwards = new Vector3f(0.0f, 0.0f, 1.0f);
    @Shadow private float xRot;
    @Shadow private float yRot;
    @Shadow private boolean detached;
    @Shadow private float eyeHeight;
    @Shadow private float eyeHeightOld;

    @Shadow protected abstract void move(double d, double e, double f);
    @Shadow protected abstract void setRotation(float f, float g);
    @Shadow protected abstract void setPosition(double d, double e, double f);

    private boolean freeze = false;
    private float shakingIntensity = 0;
    private boolean forcedDetached = false;
    private Cutscene cutscenePlaying = null;
    private double forcedFov = -1;
    private BlackBarsScreen currentBlackBars = null;

    /**
     * @author restonic4
     * @reason cutscenes
     */
    @Overwrite
    private double getMaxZoom(double d) {
        for (int i = 0; i < 8; ++i) {
            double e;
            Vec3 vec32;
            BlockHitResult hitResult;
            float f = (i & 1) * 2 - 1;
            float g = (i >> 1 & 1) * 2 - 1;
            float h = (i >> 2 & 1) * 2 - 1;
            Vec3 vec3 = this.position.add(f *= 0.1f, g *= 0.1f, h *= 0.1f);
            if (((HitResult)(hitResult = this.level.clip(new ClipContext(vec3, vec32 = new Vec3(this.position.x - (double)this.forwards.x() * d + (double)f, this.position.y - (double)this.forwards.y() * d + (double)g, this.position.z - (double)this.forwards.z() * d + (double)h), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, this.entity)))).getType() == HitResult.Type.MISS || !((e = hitResult.getLocation().distanceTo(this.position)) < d)) continue;
            d = e;
        }
        return d;
    }

    /**
     * @author restonic4
     * @reason cutscenes
     */
    @Overwrite
    public void setup(BlockGetter blockGetter, Entity entity, boolean bl, boolean bl2, float f) {
        if (entity == null) {
            return;
        }

        if (this.freeze) {
            return;
        }

        if (currentBlackBars != null) {
            if (currentBlackBars.isFinished()) {
                Minecraft.getInstance().setScreen(null);
                currentBlackBars = null;
            }
            else {
                currentBlackBars.update();
            }
        }

        if (cutscenePlaying != null) {
            if (cutscenePlaying.isFinished()) {
                RestApi.Log("Cutscene finished");
                reset();
                cutscenePlaying = null;
            }
            else {
                cutscenePlaying.applyEffects();
                cutscenePlaying.update();

                Vec3 newPos = cutscenePlaying.getCurrentPosition();
                Vec2 newRot = cutscenePlaying.getCurrentRotation();
                double newFov = cutscenePlaying.getCurrentFov();

                if (shakingIntensity > 0) {
                    newPos = newPos.add(new Vec3(UtilMethods.getRandomDoubleNegative() * shakingIntensity, UtilMethods.getRandomDoubleNegative() * shakingIntensity, UtilMethods.getRandomDoubleNegative() * shakingIntensity));
                }

                this.setPosition(newPos.x, newPos.y, newPos.z);
                this.setRotation(newRot.x, newRot.y);
                this.forceFov(newFov);

                return;
            }
        }

        this.initialized = true;
        this.level = blockGetter;
        this.entity = entity;

        if (forcedDetached) {
            this.detached = true;
        }
        else {
            this.detached = bl;
        }

        double baseX = Mth.lerp((double)f, entity.xo, entity.getX());
        double baseY = Mth.lerp((double)f, entity.yo, entity.getY()) + (double)Mth.lerp(f, this.eyeHeightOld, this.eyeHeight);
        double baseZ = Mth.lerp((double)f, entity.zo, entity.getZ());

        if (shakingIntensity > 0) {
            baseX += UtilMethods.getRandomDoubleNegative() * shakingIntensity;
            baseY += UtilMethods.getRandomDoubleNegative() * shakingIntensity;
            baseZ += UtilMethods.getRandomDoubleNegative() * shakingIntensity;
        }

        this.setRotation(entity.getViewYRot(f), entity.getViewXRot(f));
        this.setPosition(baseX, baseY, baseZ);

        if (bl) {//If 3* person
            if (bl2) {//If 3* person but other way
                this.setRotation(this.yRot + 180.0f, -this.xRot);
            }
            this.move(-this.getMaxZoom(4.0), 0.0, 0.0);
        } else if (entity instanceof LivingEntity && ((LivingEntity)entity).isSleeping()) {
            Direction direction = ((LivingEntity)entity).getBedOrientation();
            this.setRotation(direction != null ? direction.toYRot() - 180.0f : 0.0f, 0.0f);
            this.move(0.0, 0.3, 0.0);
        }
    }

    /**
     * @author restonic4
     * @reason cutscenes
     */
    @Overwrite
    public void reset() {
        this.level = null;
        this.entity = null;
        this.initialized = false;
        this.freeze = false;
        this.shakingIntensity = 0;
        this.forcedDetached = false;
        this.cutscenePlaying = null;
        Minecraft.getInstance().options.hideGui = false;
        this.forceFov(-1);
    }

    /*@Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (cutscenePlaying != null) {
            if (!cutscenePlaying.isFinished()) {
                cutscenePlaying.tick();
            }
        }
    }*/

    @Override
    public void setRot(float f, float g) {
        this.setRotation(f, g);
    }

    @Override
    public void setPos(double d, double e, double f) {
        this.setPosition(d, e, f);
    }

    @Override
    public void setDetached(boolean value) {
        this.detached = value;
    }

    @Override
    public void freeze(boolean value) {
        this.freeze = value;
    }

    @Override
    public boolean isFrozen(boolean value) {
        return this.freeze;
    }

    @Override
    public void shake(float value) {
        this.shakingIntensity = value;
    }

    @Override
    public float getShakingIntensity() {
        return this.shakingIntensity;
    }

    @Override
    public void forceDetach(boolean value) {
        this.forcedDetached = value;
        this.detached = (value) ? true : this.detached;
    }

    @Override
    public boolean isForcedDetached() {
        return this.forcedDetached;
    }

    @Override
    public void playCutscene(Cutscene cutscene) {
        this.cutscenePlaying = cutscene;
    }

    @Override
    public Cutscene getCutscenePlaying() {
        return this.cutscenePlaying;
    }

    @Override
    public void forceFov(double fov) {
        this.forcedFov = fov;
    }

    @Override
    public double getForcedFov() {
        return this.forcedFov;
    }

    @Override
    public void setBlackBars(BlackBarsScreen screen) {
        this.currentBlackBars = screen;
    }

    @Override
    public BlackBarsScreen getBlackBars() {
        return this.currentBlackBars;
    }

    @Override
    public void closeBlackBars() {
        if (this.currentBlackBars != null) {
            this.currentBlackBars.setClosing();
        }
    }
}
