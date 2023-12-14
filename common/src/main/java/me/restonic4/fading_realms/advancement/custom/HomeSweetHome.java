package me.restonic4.fading_realms.advancement.custom;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import me.restonic4.fading_realms.advancement.CoolAdvancement;
import me.restonic4.fading_realms.advancement.ModAdvancementTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.atomic.AtomicReference;

public class HomeSweetHome extends CoolAdvancement {
    public HomeSweetHome(String id) {
        super(id);
    }

    public void register() {
        super.registerCriteriaTriggers();

        BlockEvent.PLACE.register(
                (level, blockPos, blockState, entity) -> {
                    if (!level.isClientSide() && entity != null && entity.getType() == EntityType.PLAYER) {
                        Block placedBlock = blockState.getBlock();
                        ServerPlayer serverPlayer = (ServerPlayer) entity;

                        boolean lookingForTable = isLookingForTable(placedBlock, blockState);
                        boolean shouldGrantAdvancement;

                        if (lookingForTable) {
                            shouldGrantAdvancement = isTableClose(level, blockPos, placedBlock, blockState, entity);
                        }
                        else {
                            shouldGrantAdvancement = isBedClose(level, blockPos, placedBlock, blockState, entity);
                        }

                        if (shouldGrantAdvancement) {
                            Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(super.advancement_location);

                            serverPlayer.getAdvancements().award(advancement, super.advancement_id);
                        }
                    }

                    return EventResult.pass();
                }
        );
    }

    public static boolean isLookingForTable(Block placedBlock, BlockState blockState) {
        boolean lookingForTable = false;

        if (placedBlock == Blocks.CRAFTING_TABLE) {
            lookingForTable = false;
        }
        else {
            AtomicReference<Boolean> isBed = new AtomicReference<>(false);

            blockState.getTags().forEach(tag -> {
                if (tag == BlockTags.BEDS) {
                    isBed.set(true);
                }
            });

            if (isBed.get()) {
                lookingForTable = true;
            }
        }

        return lookingForTable;
    }

    public static boolean isTableClose(Level level, BlockPos blockPos, Block placedBlock, BlockState blockState, Entity entity) {
        boolean isClose = false;

        for (int xOffset = -2; xOffset <= 2; xOffset++) {
            for (int zOffset = -2; zOffset <= 2; zOffset++) {
                int newX = blockPos.getX() + xOffset;
                int newY = blockPos.getY();
                int newZ = blockPos.getZ() + zOffset;

                BlockPos newPos = new BlockPos(newX, newY, newZ);

               if (level.getBlockState(newPos).getBlock() == Blocks.CRAFTING_TABLE) {
                   isClose = true;
               }
            }
        }

        return isClose;
    }

    public static boolean isBedClose(Level level, BlockPos blockPos, Block placedBlock, BlockState blockState, Entity entity) {
        boolean isClose = false;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                int newX = blockPos.getX() + xOffset;
                int newY = blockPos.getY();
                int newZ = blockPos.getZ() + zOffset;

                BlockPos newPos = new BlockPos(newX, newY, newZ);

                AtomicReference<Boolean> isBedNear = new AtomicReference<>(false);

                level.getBlockState(newPos).getTags().forEach(tag -> {
                    if (tag == BlockTags.BEDS) {
                        isBedNear.set(true);
                    }
                });

                if (isBedNear.get()) {
                    isClose = true;
                }
            }
        }

        return isClose;
    }
}
