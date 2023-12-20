package me.restonic4.fading_realms.util.Camera.Cutscene;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Cutscenes {
    /*public static Cutscene cutscene = new Cutscene().setForceDetached(true).setHideGui(true)
            .addTransition(
                    new EasingTransition(
                            new Vec3(0, 0, 0),
                            new Vec3(100, 100, 100),
                            new Vec2(0, 0),
                            new Vec2(90, 0),
                            1,
                            70,
                            10,
                            new Easing().quadInOut()
                    )
            )
            .addTransition(
                    new EasingTransition(
                            new Vec3(100, 100, 100),
                            new Vec3(25, 100, 25),
                            new Vec2(90, 0),
                            new Vec2(0, 45),
                            70,
                            10,
                            5,
                            new Easing().quadInOut()
                    ).setBezier()
            )
            .addTransition(
                    new EasingTransition(
                            new Vec3(25, 100, 25),
                            new Vec3(25, 100, 25),
                            new Vec2(0, 45),
                            new Vec2(0, 45),
                            10,
                            30,
                            2,
                            new Easing().quadInOut()
                    )
            )
            .addTransition(
                    new EasingTransition(
                            new Vec3(25, 100, 25),
                            new Vec3(25, 100, 25),
                            new Vec2(0, 45),
                            new Vec2(0, 45),
                            30,
                            70,
                            1,
                            new Easing().bounceOut()
                    )
            );*/

    public static Cutscene cutscene = new Cutscene().setForceDetached(true).setHideGui(true)
            .addTransition(
                    new EasingTransition(
                            new Vec3(0, 100, 0),
                            new Vec3(100, 100, 0),
                            new Vec2(0, 0),
                            new Vec2(0, 0),
                            70,
                            70,
                            5,
                            new Easing().quadInOut()
                    ).setBezier(
                            new Vec3(50, 100, 30),
                            null
                    )
            )
            .addTransition(
                    new EasingTransition(
                            new Vec3(100, 100, 0),
                            new Vec3(0, 100, 0),
                            new Vec2(0, 0),
                            new Vec2(0, 0),
                            70,
                            70,
                            5,
                            new Easing().quadInOut()
                    ).setBezier(
                            new Vec3(50, 100, 32),
                            new Vec3(60, 100, -50)
                    )
            );

    public static Cutscene getCutscene(int id) {
        if (id == 1) {
            return new Cutscene(cutscene);
        }
        else {
            return null;
        }
    }
}
