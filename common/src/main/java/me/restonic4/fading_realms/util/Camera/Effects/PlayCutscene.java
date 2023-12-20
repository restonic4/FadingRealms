package me.restonic4.fading_realms.util.Camera.Effects;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.restonic4.fading_realms.util.Camera.CameraManager;
import me.restonic4.fading_realms.util.Camera.Cutscene.Cutscene;
import me.restonic4.fading_realms.util.Camera.Cutscene.Cutscenes;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class PlayCutscene {
    public static void sendCutscene(Player player, int cutsceneID) {
        if (CameraManager.isClient(player)) {
            cutscene(player, cutsceneID);
        }
        else {
            sendCutscenePacket((ServerPlayer) player, cutsceneID);
        }
    }

    public static void sendCutscenePacket(ServerPlayer player, int cutsceneID) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(cutsceneID);
        NetworkManager.sendToPlayer(player, CameraManager.PlayCutscenePacketId, buf);
    }

    public static void translateMessage(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        cutscene(context.getPlayer(), buf.readInt());
    }

    public static void cutscene(Player player, int cutsceneID) {
        Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();
        ICameraMixin camera = ((ICameraMixin) cam);

        if (player != null) {
            Cutscene cutscene = Cutscenes.getCutscene(cutsceneID);

            camera.playCutscene(cutscene);
        }
    }
}
