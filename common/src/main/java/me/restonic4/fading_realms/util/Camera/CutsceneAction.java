package me.restonic4.fading_realms.util.Camera;

import net.minecraft.client.player.LocalPlayer;

@FunctionalInterface
public interface CutsceneAction {
    void execute(LocalPlayer localPlayer);
}
