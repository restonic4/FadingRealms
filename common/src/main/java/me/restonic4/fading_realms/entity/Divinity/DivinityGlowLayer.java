package me.restonic4.fading_realms.entity.Divinity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class DivinityGlowLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entities/divinity_glow.png");

    public DivinityGlowLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLightIn, T entity, float f, float g, float h, float j, float k, float l) {
        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(RenderType.eyes(TEXTURE));
        this.getParentModel().renderToBuffer(poseStack, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords((LivingEntity) entity, 0F), 1F, 1F, 1F, 0.2F);
    }
}
