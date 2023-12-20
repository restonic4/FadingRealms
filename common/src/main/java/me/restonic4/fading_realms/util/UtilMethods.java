package me.restonic4.fading_realms.util;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class UtilMethods {
    /*public static ItemEntity spawnAtLocation(ItemStack itemStack, float f, Level level, double x, double y, double z) {
        if (itemStack.isEmpty()) {
            return null;
        } else if (level.isClientSide) {
            return null;
        } else {
            ItemEntity itemEntity = new ItemEntity(level, x, y + (double)f, z, itemStack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
            return itemEntity;
        }
    }*/

    public static double getRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    public static double getRandomDoubleNegative() {
        Random random = new Random();
        return random.nextDouble() * 2 - 1;
    }
}
