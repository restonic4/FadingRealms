package me.restonic4.fading_realms.sound;

import me.restonic4.restapi.holder.RestSound;
import me.restonic4.restapi.sound.SoundRegistry;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class SoundsRegistry {
    public static final RestSound DivinityAmbient = SoundRegistry.RegisterSound(MOD_ID, "divinity_ring_wind_ambient");
    public static final RestSound DivinityPortalIntro = SoundRegistry.RegisterSound(MOD_ID, "divinity_portal_intro");
    public static final RestSound DivinityPortalInitAmbient = SoundRegistry.RegisterSound(MOD_ID, "divinity_portal_init_ambient");

    public static void register() {
        SoundRegistry.Register(MOD_ID);
    }
}
