package me.restonic4.fading_realms.fabric;

import me.restonic4.fading_realms.FadingRealms;
import net.fabricmc.api.ModInitializer;

public class FadingRealmsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FadingRealms.init();
    }
}