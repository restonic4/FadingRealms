package me.restonic4.fading_realms.mixin;

import com.mojang.serialization.Codec;
import me.restonic4.fading_realms.util.Camera.ICameraMixin;
import me.restonic4.fading_realms.util.IGameRendererMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GameRenderer.class, priority = 1001)
public abstract class GameRendererMixin implements IGameRendererMixin {
    @Final @Shadow final Minecraft minecraft = Minecraft.getInstance();
    @Shadow private float fov;
    @Shadow private float oldFov;
    @Shadow private boolean panoramicMode;

    /**
     * @author restonic4
     * @reason cutscenes
     */
    @Overwrite
    private double getFov(Camera camera, float f, boolean bl) {
        ICameraMixin cam = (ICameraMixin) camera;

        if (cam.getForcedFov() >= 0) {
            return cam.getForcedFov();
        }

        FogType fogType;
        if (this.panoramicMode) {
            return 90.0;
        }
        double d = 70.0;
        if (bl) {
            d = this.minecraft.options.fov().get().intValue();
            d *= (double) Mth.lerp(f, this.oldFov, this.fov);
        }
        if (camera.getEntity() instanceof LivingEntity && ((LivingEntity)camera.getEntity()).isDeadOrDying()) {
            float g = Math.min((float)((LivingEntity)camera.getEntity()).deathTime + f, 20.0f);
            d /= (double)((1.0f - 500.0f / (g + 500.0f)) * 2.0f + 1.0f);
        }
        if ((fogType = camera.getFluidInCamera()) == FogType.LAVA || fogType == FogType.WATER) {
            d *= Mth.lerp(this.minecraft.options.fovEffectScale().get(), 1.0, 0.8571428656578064);
        }
        return d;
    }
}
