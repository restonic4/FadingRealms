package me.restonic4.fading_realms.util.Camera;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import me.restonic4.fading_realms.util.Camera.Effects.PlayCutscene;
import me.restonic4.fading_realms.util.Camera.Effects.ForceDetached;
import me.restonic4.fading_realms.util.Camera.Effects.Reset;
import me.restonic4.fading_realms.util.Camera.Effects.ScreenShake;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class CameraManager {
    public static final NetworkChannel Channel = NetworkChannel.create(new ResourceLocation(MOD_ID, "networking_channel"));

    public static final ResourceLocation CameraShakePacketId = new ResourceLocation(MOD_ID, "camera_shake_packet");
    public static final ResourceLocation ResetCameraPacketId = new ResourceLocation(MOD_ID, "reset_camera_packet");
    public static final ResourceLocation ForceDetachedPacketId = new ResourceLocation(MOD_ID, "force_detached_packet");
    public static final ResourceLocation PlayCutscenePacketId = new ResourceLocation(MOD_ID, "play_cutscene_packet");

    public static void shake(Player player, float intensity) {
        ScreenShake.sendShake(player, intensity);
    }

    public static void reset(Player player) {
        Reset.sendReset(player);
    }

    public static void forceDetached(Player player, boolean value) {
        ForceDetached.sendForceDetached(player, value);
    }

    public static void playCutscene(Player player, int cutsceneID) {
        PlayCutscene.sendCutscene(player, cutsceneID);
    }

    //MAIN METHODS

    public static void init() {
        registerPackets();
        registerEvents();
    }

    private static void registerPackets() {
        //Start camera shake
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, CameraShakePacketId, ScreenShake::translateMessage);

        //Reset camera effects
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ResetCameraPacketId, Reset::translateMessage);

        //Force detached
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ForceDetachedPacketId, ForceDetached::translateMessage);

        //Cutscene
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, PlayCutscenePacketId, PlayCutscene::translateMessage);
    }

    private static void registerEvents() {
        //Reset camera effects on quit
        PlayerEvent.PLAYER_QUIT.register(
                CameraManager::reset
        );

        //Reset camera effects on dimension change
        PlayerEvent.CHANGE_DIMENSION.register(
                (serverPlayer, oldLevel, newLevel) -> {
                    CameraManager.reset(serverPlayer);
                }
        );
    }

    public static boolean isClient(Player player) {
        if (player == null) {
            return false;
        }

        return player.level().isClientSide();
    }
}
