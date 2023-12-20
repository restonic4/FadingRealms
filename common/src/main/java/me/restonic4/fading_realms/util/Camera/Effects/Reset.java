package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.util.Camera.CameraManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class Reset {
    public static void sendReset(Player player) {
        if (CameraManager.isClient(player)) {
            reset(player);
        }
        else {
            sendResetPacket((ServerPlayer) player);
        }
    }

    public static void sendResetPacket(ServerPlayer player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        NetworkManager.sendToPlayer(player, CameraManager.ResetCameraPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        reset(context.getPlayer());
    }

    public static void reset(Player player) {
        Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();

        if (player != null) {
            cam.reset();
        }
    }
}
