package me.restonic4.fading_realms.command;

import com.mojang.brigadier.Command;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import me.restonic4.fading_realms.util.RingCalculator;
import me.restonic4.restapi.RestApi;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;

import java.util.List;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;
import static me.restonic4.fading_realms.FadingRealms.RING_SIZE;

public class CommandManager {
    public static void register() {
        CommandRegistrationEvent.EVENT.register(
                (dispatcher, registry, selection) -> {
                    dispatcher.register(
                            Commands.literal("setring")
                                    .requires(
                                            source -> source.hasPermission(4)
                                    )
                                    .executes(
                                            context -> {
                                                RestApi.Log("Generating spawn ring");

                                                MinecraftServer server = context.getSource().getServer();

                                                List<BlockPos> posList = RingCalculator.generateRing(RING_SIZE, 10000);

                                                for (BlockPos blockPos : posList) {
                                                    /*for (int i = 0; i < 75; i++) {
                                                        server.overworld().setBlock(blockPos.offset(0, i,0), Blocks.DIAMOND_BLOCK.defaultBlockState(), 1);
                                                    }*/
                                                    RestApi.Log("Pos: " + blockPos);
                                                }

                                                return Command.SINGLE_SUCCESS;
                                            }
                                    )
                    );
                }
        );
    }
}
