package me.restonic4.fading_realms.dimension;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import me.restonic4.fading_realms.util.Camera.CameraManager;
import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Limbo {
    public static void init() {
        TickEvent.SERVER_POST.register(
                (server) -> {
                    for (ServerLevel levelFound : server.getAllLevels()) {
                        String dimName = levelFound.dimension().toString();

                        if (dimName.contains("before") && dimName.contains("limbo")) {
                            for (ServerPlayer serverPlayer : levelFound.players()) {
                                if (serverPlayer.getY() <= 0) {
                                    MobEffectInstance effect = new MobEffectInstance(MobEffects.LEVITATION, 60, 1);

                                    serverPlayer.addEffect(effect);
                                }
                            }
                        }
                    }
                }
        );

        PlayerEvent.CHANGE_DIMENSION.register(
                (serverPlayer, oldLevel, newLevel) -> {
                    String newLevelName = newLevel.toString();
                    //Not op
                    if (serverPlayer.server.getProfilePermissions(serverPlayer.getGameProfile()) < 4) {
                        RestApi.Log("Not op");

                        if (newLevelName.contains("before") && newLevelName.contains("limbo")) {
                            RestApi.Log("In before limbo");
                            serverPlayer.setGameMode(GameType.ADVENTURE);
                        }
                        else {
                            serverPlayer.setGameMode(GameType.SURVIVAL);
                        }
                    }
                    else {
                        RestApi.Log("Op");
                    }
                }
        );
    }

    public static void generateHollowBox(Level level, BlockPos lowerCorner, BlockPos upperCorner, Block block) {
        fillBox(level, lowerCorner, upperCorner, block.defaultBlockState());
        emptyBox(level, lowerCorner.offset(1, 1, 1), upperCorner.offset(-1, -1, -1), Blocks.AIR.defaultBlockState());
    }

    private static void fillBox(Level level, BlockPos lowerCorner, BlockPos upperCorner, BlockState blockState) {
        for (int x = lowerCorner.getX(); x <= upperCorner.getX(); x++) {
            for (int y = lowerCorner.getY(); y <= upperCorner.getY(); y++) {
                for (int z = lowerCorner.getZ(); z <= upperCorner.getZ(); z++) {
                    level.setBlock(new BlockPos(x, y, z), blockState, 2);
                }
            }
        }
    }

    private static void emptyBox(Level level, BlockPos lowerCorner, BlockPos upperCorner, BlockState blockState) {
        for (int x = lowerCorner.getX(); x <= upperCorner.getX(); x++) {
            for (int y = lowerCorner.getY(); y <= upperCorner.getY(); y++) {
                for (int z = lowerCorner.getZ(); z <= upperCorner.getZ(); z++) {
                    level.setBlock(new BlockPos(x, y, z), blockState, 2);

                    /*if (y % 2 == 0) {
                        level.setBlock(new BlockPos(x + 1, y, z), blockState, 2);
                        level.setBlock(new BlockPos(x - 1, y, z), blockState, 2);
                        level.setBlock(new BlockPos(x, y, z + 1), blockState, 2);
                        level.setBlock(new BlockPos(x, y, z - 1), blockState, 2);
                    }*/
                }
            }
        }
    }
}
