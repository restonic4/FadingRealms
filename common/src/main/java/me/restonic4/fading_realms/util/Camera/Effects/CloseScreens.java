package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.gui.ScreenManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class CloseScreens {
    public static void send(Player player, float delay) {
        if (PacketManager.isClient(player)) {
            execute(player, delay);
        }
        else {
            sendPacket((ServerPlayer) player, delay);
        }
    }

    public static void sendPacket(ServerPlayer player, float delay) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeFloat(delay);
        NetworkManager.sendToPlayer(player, PacketManager.CloseScreensPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        execute(context.getPlayer(), buf.readFloat());
    }

    public static void execute(Player player, float delay) {
        if (player != null) {
            ScreenManager.openScreen(null, delay);
        }
    }
}
