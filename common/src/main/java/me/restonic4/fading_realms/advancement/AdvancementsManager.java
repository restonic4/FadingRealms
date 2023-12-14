package me.restonic4.fading_realms.advancement;

import me.restonic4.fading_realms.advancement.custom.HomeSweetHome;

public class AdvancementsManager {
    public static HomeSweetHome home_sweet_home = new HomeSweetHome("home_sweet_home");
    public static CoolAdvancement welcome_to_harmony = new CoolAdvancement("welcome_to_harmony");
    public static CoolAdvancement center_of_harmony = new CoolAdvancement("center_of_harmony");

    public static void init() {
        home_sweet_home.register();
        welcome_to_harmony.register();
        center_of_harmony.register();
    }
}
