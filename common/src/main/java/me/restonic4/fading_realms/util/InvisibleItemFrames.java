package me.restonic4.fading_realms.util;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import me.restonic4.restapi.RestApi;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class InvisibleItemFrames {
    public static void register() {
        PlayerEvent.ATTACK_ENTITY.register(
                (player, level, entity, interactionHand, entityHitResult) -> {
                    if (entity.getType() == EntityType.ITEM_FRAME) {
                        ItemFrame frame = (ItemFrame) entity;

                        if (entity.isInvisible() && frame.getItem().isEmpty()) {
                            RestApi.Log("Invisible item frame, deleting", MOD_ID);

                            entity.kill();

                            return EventResult.interrupt(false);
                        }
                    }

                    return EventResult.pass();
                }
        );
    }
}
