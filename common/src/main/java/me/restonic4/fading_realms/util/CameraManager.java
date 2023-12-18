package me.restonic4.fading_realms.util;

import me.restonic4.fading_realms.mixin.CameraMixin;
import me.restonic4.fading_realms.mixin.ICameraMixin;
import me.restonic4.restapi.RestApi;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Rotation;

import java.util.List;

public class CameraManager {
    public static void shake(List<Player> players) {
        if (!players.isEmpty()) {
            players.forEach(CameraManager::shake);
        }
    }

    public static void shake(Player player) {
        RestApi.Log("Shaking " + player.getName() + "'s camera.");

        if (player.level().isClientSide()) {
            Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();
            ICameraMixin camera = ((ICameraMixin) cam);

            camera.setRot(1,1);
            camera.setPos(1, 1, 1);
        }
    }
}
