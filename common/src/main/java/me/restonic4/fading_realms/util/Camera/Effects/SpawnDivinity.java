package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.command.CommandManager;
import me.restonic4.fading_realms.entity.Divinity.Divinity;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortal;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpawnDivinity {
    public static boolean isSpawned = false;
    public static DivinityPortal portal;

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
        NetworkManager.sendToServer(PacketManager.SpawnDivinityPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        execute(context.getPlayer());
    }

    public static void execute(Player player) {
        if (!isSpawned) {
            isSpawned = true;

            MinecraftServer server = player.getServer();

            if (CommandManager.divinityPortalInit != null) {
                CommandManager.divinityPortalInit.teleportTo(0, 500, 0);
                CommandManager.divinityPortalInit.kill();
            }

            for (ServerLevel serverLevel : server.getAllLevels()) {
                if (serverLevel.dimension().toString().toLowerCase().contains("before_limbo")) {
                    DivinityPortal divinityPortal = new DivinityPortal(EntityManager.DIVINITY_PORTAL.get(), player.level());
                    divinityPortal.setNoAi(true);
                    divinityPortal.setPos(0.5f, -5, -64.5f);

                    portal = divinityPortal;

                    serverLevel.addFreshEntity(divinityPortal);
                }
            }
        }
    }
}
