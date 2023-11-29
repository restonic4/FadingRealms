package me.restonic4.fading_realms.util;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class InvisibleItemFrames {
    public static void register() {
        PlayerEvent.ATTACK_ENTITY.register(
                (player, level, entity, interactionHand, entityHitResult) -> {
                    if (entity.getType() == EntityType.ITEM_FRAME) {
                        ItemFrame frame = (ItemFrame) entity;

                        //Server side
                        if (frame.isInvisible() && !player.level().isClientSide()) {
                            if (frame.getItem().isEmpty()) {
                                RestApi.Log("Empty invisible item frame, deleting", MOD_ID);

                                frame.kill();

                                return EventResult.interrupt(false);
                            }
                            else {
                                RestApi.Log("Invisible item frame with an item, deleting", MOD_ID);

                                frame.dropItem(frame);

                                frame.kill();
                            }
                        }
                    }

                    return EventResult.pass();
                }
        );

        AtomicBoolean isZDown = new AtomicBoolean(false);

        ClientRawInputEvent.KEY_PRESSED.register(
                (client, keyCode, scanCode, action, modifiers) -> {
                    // keycode: 44 = z // https://minecraft.wiki/w/Key_codes
                    //action = 0 stop, 1 single, 2 multiple secs

                    if (scanCode == 44) {
                        isZDown.set(action == 1 || action == 2);

                        RestApi.Log("Key: " + keyCode + ", Scan: " + scanCode + ", Action: " + action + ", Modifiers: " + modifiers, MOD_ID);
                    }

                    return EventResult.pass();
                }
        );

        InteractionEvent.RIGHT_CLICK_BLOCK.register(
                (player, interactionHand, blockPos, direction) -> {
                    if (player.isCrouching() && isZDown.get()) {
                        Item item = player.getMainHandItem().getItem();

                        //Server side
                        if (!player.level().isClientSide() && !player.getMainHandItem().isEmpty()) {
                            RestApi.Log("Creating invisible item frame", MOD_ID);

                            BlockPos fixedPosition = getPosition(direction, blockPos);

                            ItemFrame itemFrame = new ItemFrame(player.level(), fixedPosition, direction);

                            player.level().addFreshEntity(itemFrame);

                            itemFrame.setItem(player.getMainHandItem());

                            itemFrame.setInvisible(true);

                            player.getMainHandItem().setCount(player.getMainHandItem().getCount() - 1);
                        }
                    }

                    return EventResult.pass();
                }
        );
    }

    @NotNull
    private static BlockPos getPosition(Direction direction, BlockPos pos) {
        BlockPos newPos = pos;

        switch (direction) {
            case UP -> newPos = pos.offset(0, 1, 0);
            case DOWN -> newPos = pos.offset(0, -1, 0);
            case NORTH -> newPos = pos.offset(0, 0, -1);
            case SOUTH -> newPos = pos.offset(0, 0, 1);
            case EAST -> newPos = pos.offset(1, 0, 0);
            case WEST -> newPos = pos.offset(-1, 0, 0);
            default -> {}
        }
        return newPos;
    }
}
