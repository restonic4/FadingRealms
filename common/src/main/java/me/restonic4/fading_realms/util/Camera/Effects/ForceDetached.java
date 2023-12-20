package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.util.Camera.CameraManager;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ForceDetached {
    public static void sendForceDetached(Player player, boolean value) {
        if (CameraManager.isClient(player)) {
            forceDetached(player, value);
        }
        else {
            sendForceDetachedPacket((ServerPlayer) player, value);
        }
    }

    public static void sendForceDetachedPacket(ServerPlayer player, boolean value) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeBoolean(value);
        NetworkManager.sendToPlayer(player, CameraManager.ForceDetachedPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        forceDetached(context.getPlayer(), buf.readBoolean());
    }

    public static void forceDetached(Player player, boolean value) {
        Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();
        ICameraMixin camera = ((ICameraMixin) cam);

        if (player != null) {
            camera.forceDetach(value);
        }
    }
}
