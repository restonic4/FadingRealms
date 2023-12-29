package me.restonic4.fading_realms.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.client.ClientChatEvent;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import me.restonic4.fading_realms.dimension.Limbo;
import me.restonic4.fading_realms.entity.Divinity.Divinity;
import me.restonic4.fading_realms.entity.DivinityPortalInit.DivinityPortalInit;
import me.restonic4.fading_realms.entity.EntityManager;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import me.restonic4.fading_realms.util.Camera.Cutscene.*;
import me.restonic4.fading_realms.util.RingCalculator;
import me.restonic4.restapi.RestApi;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Vec2Argument;
import net.minecraft.commands.arguments.coordinates.WorldCoordinates;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.restonic4.fading_realms.FadingRealms.RING_SIZE;
import static net.minecraft.commands.Commands.argument;

public class CommandManager {
    private static final Map<String, CommandHandler> COMMAND_HANDLERS = new HashMap<>();

    public static EasingTransition pathData = new EasingTransition();

    public static DivinityPortalInit divinityPortalInit;
    public static boolean waiting = false;

    static {
        COMMAND_HANDLERS.put("set_spawn_ring_data", CommandManager::set_spawn_ring_data);
        COMMAND_HANDLERS.put("set_before_limbo_box", CommandManager::set_before_limbo_box);
        COMMAND_HANDLERS.put("set_before_limbo_divinity", CommandManager::set_before_limbo_divinity);
        COMMAND_HANDLERS.put("setup_intro_scene", CommandManager::setup_intro_scene);
        COMMAND_HANDLERS.put("open_waiting_screen", CommandManager::open_waiting_screen);
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
            DivinityPortalInit entity = new DivinityPortalInit(EntityManager.DIVINITY_PORTAL_INIT.get(), level);
            entity.setNoAi(true);
            entity.setPos(0.5f, -15f, -64.5f);

            divinityPortalInit = entity;

            level.addFreshEntity(entity);

            RestApi.Log("Added");
        }
        else {
            RestApi.Log("Level is null");
        }
    }

    public static void open_waiting_screen(CommandContext<CommandSourceStack> context) {
        waiting = true;

        List<ServerPlayer> targetPlayers = context.getSource().getServer().getPlayerList().getPlayers();

        for (ServerPlayer player : targetPlayers) {
            PacketManager.playCutscene(player, 5);
        }
    }

    public static void setup_intro_scene(CommandContext<CommandSourceStack> context) {
        open_waiting_screen(context);
        set_before_limbo_box(context);
        set_before_limbo_divinity(context);
    }

    public static void play_cutscene(CommandDispatcher<CommandSourceStack> dispatcher, String command, int permLevel) {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(permLevel)
                        )
                        .then(
                                argument("target", EntityArgument.players())
                                        .executes(
                                                context -> {
                                                    context.getSource().sendFailure(Component.nullToEmpty("Define the cutscene id!"));

                                                    return Command.SINGLE_SUCCESS;
                                                }
                                        )
                                        .then(
                                                argument("id", IntegerArgumentType.integer())
                                                        .executes(
                                                                context -> {
                                                                    RestApi.Log("Playing cutscene");

                                                                    List<ServerPlayer> targetPlayers = EntityArgument.getPlayers(context, "target").stream().toList();
                                                                    int cutsceneId = context.getArgument("id", Integer.class);

                                                                    if (Cutscenes.getCutscene(cutsceneId) != null) {
                                                                        for (ServerPlayer player : targetPlayers) {
                                                                            PacketManager.playCutscene(player, cutsceneId);
                                                                        }
                                                                    }
                                                                    else {
                                                                        context.getSource().sendFailure(Component.nullToEmpty("Wrong cutscene id!"));
                                                                    }

                                                                    return Command.SINGLE_SUCCESS;
                                                                }
                                                        )
                                        )
                        )
                        .executes(
                                context -> {
                                    context.getSource().sendFailure(Component.nullToEmpty("Define the cutscene id and players!"));

                                    return Command.SINGLE_SUCCESS;
                                }
                        )
        );
    }

    public static void start_intro(CommandContext<CommandSourceStack> context) {
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

    public static void addCutsceneStartPointCommand(CommandDispatcher<CommandSourceStack> dispatcher, String command, int level) {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(level)
                        )
                        .then(
                                argument("fov", DoubleArgumentType.doubleArg())
                                        .executes(
                                                context -> {
                                                    Player player = context.getSource().getPlayer();

                                                    Vec3 pos = player.getEyePosition();

                                                    pathData.setStartPos(pos);
                                                    pathData.setStartRot(new Vec2(player.getYRot(), player.getXRot()));
                                                    pathData.setStartFov(context.getArgument("fov", Double.class));

                                                    return Command.SINGLE_SUCCESS;
                                                }
                                        )
                                        .then(
                                                argument("facing", Vec2Argument.vec2())
                                                        .executes(
                                                                context -> {
                                                                    Player player = context.getSource().getPlayer();

                                                                    Vec3 pos = player.getEyePosition();
                                                                    WorldCoordinates rot = context.getArgument("facing", WorldCoordinates.class);
                                                                    Vec2 rotVec = rot.getRotation(context.getSource());

                                                                    pathData.setStartPos(pos);
                                                                    pathData.setStartRot(new Vec2(rotVec.y, rotVec.x));
                                                                    pathData.setStartFov(context.getArgument("fov", Double.class));

                                                                    return Command.SINGLE_SUCCESS;
                                                                }
                                                        )
                                        )
                        )
                        .executes(
                                context -> {
                                    context.getSource().sendFailure(Component.nullToEmpty("Define the fov!"));

                                    return Command.SINGLE_SUCCESS;
                                }
                        )
        );
    }

    public static void addCutsceneEndPointCommand(CommandDispatcher<CommandSourceStack> dispatcher, String command, int level) {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(level)
                        )
                        .then(
                                argument("fov", DoubleArgumentType.doubleArg())
                                        .executes(
                                                context -> {
                                                    Player player = context.getSource().getPlayer();

                                                    Vec3 pos = player.getEyePosition();

                                                    pathData.setEndPos(pos);
                                                    pathData.setEndRot(new Vec2(player.getYRot(), player.getXRot()));
                                                    pathData.setEndFov(context.getArgument("fov", Double.class));

                                                    return Command.SINGLE_SUCCESS;
                                                }
                                        )
                                        .then(
                                                argument("facing", Vec2Argument.vec2())
                                                        .executes(
                                                                context -> {
                                                                    Player player = context.getSource().getPlayer();

                                                                    Vec3 pos = player.getEyePosition();
                                                                    WorldCoordinates rot = context.getArgument("facing", WorldCoordinates.class);
                                                                    Vec2 rotVec = rot.getRotation(context.getSource());

                                                                    pathData.setEndPos(pos);
                                                                    pathData.setEndRot(new Vec2(rotVec.y, rotVec.x));
                                                                    pathData.setEndFov(context.getArgument("fov", Double.class));

                                                                    return Command.SINGLE_SUCCESS;
                                                                }
                                                        )
                                        )
                        )
                        .executes(
                                context -> {
                                    context.getSource().sendFailure(Component.nullToEmpty("Define the fov!"));

                                    return Command.SINGLE_SUCCESS;
                                }
                        )
        );
    }

    public static void generateCutsceneCodeCommand(CommandDispatcher<CommandSourceStack> dispatcher, String command, int level) {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(level)
                        )
                        .then(
                                argument("seconds", IntegerArgumentType.integer())
                                        .executes(
                                                context -> {
                                                    pathData.setDuration(context.getArgument("seconds", Integer.class));

                                                    return Command.SINGLE_SUCCESS;
                                                }
                                        )
                                        .then(
                                                argument("easing", StringArgumentType.string())
                                                        .executes(
                                                                context -> {
                                                                    String easingName = context.getArgument("easing", String.class);
                                                                    Easing easing = new Easing(easingName);

                                                                    pathData.setDuration(context.getArgument("seconds", Integer.class));
                                                                    pathData.setEasing(easing);

                                                                    log(context);

                                                                    return Command.SINGLE_SUCCESS;
                                                                }
                                                        )
                                                        .then(
                                                                argument("bezierPoint1", BlockPosArgument.blockPos())
                                                                        .executes(
                                                                                context -> {
                                                                                    WorldCoordinates point1Context = context.getArgument("bezierPoint1", WorldCoordinates.class);
                                                                                    Vec3 point1 = point1Context.getPosition(context.getSource());

                                                                                    String easingName = context.getArgument("easing", String.class);
                                                                                    Easing easing = new Easing(easingName);

                                                                                    pathData.setDuration(context.getArgument("seconds", Integer.class));
                                                                                    pathData.setEasing(easing);
                                                                                    pathData.setBezier(new Vec3(point1.x, point1.y, point1.z));

                                                                                    log(context);

                                                                                    return Command.SINGLE_SUCCESS;
                                                                                }
                                                                        )
                                                                        .then(
                                                                                argument("bezierPoint2", BlockPosArgument.blockPos())
                                                                                        .executes(
                                                                                                context -> {
                                                                                                    WorldCoordinates point1Context = context.getArgument("bezierPoint1", WorldCoordinates.class);
                                                                                                    Vec3 point1 = point1Context.getPosition(context.getSource());

                                                                                                    WorldCoordinates point2Context = context.getArgument("bezierPoint2", WorldCoordinates.class);
                                                                                                    Vec3 point2 = point2Context.getPosition(context.getSource());

                                                                                                    String easingName = context.getArgument("easing", String.class);
                                                                                                    Easing easing = new Easing(easingName);

                                                                                                    pathData.setDuration(context.getArgument("seconds", Integer.class));
                                                                                                    pathData.setEasing(easing);
                                                                                                    pathData.setBezier(new Vec3(point1.x, point1.y, point1.z), new Vec3(point2.x, point2.y, point2.z));

                                                                                                    log(context);

                                                                                                    return Command.SINGLE_SUCCESS;
                                                                                                }
                                                                                        )
                                                                        )
                                                        )
                                        )
                        )
                        .executes(
                                context -> {
                                    context.getSource().sendFailure(Component.nullToEmpty("Define the data!"));

                                    return Command.SINGLE_SUCCESS;
                                }
                        )
        );
    }

    public static void log(CommandContext<CommandSourceStack> context) {
        RestApi.Log(pathData.generateJavaCode());
        context.getSource().sendSystemMessage(Component.nullToEmpty(pathData.generateJavaCode()));
    }

    public static void addCutscenePlayCommand(CommandDispatcher<CommandSourceStack> dispatcher, String command, int level)
    {
        dispatcher.register(
                Commands.literal(command)
                        .requires(
                                source -> source.hasPermission(level)
                        )
                        .executes(
                                context -> {
                                    Player player = context.getSource().getPlayer();

                                    PacketManager.playCutscene(player, -1);

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
                    play_cutscene(dispatcher, "play_cutscene", 4);
                    addCutsceneStartPointCommand(dispatcher, "cutscene_start_point", 4);
                    addCutsceneEndPointCommand(dispatcher, "cutscene_end_point", 4);
                    generateCutsceneCodeCommand(dispatcher, "cutscene_generate", 4);
                    addCutscenePlayCommand(dispatcher, "cutscene_play", 4);
                    addCommand(dispatcher, "setup_intro_scene", 4);
                    addCommand(dispatcher, "open_waiting_screen", 4);
                }
        );

        ClientChatEvent.RECEIVED.register(
                (type, message) -> {
                    if (message.getString().contains("new EasingTransition(")) {
                        RestApi.Log(message.getString());

                        EasingTransition transition = EasingTransitionParser.parseEasingTransition(message.getString());

                        RestApi.Log("CODE:");
                        RestApi.Log(transition.generateJavaCode());

                        pathData = transition;
                    }

                    return CompoundEventResult.pass();
                }
        );
    }

    private interface CommandHandler {
        void handle(CommandContext<CommandSourceStack> context);
    }
}
