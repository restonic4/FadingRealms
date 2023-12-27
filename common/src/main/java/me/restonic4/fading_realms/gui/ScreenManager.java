package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class ScreenManager {
    public static <T extends Screen> void openScreen(T screen, float delay) {
        Minecraft mc = Minecraft.getInstance();

        boolean canSetScreen = mc.screen != null && !(mc.screen.getTitle().getString().contains("You Died!") || mc.screen.getTitle().getString().contains("Game Over!"));

        if (canSetScreen || mc.screen == null) {
            if (screen == null && delay > 0) {
                Timer timer2 = new Timer();

                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        mc.setScreen(null);
                    }
                };

                timer2.schedule(task2, (long) (delay * 1000));
            }
            else {
                mc.setScreen(null);
            }


            if (screen != null && delay > 0) {
                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        mc.setScreen(screen);
                    }
                };

                timer.schedule(task, (long) (delay * 1000));
            } else if (screen != null) {
                mc.setScreen(screen);
            }
        }
    }

    public static <T extends Screen> void openScreen(T screen) {
        openScreen(screen, 0);
    }

    public static void openBlackBars() {
        BlackBarsScreen blackBarsScreen = new BlackBarsScreen();

        ICameraMixin cam = (ICameraMixin) Minecraft.getInstance().gameRenderer.getMainCamera();
        cam.setBlackBars(blackBarsScreen);

        openScreen(blackBarsScreen);
    }

    public static void closeBlackBars() {
        ICameraMixin cam = (ICameraMixin) Minecraft.getInstance().gameRenderer.getMainCamera();
        cam.closeBlackBars();
    }
}
