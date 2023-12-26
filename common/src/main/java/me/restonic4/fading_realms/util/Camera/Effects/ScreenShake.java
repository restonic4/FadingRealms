package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ScreenShake {
    public static void sendShake(Player player, float intensity) {
        if (PacketManager.isClient(player)) {
            shake(player, intensity);
        }
        else {
            sendShakePacket((ServerPlayer) player, intensity);
        }
    }

    public static void sendShakePacket(ServerPlayer player, float intensity) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeFloat(intensity);
        NetworkManager.sendToPlayer(player, PacketManager.CameraShakePacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        shake(context.getPlayer(), buf.readFloat());
    }

    public static void shake(Player player, float intensity) {
        Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();
        ICameraMixin camera = ((ICameraMixin) cam);

        if (player != null) {
            camera.shake(intensity);
        }
    }
}
