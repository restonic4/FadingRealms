package me.restonic4.fading_realms.mixin;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

public interface ICameraMixin {
    void setRot(float f, float g);
    void setPos(double d, double e, double f);
}
