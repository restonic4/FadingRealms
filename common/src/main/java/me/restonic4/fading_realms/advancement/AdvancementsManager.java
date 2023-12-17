package me.restonic4.fading_realms.advancement;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import me.restonic4.fading_realms.advancement.custom.HomeSweetHome;
import me.restonic4.restapi.RestApi;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class AdvancementsManager {
    public static HomeSweetHome home_sweet_home = new HomeSweetHome("home_sweet_home");
    public static CoolAdvancement welcome_to_harmony = new CoolAdvancement("welcome_to_harmony");
    public static CoolAdvancement center_of_harmony = new CoolAdvancement("center_of_harmony");
    public static CoolAdvancement waystone = new CoolAdvancement("waystone");

    public static void init() {
        home_sweet_home.register();
        welcome_to_harmony.register();
        center_of_harmony.register();
        waystone.register();

        registerWaystoneInteraction();
    }

    static void registerWaystoneInteraction() {
        InteractionEvent.RIGHT_CLICK_BLOCK.register(
                (player, hand, pos, face) -> {
                    Level level = player.level();

                    if (!level.isClientSide()) {
                        Block block = level.getBlockState(pos).getBlock();

                        if (block.toString().contains("waystone")) {
                            AdvancementsManager.waystone.grant((ServerPlayer) player);
                        }
                    }

                    return EventResult.pass();
                }
        );
    }
}
