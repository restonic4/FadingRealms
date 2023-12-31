package me.restonic4.fading_realms.entity.Divinity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

@Environment(value= EnvType.CLIENT)
public class DivinityRenderer<Type extends Divinity> extends MobRenderer<Type, DivinityModel<Type>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entities/divinity.png");
    private float scale;

    public DivinityRenderer(EntityRendererProvider.Context context, float scale) {
        super(context, new DivinityModel<>(context.bakeLayer(DivinityModel.LAYER_LOCATION)), 0.5f);
        super.addLayer(new DivinityEyesLayer(this));
        super.addLayer(new DivinityGlowLayer(this));
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

        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
