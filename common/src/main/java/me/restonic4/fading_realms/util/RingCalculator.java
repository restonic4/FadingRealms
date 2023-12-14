package me.restonic4.fading_realms.util;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RingCalculator {
    public static List<BlockPos> generateRing(int numPositions, int radius) {
        List<BlockPos> positions = new ArrayList<>();
        double angleIncrement = 2 * Math.PI / numPositions;

        for (int i = 0; i < numPositions; i++) {
            double angle = i * angleIncrement;

            int x = (int) (radius * Math.cos(angle));
            int z = (int) (radius * Math.sin(angle));

            BlockPos pos = new BlockPos(x, 0, z);

            positions.add(pos);
        }

        return positions;
    }
}
