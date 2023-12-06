package me.restonic4.fading_realms.util;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class POIManager {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(MOD_ID, Registries.POINT_OF_INTEREST_TYPE);

    public static final RegistrySupplier<PoiType> campfire = POI_TYPES.register("campfire_poi",
            () -> new PoiType(
                    ImmutableSet.copyOf(Blocks.CAMPFIRE.getStateDefinition().getPossibleStates()),
                    1,
                    1
            ));


    public static void register() {
        POI_TYPES.register();
    }
}
