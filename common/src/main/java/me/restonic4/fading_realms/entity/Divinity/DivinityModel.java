package me.restonic4.fading_realms.entity.Divinity;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class DivinityModel<T extends Divinity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "divinity"), "main");
	private final ModelPart root;

	public DivinityModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(26, 45).addBox(-4.0F, -5.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(46, 2).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(24, 8).addBox(-3.0F, -5.0F, 3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 41).addBox(3.0F, -5.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 1.0F));

		PartDefinition leftArm = root.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 25).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(51, 31).addBox(-0.5F, 6.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(47, 9).addBox(-0.5F, 4.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, 1.0F));

		PartDefinition rightArm = root.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(36, 37).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(11, 52).addBox(-4.5F, 6.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(47, 48).addBox(-4.5F, 4.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -22.0F, 1.0F));

		PartDefinition leftLeg = root.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 1.0F));

		PartDefinition rightLeg = root.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 41).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 14).addBox(-4.5F, -3.0F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 2).addBox(-4.5F, 4.0F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 1.0F));

		PartDefinition ring1 = body.addOrReplaceChild("ring1", CubeListBuilder.create().texOffs(24, 14).addBox(-12.0F, -0.5F, -18.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, 3).addBox(-15.0F, -0.5F, -17.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(34, 2).addBox(-16.0F, -0.5F, -16.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 2).addBox(-17.0F, -0.5F, -15.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 2).addBox(-17.0F, -0.5F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r1 = ring1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 16).addBox(-11.0F, -1.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 16).addBox(-12.0F, -1.0F, -14.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 18).addBox(-13.0F, -1.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 16).addBox(-13.0F, -1.0F, -12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.5F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = ring1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 16).addBox(-17.0F, -1.0F, -10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(30, 3).addBox(-17.0F, -1.0F, -11.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 17).addBox(-16.0F, -1.0F, -12.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 3).addBox(-15.0F, -1.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 14).addBox(-12.0F, -1.0F, -14.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 4.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r3 = ring1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 14).addBox(-8.0F, -1.0F, 17.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r4 = ring1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(33, 4).addBox(-11.0F, -1.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 18).addBox(-12.0F, -1.0F, -14.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 14).addBox(-13.0F, -1.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 4).addBox(-13.0F, -1.0F, -12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.5F, -4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = ring1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 14).addBox(-8.0F, -1.0F, 17.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition ring2 = body.addOrReplaceChild("ring2", CubeListBuilder.create().texOffs(24, 14).addBox(-12.0F, -0.5F, -18.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, 3).addBox(-15.0F, -0.5F, -17.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(34, 2).addBox(-16.0F, -0.5F, -16.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 2).addBox(-17.0F, -0.5F, -15.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 2).addBox(-17.0F, -0.5F, -14.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r6 = ring2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 16).addBox(-11.0F, -1.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 16).addBox(-12.0F, -1.0F, -14.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 18).addBox(-13.0F, -1.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 16).addBox(-13.0F, -1.0F, -12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.5F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r7 = ring2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(26, 16).addBox(-17.0F, -1.0F, -10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(30, 3).addBox(-17.0F, -1.0F, -11.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 17).addBox(-16.0F, -1.0F, -12.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 3).addBox(-15.0F, -1.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 14).addBox(-12.0F, -1.0F, -14.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 4.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r8 = ring2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 14).addBox(-8.0F, -1.0F, 17.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r9 = ring2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(33, 4).addBox(-11.0F, -1.0F, -15.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 18).addBox(-12.0F, -1.0F, -14.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 14).addBox(-13.0F, -1.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 4).addBox(-13.0F, -1.0F, -12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.5F, -4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = ring2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 14).addBox(-8.0F, -1.0F, 17.0F, 24.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -4.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.idleAnimationState, DivinityAnimation.idle, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}