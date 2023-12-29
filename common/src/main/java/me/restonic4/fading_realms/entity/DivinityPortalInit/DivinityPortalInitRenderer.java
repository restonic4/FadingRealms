package me.restonic4.fading_realms.entity.DivinityPortalInit;

import com.mojang.blaze3d.vertex.PoseStack;
import me.restonic4.fading_realms.gui.ScreenManager;
import me.restonic4.fading_realms.gui.WhiteScreen;
import me.restonic4.fading_realms.sound.SoundsRegistry;
import me.restonic4.fading_realms.util.Camera.PacketManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

@Environment(value= EnvType.CLIENT)
public class DivinityPortalInitRenderer<Type extends DivinityPortalInit> extends MobRenderer<Type, DivinityPortalInitModel<Type>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entities/divinity_portal.png");
    private float scale;
    private long startTime = -1;

    public DivinityPortalInitRenderer(EntityRendererProvider.Context context, float scale) {
        super(context, new DivinityPortalInitModel<>(context.bakeLayer(DivinityPortalInitModel.LAYER_LOCATION)), 0.5f);
        super.addLayer(new DivinityPortalInitGlowLayer(this));
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
        }

        if (System.currentTimeMillis() - startTime >= 1750) {
            this.startTime = -1;
            Minecraft.getInstance().player.playSound(SoundsRegistry.DivinityPortalInitAmbient.get().get(), 1, 1);
        }

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
