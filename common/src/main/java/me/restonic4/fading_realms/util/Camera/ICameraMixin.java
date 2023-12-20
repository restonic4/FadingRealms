package me.restonic4.fading_realms.util.Camera;

import me.restonic4.fading_realms.util.Camera.Cutscene.Cutscene;
import me.restonic4.fading_realms.util.Camera.Cutscene.EasingTransition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;

public interface ICameraMixin {
    void setRot(float f, float g);
    void setPos(double d, double e, double f);

    void setDetached(boolean value);

    void freeze(boolean value);

    boolean isFrozen(boolean value);

    void shake(float value);

    float getShakingIntensity();

    void forceDetach(boolean value);

    boolean isForcedDetached();

    void playCutscene(Cutscene cutscene);

    Cutscene getCutscenePlaying();

    void forceFov(double fov);

    double getForcedFov();
}
