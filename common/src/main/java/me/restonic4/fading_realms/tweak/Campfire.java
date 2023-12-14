package me.restonic4.fading_realms.tweak;

import dev.architectury.event.events.common.TickEvent;
import me.restonic4.fading_realms.advancement.AdvancementsManager;
import me.restonic4.fading_realms.util.POIManager;
import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class Campfire {
    public static void register() {
        AtomicInteger tickCount = new AtomicInteger();

        TickEvent.SERVER_POST.register(
                (server) -> {
                    ServerLevel level = server.overworld().getLevel();

                    level.players().forEach(player -> {
                        //0 0 advancement
                        if (player.getX() <= 50 && player.getX() >= -50 && player.getZ() <= 50 && player.getZ() >= -50) {
                            RestApi.Log("Player in bounds and advancement state of: " + player.getAdvancements().getOrStartProgress(AdvancementsManager.welcome_to_harmony.getAdvancement(player)).isDone());
                            if (player.getAdvancements().getOrStartProgress(AdvancementsManager.welcome_to_harmony.getAdvancement(player)).isDone()) {
                                AdvancementsManager.center_of_harmony.grant(player);
                            }
                        }
                    });

                    if (shouldCheck(level, tickCount)) {
                        level.players().forEach(player -> {
                            List<BlockPos> poi = getPOI(level, player);

                            int modified = 0;
                            int maxNumber = (poi.size() / 3 == 0) ? 1 : poi.size() / 3;
                            int maxToModify = new Random().nextInt(maxNumber) + 1;

                            for (BlockPos poiPos : poi) {
                                BlockState campfire = level.getBlockState(poiPos);

                                boolean isOutside = isOutside(
                                        checkRow(level, poiPos, poiPos.getX(), poiPos.getZ(), 64),
                                        checkRow(level, poiPos, poiPos.getX() + 1, poiPos.getZ(), 6),
                                        checkRow(level, poiPos, poiPos.getX() - 1, poiPos.getZ(), 6),
                                        checkRow(level, poiPos, poiPos.getX(), poiPos.getZ() + 1, 6),
                                        checkRow(level, poiPos, poiPos.getX(), poiPos.getZ() - 1, 6)
                                );

                                if (campfire.getValue(CampfireBlock.LIT) && modified <= maxToModify && isOutside) {
                                    CampfireBlock.dowse(player, level, poiPos, campfire);
                                    BlockState newState = campfire.setValue(CampfireBlock.LIT, false);
                                    level.setBlock(poiPos, newState, 11);

                                    modified++;
                                }
                            }
                        });
                    }
                }
        );
    }

    public static boolean shouldCheck(ServerLevel level, AtomicInteger tickCount) {
        if (level.isRaining() || level.isThundering()) {
            tickCount.getAndIncrement();

            if (tickCount.get() >= 120) {
                tickCount.set(0);

                return true;
            }
        }

        return false;
    }

    public static List<BlockPos> getPOI(ServerLevel level, ServerPlayer player) {
        PoiManager pointofinterestmanager = level.getPoiManager();
        BlockPos blockPos = player.getOnPos();

        Stream<BlockPos> stream = pointofinterestmanager.findAll(poiTypeHolder -> poiTypeHolder.is(POIManager.campfire.getKey()), blockPos1 -> true, blockPos, 128, PoiManager.Occupancy.ANY);
        return stream.collect(Collectors.toList());
    }

    public static boolean checkRow(ServerLevel level, BlockPos poiPos, int x, int z, int maxY) {
        boolean isOutside = true;

        for (int i = 1; i < maxY; i++) {
            Block foundBlock = level.getBlockState(new BlockPos(x, poiPos.getY() + i, z)).getBlock();

            if (foundBlock != Blocks.AIR && foundBlock != Blocks.VOID_AIR && foundBlock != Blocks.CAVE_AIR) {
                isOutside = false;
            }
        }

        return isOutside;
    }

    public static boolean isOutside(boolean mainCheck, boolean check1, boolean check2, boolean check3, boolean check4) {
        boolean isOutside = false;

        if (mainCheck) {
            boolean[] checks = {check1, check2, check3, check4};
            int count = 0;

            for (boolean check : checks) {
                if (check) {
                    count++;
                }
            }

            if (count >= 2) {
                isOutside = true;
            }
        }

        return isOutside;
    }
}
