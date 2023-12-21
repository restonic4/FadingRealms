package me.restonic4.fading_realms.util.Camera.Cutscene;

import me.restonic4.fading_realms.command.CommandManager;
import me.restonic4.restapi.RestApi;
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

    public static Cutscene xd = new Cutscene().setForceDetached(true).setHideGui(true)
            .addTransition(
                    new EasingTransition(
                            new Vec3(34.43436844186763, 97.46077650886616, -38.276982483822),
                            new Vec3(26.793053507869143, 97.46077650886616, -31.783605571170703),
                            new Vec2(36.599407f, 36.599407f),
                            new Vec2(26.549376f, 26.549376f),
                            10.0,
                            70.0,
                            5,
                            new Easing().quadInOut()
                    )
            );

    public static Cutscene getCutscene(int id) {
        if (id == -1) {
            return new Cutscene().setForceDetached(true).setHideGui(true).addTransition(new EasingTransition(CommandManager.pathData));
        }
        else if (id == 1) {
            return new Cutscene(cutscene);
        }
        else if (id == 2) {
            Cutscene cutscene = new Cutscene(xd);

            for(EasingTransition transition : cutscene.getTransitions()) {
                RestApi.Log(transition.generateJavaCode());
            }

            return cutscene;
        }
        else {
            return null;
        }
    }
}
