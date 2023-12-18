package me.restonic4.fading_realms.mixin;

import me.restonic4.restapi.RestApi;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow protected abstract void setRotation(float f, float g);
    @Shadow protected abstract void setPosition(double d, double e, double f);

    @Inject(method = "setRotation", at = @At("RETURN"))
    public void setRot(float f, float g, CallbackInfo ci) {
        RestApi.Log("Mixin cam rot");
        this.setRotation(f, g);
    }

    @Inject(method = "setPosition(DDD)V", at = @At("RETURN"))
    public void setPos(double d, double e, double f, CallbackInfo ci) {
        RestApi.Log("Mixin cam pos");
        this.setPosition(d, e, f);
    }
}
