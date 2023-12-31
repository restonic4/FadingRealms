package me.restonic4.fading_realms.forge;

import dev.architectury.platform.forge.EventBuses;
import me.restonic4.fading_realms.FadingRealms;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FadingRealms.MOD_ID)
public class FadingRealmsForge {
    public FadingRealmsForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EventBuses.registerModEventBus(FadingRealms.MOD_ID, eventBus);
        FadingRealms.init();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onPortalIgnition(BlockEvent.PortalSpawnEvent event) {
            event.setCanceled(true);
        }
    }
}