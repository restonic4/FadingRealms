package me.restonic4.fading_realms.util.Camera;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import me.restonic4.fading_realms.util.Camera.Effects.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class PacketManager {
    /**
        CHANNELS AND MESSAGES
    **/

    public static final NetworkChannel Channel = NetworkChannel.create(new ResourceLocation(MOD_ID, "networking_channel"));

    //SERVER TO CLIENT
    public static final ResourceLocation CameraShakePacketId = new ResourceLocation(MOD_ID, "camera_shake_packet");
    public static final ResourceLocation ResetCameraPacketId = new ResourceLocation(MOD_ID, "reset_camera_packet");
    public static final ResourceLocation ForceDetachedCameraPacketId = new ResourceLocation(MOD_ID, "force_detached_camera_packet");
    public static final ResourceLocation PlayCutscenePacketId = new ResourceLocation(MOD_ID, "play_cutscene_packet");

    //CLIENT TO SERVER
    public static final ResourceLocation SpawnDivinityPacketId = new ResourceLocation(MOD_ID, "spawn_divinity_packet");

    /**
        METHODS TO CALL
    **/
    public static void screenShake(Player player, float intensity) {
        ScreenShake.sendShake(player, intensity);
    }

    public static void resetCamera(Player player) {
        Reset.sendReset(player);
    }

    public static void forceDetachedCamera(Player player, boolean value) {
        ForceDetached.sendForceDetached(player, value);
    }

    public static void playCutscene(Player player, int cutsceneID) {
        PlayCutscene.sendCutscene(player, cutsceneID);
    }

    public static void spawnDivinity(Player player) {
        SpawnDivinity.send(player);
    }

    /**
        MAIN METHODS
    **/

    public static void init() {
        registerPackets();
        registerEvents();
    }

    private static void registerPackets() {
        //Start camera shake
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, CameraShakePacketId, ScreenShake::translateMessage);

        //Reset camera effects
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ResetCameraPacketId, Reset::translateMessage);

        //Force detached camera
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, ForceDetachedCameraPacketId, ForceDetached::translateMessage);

        //Play cutscene
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, PlayCutscenePacketId, PlayCutscene::translateMessage);

        //Spawn divinity
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SpawnDivinityPacketId, SpawnDivinity::translateMessage);
    }

    private static void registerEvents() {
        //Reset camera effects on quit
        PlayerEvent.PLAYER_QUIT.register(
                PacketManager::resetCamera
        );

        //Reset camera effects on dimension change
        PlayerEvent.CHANGE_DIMENSION.register(
                (serverPlayer, oldLevel, newLevel) -> {
                    PacketManager.resetCamera(serverPlayer);
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
