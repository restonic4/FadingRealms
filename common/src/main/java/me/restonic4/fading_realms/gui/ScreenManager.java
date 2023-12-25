package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class ScreenManager {
    public static <T extends Screen> void openScreen(T screen) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(null);
        mc.setScreen(screen);
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
