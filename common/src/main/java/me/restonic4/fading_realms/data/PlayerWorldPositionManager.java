package me.restonic4.fading_realms.data;

import dev.architectury.event.events.common.PlayerEvent;
import me.restonic4.fading_realms.advancement.AdvancementsManager;
import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class PlayerWorldPositionManager {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void register() {
        //TP PLAYER AT RESPAWN IF BED NOT FOUND
        //TP PLAYER TO BEFORE LIMBO IF DIES, started == false
        PlayerEvent.PLAYER_RESPAWN.register(
                (serverPlayer, conqueredEnd) -> {
                    ModSavedData data = ModSavedData.manage(serverPlayer.server);

                    UUID uuid = serverPlayer.getUUID();

                    RestApi.Log("Player " + uuid + " respawned, reading spawn data..", MOD_ID);
                    RestApi.Log("-----------------------------------------------------------------------------------", MOD_ID);

                    RestApi.Log("Is player data empty? " + data.isEmpty(uuid), MOD_ID);
                    RestApi.Log("Pos data: " + data.getX(uuid) + ", " + data.getY(uuid) + ", " + data.getZ(uuid), MOD_ID);

                    if (!data.isEmpty(uuid)) {
                        if (data.started(uuid)) {
                            RestApi.Log("Respawn pos data: " + serverPlayer.getRespawnPosition(), MOD_ID);

                            //Set spawn to saved one
                            if (serverPlayer.getRespawnPosition() == null) {
                                RestApi.Log("Changing spawn position to: " + data.getPosition(uuid), MOD_ID);

                                BlockPos pos = data.getPosition(uuid);

                                serverPlayer.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                            }
                        }
                        else {
                            RestApi.Log("DIED BEFORE START, TP TO BEFORE LIMBO", MOD_ID);

                            tpToDim(serverPlayer, 0, 1, 0);
                        }
                    }
                }
        );

        //CREATE INITIAL PLAYER DATA ON JOIN AND TP TO BEFORE LIMBO
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

                        tpToDim(serverPlayer, 0, 1, 0);
                    }
                    else {
                        MinecraftServer server = serverPlayer.server;

                        boolean inBeforeLimbo = false;

                        for (ServerLevel levelFound : server.getAllLevels()) {
                            if (data.started(uuid)) {
                                String dimName = levelFound.dimension().toString();

                                if (dimName.contains("before") && dimName.contains("limbo") && serverPlayer.level() == levelFound) {
                                    inBeforeLimbo = true;
                                }
                            }
                        }

                        if (inBeforeLimbo) {
                            serverPlayer.teleportTo(server.overworld(), data.getX(uuid), data.getY(uuid), data.getZ(uuid), 0, 0);

                            //5 secs delay
                            scheduler.schedule(
                                    () -> AdvancementsManager.welcome_to_harmony.grant(serverPlayer),
                                    5,
                                    TimeUnit.SECONDS
                            );
                        }
                    }
                }
        );
    }

    public static void tpToDim(ServerPlayer serverPlayer, int x, int y, int z) {
        MinecraftServer server = serverPlayer.server;

        for (ServerLevel levelFound : server.getAllLevels()) {
            String dimName = levelFound.dimension().toString();

            if (dimName.contains("before") && dimName.contains("limbo")) {
                serverPlayer.teleportTo(levelFound, x, y, z, 0, 0);
            }
        }
    }
}
