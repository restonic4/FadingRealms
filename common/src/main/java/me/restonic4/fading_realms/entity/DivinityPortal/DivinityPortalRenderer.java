package me.restonic4.fading_realms.entity.DivinityPortal;

import com.mojang.blaze3d.vertex.PoseStack;
import me.restonic4.fading_realms.gui.ScreenManager;
import me.restonic4.fading_realms.gui.WhiteScreen;
import me.restonic4.fading_realms.sound.SoundsRegistry;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import me.restonic4.restapi.RestApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

@Environment(value= EnvType.CLIENT)
public class DivinityPortalRenderer<Type extends DivinityPortal> extends MobRenderer<Type, DivinityPortalModel<Type>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entities/divinity_portal.png");
    private float scale;
    private boolean ended = false;
    private long startTime = -1;

    public DivinityPortalRenderer(EntityRendererProvider.Context context, float scale) {
        super(context, new DivinityPortalModel<>(context.bakeLayer(DivinityPortalModel.LAYER_LOCATION)), 0.5f);
        super.addLayer(new DivinityPortalGlowLayer(this));
        this.scale = scale;
    }

    protected void scale(PoseStack poseStack) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    protected void scale(PoseStack poseStack, float f) {
        scale = f;
        this.scale(poseStack);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }

    @Override
    public void render(Type mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        this.scale(poseStack);

        if (startTime == -1) {
            this.startTime = System.currentTimeMillis();
            Minecraft.getInstance().player.playSound(SoundsRegistry.DivinityPortalIntro.get().get(), 1, 1);
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if(!ended && elapsedTime >= 7f * 1000) {
            ended = true;
            ScreenManager.openScreen(new WhiteScreen());
            PacketManager.spawnDivinityPart2(Minecraft.getInstance().player);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
