package me.restonic4.fading_realms.util.Camera.Cutscene;

import me.restonic4.fading_realms.util.Camera.CutsceneAction;
import me.restonic4.fading_realms.util.Camera.CutsceneActionEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EasingTransition {
    private Vec3 startPosVec;
    private Vec3 endPosVec;
    private Vec2 startRotVec;
    private Vec2 endRotVec;
    private double startFov;
    private double endFov;
    private float durationSeconds;
    private Easing easingFunction;
    private long startTime;
    private float partialTick;
    private Vec3 currentPosition;
    private Vec2 currentRotation;
    private double currentFov;
    private Vec3 bezierPoint1;
    private Vec3 bezierPoint2;

    private List<CutsceneActionEntry> actions;

    private Minecraft mc;
    private LocalPlayer localPlayer;

    public EasingTransition() {

    }

    public EasingTransition(float durationSeconds) {
        this.durationSeconds = durationSeconds;
        this.easingFunction = null;
    }

    public EasingTransition(Vec3 startPosVec, Vec3 endPosVec, Vec2 startRotVec, Vec2 endRotVec, double startFov, double endFov, float durationSeconds, Easing easingFunction) {
        this.startPosVec = startPosVec;
        this.endPosVec = endPosVec;
        this.startRotVec = startRotVec;
        this.endRotVec = endRotVec;
        this.startFov = startFov;
        this.endFov = endFov;
        this.durationSeconds = durationSeconds;
        this.easingFunction = easingFunction;
        this.startTime = -1;
        this.currentPosition = new Vec3(0,0,0);
        this.currentRotation = new Vec2(0, 0);
        this.currentFov = 70;
        this.bezierPoint1 = null;
        this.bezierPoint2 = null;
        this.mc = Minecraft.getInstance();
        this.localPlayer = this.mc.player;
        this.actions = new ArrayList<>();
        reset();
    }

    public EasingTransition(EasingTransition other) {
        this.startPosVec = new Vec3(other.startPosVec.x, other.startPosVec.y, other.startPosVec.z);
        this.endPosVec = new Vec3(other.endPosVec.x, other.endPosVec.y, other.endPosVec.z);
        this.startRotVec = new Vec2(other.startRotVec.x, other.startRotVec.y);
        this.endRotVec = new Vec2(other.endRotVec.x, other.endRotVec.y);
        this.startFov = other.startFov;
        this.endFov = other.endFov;
        this.durationSeconds = other.durationSeconds;
        this.easingFunction = other.easingFunction;
        this.startTime = -1;
        this.currentPosition = new Vec3(0,0,0);
        this.currentRotation = new Vec2(0, 0);
        this.currentFov = 70;
        this.bezierPoint1 = null;
        this.bezierPoint2 = null;
        this.mc = Minecraft.getInstance();
        this.localPlayer = this.mc.player;
        this.actions = other.actions;
        reset();
    }

    public EasingTransition setStartPos(Vec3 vec) {
        this.startPosVec = vec;
        return this;
    }

    public EasingTransition setEndPos(Vec3 vec) {
        this.endPosVec = vec;
        return this;
    }

    public EasingTransition setStartRot(Vec2 vec) {
        this.startRotVec = vec;
        return this;
    }

    public EasingTransition setEndRot(Vec2 vec) {
        this.endRotVec = vec;
        return this;
    }

    public EasingTransition setStartFov(double value) {
        this.startFov = value;
        return this;
    }

    public EasingTransition setEndFov(double value) {
        this.endFov = value;
        return this;
    }

    public EasingTransition setDuration(int value) {
        this.durationSeconds = value;
        return this;
    }

    public EasingTransition setEasing(Easing value) {
        this.easingFunction = value;
        return this;
    }

    public EasingTransition setBezier(Vec3 p1) {
        this.bezierPoint1 = p1;
        return this;
    }

    public EasingTransition setAction(float timeToTrigger, CutsceneAction action) {
        this.actions.add(new CutsceneActionEntry(timeToTrigger, action));
        return this;
    }

    public EasingTransition setBezier(Vec3 p1, Vec3 p2) {
        this.bezierPoint1 = p1;
        this.bezierPoint2 = p2;
        return this;
    }

    public void update() {
        if (startTime == -1) {
            this.startTime = System.currentTimeMillis();
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        partialTick = (float) elapsedTime / 1000.0f;

        if (easingFunction != null && partialTick >= durationSeconds) {
            this.currentPosition = new Vec3(endPosVec.x, endPosVec.y, endPosVec.z);
            this.currentRotation = new Vec2(endRotVec.x, endRotVec.y);
            this.currentFov = endFov;
        }

        for (CutsceneActionEntry actionEntry : actions) {
            if (partialTick >= actionEntry.getTime() && !actionEntry.isExecuted()) {
                actionEntry.setExecuted(true);
                actionEntry.getAction().execute(localPlayer);
            }
        }

        if (easingFunction == null) {
            return;
        }

        float t = partialTick / durationSeconds;
        t = (float) easingFunction.apply(t);

        double currentX = lerpBezier(startPosVec.x, endPosVec.x, t, "x");
        double currentY = lerpBezier(startPosVec.y, endPosVec.y, t, "y");
        double currentZ = lerpBezier(startPosVec.z, endPosVec.z, t, "z");

        this.currentPosition = new Vec3(currentX, currentY, currentZ);

        currentX = lerp(startRotVec.x, endRotVec.x, t);
        currentY = lerp(startRotVec.y, endRotVec.y, t);

        this.currentRotation = new Vec2((float) currentX, (float) currentY);

        this.currentFov = lerp(startFov, endFov, t);
    }

    private double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    private double lerpBezier(double start, double end, double t, String axis) {
        if (this.bezierPoint1 ==null) {
            return lerp(start, end, t);
        }

        if (this.bezierPoint2 == null) {
            double point = (Objects.equals(axis, "x")) ? this.bezierPoint1.x : ((Objects.equals(axis, "y")) ? this.bezierPoint1.y : this.bezierPoint1.z);

            return QuadraticBezier(start, point, end, t);
        }
        else {
            double point1 = (Objects.equals(axis, "x")) ? this.bezierPoint1.x : ((Objects.equals(axis, "y")) ? this.bezierPoint1.y : this.bezierPoint1.z);
            double point2 = (Objects.equals(axis, "x")) ? this.bezierPoint2.x : ((Objects.equals(axis, "y")) ? this.bezierPoint2.y : this.bezierPoint2.z);

            return CubicBezier(start, point1, point2, end, t);
        }
    }

    private double QuadraticBezier(double p0, double p1, double p2, double t)
    {
        double q0 = lerp(p0, p1, t);
        double q1 = lerp(p1, p2, t);

        return lerp(q0, q1, t);
    }

    private double CubicBezier(double p0, double p1, double p2, double p3, double t)
    {
        double q0 = lerp(p0, p1, t);
        double q1 = lerp(p1, p2, t);
        double q2 = lerp(p2, p3, t);

        double r0 = lerp(q0, q1, t);
        double r1 = lerp(q1, q2, t);

        return lerp(r0, r1, t);
    }

    public boolean isFinished() {
        return partialTick >= durationSeconds;
    }

    public void reset() {
        this.startTime = -1;
        this.partialTick = 0;
        this.currentPosition = new Vec3(0,0,0);
        this.currentRotation = new Vec2(0,0);
        this.currentFov = 70;
        for (CutsceneActionEntry actionEntry : actions) {
            actionEntry.setExecuted(false);
        }
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

    public String generateJavaCode() {
        String code;

        code =  "new EasingTransition(\n" +
                "   new Vec3(" + startPosVec.x + ", " + startPosVec.y + ", " + startPosVec.z + "),\n" +
                "   new Vec3(" + endPosVec.x + ", " + endPosVec.y + ", " + endPosVec.z + "),\n" +
                "   new Vec2(" + startRotVec.x + "f, " + startRotVec.y + "f),\n" +
                "   new Vec2(" + endRotVec.x + "f, " + endRotVec.y + "f),\n" +
                "   " + startFov + ",\n" +
                "   " + endFov + ",\n" +
                "   " + durationSeconds + ",\n" +
                "   " + easingFunction.generateJavaCode() + "\n" +
                ")";

        if (bezierPoint1 != null && bezierPoint2 != null) {
            code = code + ".setBezier(\n" +
                    "   new Vec3(" + bezierPoint1.x + ", " + bezierPoint1.y + ", " + bezierPoint1.z + "),\n" +
                    "   new Vec3(" + bezierPoint2.x + ", " + bezierPoint2.y + ", " + bezierPoint2.z + ")\n" +
                    ")";
        }
        else if (bezierPoint1 != null) {
            code = code + ".setBezier(\n" +
                    "   new Vec3(" + bezierPoint1.x + ", " + bezierPoint1.y + ", " + bezierPoint1.z + "),\n" +
                    "   null" +
                    ")";
        }

        return code;
    }
}
