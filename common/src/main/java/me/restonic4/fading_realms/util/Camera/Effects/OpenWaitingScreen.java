package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.gui.IntroWaitScreen;
import me.restonic4.fading_realms.gui.ScreenManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.minecraft.client.gui.screens.multiplayer.WarningScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class OpenWaitingScreen {
    public static void send(Player player) {
        if (PacketManager.isClient(player)) {
            execute(player);
        }
        else {
            sendPacket((ServerPlayer) player);
        }
    }

    public static void sendPacket(ServerPlayer player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        NetworkManager.sendToPlayer(player, PacketManager.OpenWaitingScreenPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        execute(context.getPlayer());
    }

    public static void execute(Player player) {
        if (player != null) {
            ScreenManager.openScreen(new IntroWaitScreen());
        }
    }
}
