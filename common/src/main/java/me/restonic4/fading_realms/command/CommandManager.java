package me.restonic4.fading_realms.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import me.restonic4.fading_realms.dimension.Limbo;
import me.restonic4.fading_realms.entity.Divinity.Divinity;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.util.RingCalculator;
import me.restonic4.restapi.RestApi;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;
import static me.restonic4.fading_realms.FadingRealms.RING_SIZE;

public class CommandManager {
    private static final Map<String, CommandHandler> COMMAND_HANDLERS = new HashMap<>();

    static {
        COMMAND_HANDLERS.put("set_spawn_ring_data", CommandManager::set_spawn_ring_data);
        COMMAND_HANDLERS.put("set_before_limbo_box", CommandManager::set_before_limbo_box);
        COMMAND_HANDLERS.put("set_before_limbo_divinity", CommandManager::set_before_limbo_divinity);
    }

    private static void defaultHandler(CommandContext<CommandSourceStack> context) {
        //Nothing
    }

    ////////////////////////////////////

    public static void set_spawn_ring_data(CommandContext<CommandSourceStack> context) {
        RestApi.Log("Generating spawn ring");

        List<BlockPos> posList = RingCalculator.generateRing(RING_SIZE, 10000);

        for (BlockPos blockPos : posList) {
            RestApi.Log("Pos: " + blockPos);
        }
    }

    public static void set_before_limbo_box(CommandContext<CommandSourceStack> context) {
        RestApi.Log("Generating before limbo barrier box");

        MinecraftServer server = context.getSource().getServer();

        Level level = null;

        for (Level levelFound : server.getAllLevels()) {
            String levelName = levelFound.dimension().toString();

            if (levelName.contains("before") && levelName.contains("limbo")) {
                level = levelFound;
            }
        }

        int boxSize = 15;

        Limbo.generateHollowBox(
                level,
                new BlockPos(-boxSize, -boxSize, -boxSize),
                new BlockPos(boxSize, boxSize, boxSize),
                Blocks.BARRIER
        );
    }

    public static void set_before_limbo_divinity(CommandContext<CommandSourceStack> context) {
        RestApi.Log("Generating before limbo divinity");

        MinecraftServer server = context.getSource().getServer();

        Level level = null;

        for (Level levelFound : server.getAllLevels()) {
            String levelName = levelFound.dimension().toString();

            if (levelName.contains("before") && levelName.contains("limbo")) {
                level = levelFound;
            }
        }

        if (level != null) {
            Divinity entity = new Divinity(EntityManager.DIVINITY.get(), level);
            entity.setNoAi(true);
            entity.setPos(0.5f, -15f, -64.5f);

            level.addFreshEntity(entity);

            RestApi.Log("Added");
        }
        else {
            RestApi.Log("Level is null");
        }
    }

    ////////////////////////////////////

    public static void executeCommand(String command, CommandContext<CommandSourceStack> context) {
        COMMAND_HANDLERS.getOrDefault(command, CommandManager::defaultHandler).handle(context);
    }

    public static void addCommand(CommandDispatcher<CommandSourceStack> dispatcher, String command, int level) {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(level)
                        )
                        .executes(
                                context -> {
                                    executeCommand(command, context);

                                    return Command.SINGLE_SUCCESS;
                                }
                        )
        );
    }

    public static void register() {
        CommandRegistrationEvent.EVENT.register(
                (dispatcher, registry, selection) -> {
                    addCommand(dispatcher, "set_spawn_ring_data", 4);
                    addCommand(dispatcher, "set_before_limbo_box", 4);
                    addCommand(dispatcher, "set_before_limbo_divinity", 4);
                }
        );
    }

    private interface CommandHandler {
        void handle(CommandContext<CommandSourceStack> context);
    }
}
