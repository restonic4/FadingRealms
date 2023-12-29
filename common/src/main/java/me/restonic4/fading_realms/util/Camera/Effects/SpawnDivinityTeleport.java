package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.entity.DivinityPortal.DivinityPortal;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class SpawnDivinityTeleport {
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
        NetworkManager.sendToServer(PacketManager.SpawnDivinityTeleportPacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        execute(context.getPlayer());
    }

    public static void execute(Player player) {
        player.teleportTo(0, 0, 0);
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.INVISIBILITY, 20 * 3, 1, true, true, false
                )
        );
    }
}
