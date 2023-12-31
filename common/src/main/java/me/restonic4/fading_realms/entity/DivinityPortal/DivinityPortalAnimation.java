package me.restonic4.fading_realms.entity.DivinityPortal;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class DivinityPortalAnimation {
    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(7.291677f)
            .addAnimation("ring1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-360f, 0f, 360f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(-720f, 0f, 720f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.degreeVec(-1080f, 0f, 1080f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.degreeVec(-1440f, 0f, 1440f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.degreeVec(-1800f, 0f, 1800f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.degreeVec(-2160f, 0f, 2160f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.degreeVec(-2520f, 0f, 2520f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("ring1",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(2f, 1f, 2f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.125f, KeyframeAnimations.scaleVec(3f, 1f, 3f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("ring2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-360f, 0f, 360f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(360f, 0f, -360f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.degreeVec(720f, 0f, -720f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.degreeVec(1080f, 0f, -1080f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.degreeVec(1440f, 0f, -1440f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.degreeVec(1800f, 0f, -1800f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.degreeVec(2160f, 0f, -2160f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("ring2",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(3f, 1f, 3f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.125f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("ring3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(2f, KeyframeAnimations.degreeVec(-720f, 720f, 720f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.degreeVec(-2520f, 2520f, 2520f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("ring3",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(2f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.125f, KeyframeAnimations.scaleVec(2f, 1f, 2f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("ring4",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(3f, KeyframeAnimations.degreeVec(-1080f, -1080f, 1080f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.degreeVec(-2520f, -2520f, 2520f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.291677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("ring4",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(3f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(0.5f, 1f, 0.5f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.125f, KeyframeAnimations.scaleVec(0.5f, 1f, 0.5f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.25f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("center",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.degreeVec(2000f, 2000f, -2000f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.5f, KeyframeAnimations.degreeVec(2000f, 2070f, -2000f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.875f, KeyframeAnimations.degreeVec(1996.46f, 2116.65f, -2086.82f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.degreeVec(2000f, 2070f, -2000f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("center",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.5f, KeyframeAnimations.scaleVec(2.77f, 2.77f, 2.77f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.291677f, KeyframeAnimations.scaleVec(5f, 5f, 5f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
}
