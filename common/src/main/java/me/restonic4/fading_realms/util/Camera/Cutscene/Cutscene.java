package me.restonic4.fading_realms.util.Camera.Cutscene;

import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Cutscene {
    private final List<EasingTransition> transitions;
    private boolean finished;
    private Vec3 currentPosition;
    private Vec2 currentRotation;
    private double currentFov;
    private boolean isDetached;
    private boolean hideGui;

    private final Minecraft mc = Minecraft.getInstance();
    private final Camera cam = mc.gameRenderer.getMainCamera();

    public Cutscene() {
        this.transitions = new ArrayList<>();
        this.finished = false;
        this.currentPosition = new Vec3(0,0,0);
        this.currentRotation = new Vec2(0, 0);
        this.currentFov = 70;
        this.isDetached = false;
        this.hideGui = false;
    }

    public Cutscene(Cutscene from) {
        this.transitions = new ArrayList<>();
        this.finished = false;
        this.currentPosition = new Vec3(0,0,0);
        this.currentRotation = new Vec2(0, 0);
        this.currentFov = 70;
        this.isDetached = from.isDetached;
        this.hideGui = from.hideGui;

        from.transitions.forEach(transition -> {
            transition.reset();
            this.addTransition(transition);
        });
    }

    public Cutscene addTransition(EasingTransition transition) {
        this.transitions.add(transition);
        return this;
    }

    public List<EasingTransition> getTransitions() {
        return this.transitions;
    }

    public Cutscene setForceDetached(boolean value) {
        this.isDetached = value;
        return this;
    }

    public Cutscene setHideGui(boolean value) {
        this.hideGui = value;
        return this;
    }

    public void update() {
        if (!this.transitions.isEmpty()) {
            EasingTransition firstTransition = this.transitions.get(0);

            firstTransition.update();

            this.currentPosition = firstTransition.getCurrentPosition();
            this.currentRotation = firstTransition.getCurrentRotation();
            this.currentFov = firstTransition.getCurrentFov();

            if (firstTransition.isFinished()) {
                this.transitions.remove(0);
            }
        }
        else {
            this.finished = true;
            ((ICameraMixin)cam).closeBlackBars();
        }
    }

    public void applyEffects() {
        ICameraMixin camera = (ICameraMixin) cam;

        camera.forceDetach(isDetached);
        mc.options.hideGui = this.hideGui;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public Vec3 getCurrentPosition() {
        return this.currentPosition;
    }

    public Vec2 getCurrentRotation() {
        return this.currentRotation;
    }

    public double getCurrentFov() {
        return this.currentFov;
    }
}
