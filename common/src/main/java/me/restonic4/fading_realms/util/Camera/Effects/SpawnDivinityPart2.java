package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortal;
import me.restonic4.fading_realms.entity.Divinity.Divinity;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SpawnDivinityPart2 {
    public static boolean isSpawned = false;
    public static int readyPlayers = 0;

    public static void send(Player player) {
        if (!PacketManager.isClient(player)) {
            execute(player);
        }
        else {
            sendPacket(player);
        }
    }

    public static void sendPacket(Player player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        NetworkManager.sendToServer(PacketManager.SpawnDivinityPart2PacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        execute(context.getPlayer());
    }

    public static void execute(Player player) {
        readyPlayers++;

        if (!isSpawned && readyPlayers >= player.getServer().getPlayerCount() * 0.75f) {
            isSpawned = true;

            MinecraftServer server = player.getServer();

            for (ServerLevel serverLevel : server.getAllLevels()) {
                if (serverLevel.dimension().toString().toLowerCase().contains("before_limbo")) {
                    if (SpawnDivinity.portal != null) {
                        SpawnDivinity.portal.kill();
                    }

                    Divinity divinity = new Divinity(EntityManager.DIVINITY.get(), player.level());
                    divinity.setNoAi(true);
                    divinity.setPos(0.5f, -15f, -64.5f);

                    serverLevel.addFreshEntity(divinity);

                    for (Player playerFound : server.getPlayerList().getPlayers()) {
                        PacketManager.playCutscene(playerFound, 4, 4);
                    }
                }
            }
        }
    }
}
