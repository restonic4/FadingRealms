package me.restonic4.fading_realms.util.Camera.Cutscene;

import dev.architectury.registry.menu.MenuRegistry;
import me.restonic4.fading_realms.command.CommandManager;
import me.restonic4.fading_realms.gui.BlackBarsScreen;
import me.restonic4.fading_realms.gui.ScreenManager;
import me.restonic4.fading_realms.util.Camera.CameraManager;
import me.restonic4.restapi.RestApi;
import net.minecraft.client.Minecraft;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Cutscenes {
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
                            new Vec3(50, 100, 30)
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

    public static Cutscene intro = new Cutscene().setForceDetached(true).setHideGui(true)
            .addTransition(
                    new EasingTransition(
                            new Vec3(-13.699999988079071, -11.875790366522379, 14.699999988079071),
                            new Vec3(14.699999988079071, 14.820000052452087, 0.07846771920797348),
                            new Vec2(225.30f, -27.75f),
                            new Vec2(92.10f, 39.90f),
                            20.0,
                            50.0,
                            12,
                            new Easing().quadInOut()
                    ).setBezier(
                            new Vec3(11.65, 5.00, 16.66)
                    ).setAction(0,
                            (localPlayer) -> {
                                CameraManager.shake(localPlayer, 0);
                            }
                    )
            ).addTransition(
                    new EasingTransition(
                            new Vec3(14.699999988079071, 14.820000052452087, 0.07846771920797348),
                            new Vec3(12.97, 14.82, -2.96),
                            new Vec2(92.10f, 39.90f),
                            new Vec2(167.85f, -3.90f),
                            50.0,
                            35.0,
                            1,
                            new Easing().quadInOut()
                    ).setAction(0,
                            (localPlayer) -> {
                                CameraManager.shake(localPlayer, 0.4f);
                                ScreenManager.openBlackBars();
                                //tell server to spawn the divinity once
                            }
                    )
            ).addTransition(
                    new EasingTransition(3)
            ).addTransition(
                    new EasingTransition(
                            new Vec3(12.97, 14.82, -2.96),
                            new Vec3(12.97, 14.82, -2.96),
                            new Vec2(167.85f, -3.90f),
                            new Vec2(167.85f, -3.90f),
                            35.0,
                            50.0,
                            1,
                            new Easing().quadInOut()
                    ).setAction(0,
                            (localPlayer) -> {
                                CameraManager.shake(localPlayer, 0.1f);
                            }
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
            return new Cutscene(xd);
        }
        else if (id == 3) {
            return new Cutscene(intro);
        }
        else {
            return null;
        }
    }
}