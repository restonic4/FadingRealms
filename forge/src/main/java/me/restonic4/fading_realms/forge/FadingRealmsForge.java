package me.restonic4.fading_realms.forge;

import dev.architectury.platform.forge.EventBuses;
import me.restonic4.fading_realms.FadingRealms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FadingRealms.MOD_ID)
public class FadingRealmsForge {
    public FadingRealmsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(FadingRealms.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FadingRealms.init();
    }
}