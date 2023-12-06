package me.restonic4.fading_realms.dimension;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.world.item.Items;

public class EndDisable {
    public static void init() {
        InteractionEvent.RIGHT_CLICK_BLOCK.register(
                (player, interactionHand, blockPos, direction) -> {
                    if (player.getItemInHand(interactionHand).getItem() == Items.ENDER_EYE) {
                        return EventResult.interruptTrue();
                    }

                    return EventResult.pass();
                }
        );
    }
}
