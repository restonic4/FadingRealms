package me.restonic4.fading_realms.data;

import dev.architectury.event.events.common.PlayerEvent;
import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;

import java.util.UUID;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class PlayerWorldPositionManager {
    public static void register() {
        //TP PLAYER AT RESPAWN IF BED NOT FOUND
        PlayerEvent.PLAYER_RESPAWN.register(
                (serverPlayer, conqueredEnd) -> {
                    ModSavedData data = ModSavedData.manage(serverPlayer.server);

                    UUID uuid = serverPlayer.getUUID();

                    RestApi.Log("Player " + uuid + " respawned, reading spawn data..", MOD_ID);
                    RestApi.Log("-----------------------------------------------------------------------------------", MOD_ID);

                    RestApi.Log("Is player data empty? " + data.isEmpty(uuid), MOD_ID);
                    RestApi.Log("Pos data: " + data.getX(uuid) + ", " + data.getY(uuid) + ", " + data.getZ(uuid), MOD_ID);

                    if (!data.isEmpty(uuid)) {
                        RestApi.Log("Respawn pos data: " + serverPlayer.getRespawnPosition(), MOD_ID);

                        //Set spawn to saved one
                        if (serverPlayer.getRespawnPosition() == null) {
                            RestApi.Log("Changing spawn position to: " + data.getPosition(uuid), MOD_ID);
                            //serverPlayer.setRespawnPosition(serverPlayer.server.overworld().dimension(), data.getPosition(uuid), 0, true, false);

                            BlockPos pos = data.getPosition(uuid);

                            serverPlayer.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                        }
                    }
                }
        );

        //CREATE INITIAL PLAYER DATA ON JOIN
        PlayerEvent.PLAYER_JOIN.register(
                (serverPlayer) -> {
                    ModSavedData data = ModSavedData.manage(serverPlayer.server);

                    UUID uuid = serverPlayer.getUUID();

                    RestApi.Log("Player " + uuid + " joined, reading spawn data..", MOD_ID);
                    RestApi.Log("-----------------------------------------------------------------------------------", MOD_ID);

                    RestApi.Log("Is player data empty? " + data.isEmpty(uuid), MOD_ID);
                    RestApi.Log("Pos data: " + data.getX(uuid) + ", " + data.getY(uuid) + ", " + data.getZ(uuid), MOD_ID);

                    if (data.isEmpty(uuid)) {
                        RestApi.Log("Pos data created");
                        data.setPosition(uuid, 0, 0, 0);
                    }
                }
        );
    }
}
