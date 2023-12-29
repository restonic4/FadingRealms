package me.restonic4.fading_realms.entity.DivinityPortalInit;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class DivinityPortalInitAnimation {
    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(1.75f).looping()
            .addAnimation("ring1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-360f, 0f, 360f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(-520f, 0f, 520f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.4167667f, KeyframeAnimations.degreeVec(-452f, 0f, 452f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, KeyframeAnimations.degreeVec(-600f, 0f, 600f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.degreeVec(-720f, 0f, 720f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("ring1",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0.1f, 0.1f, 0.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(0.3f, 0.1f, 0.3f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.3433333f, KeyframeAnimations.scaleVec(0.23f, 0.1f, 0.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.4167667f, KeyframeAnimations.scaleVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5416767f, KeyframeAnimations.scaleVec(0.17f, 0.1f, 0.17f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.75f, KeyframeAnimations.scaleVec(0.1f, 0.1f, 0.1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("center",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(-274.29f, -274.29f, 274.29f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.4167667f, KeyframeAnimations.degreeVec(-191.43f, -191.43f, 191.43f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, KeyframeAnimations.degreeVec(-308.57f, -308.57f, 308.57f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.degreeVec(-360f, -360f, 360f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("center",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(0.5f, 0.5f, 0.5f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.3433333f, KeyframeAnimations.scaleVec(0.69f, 0.69f, 0.69f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.4167667f, KeyframeAnimations.scaleVec(2f, 2f, 2f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5416767f, KeyframeAnimations.scaleVec(0.81f, 0.81f, 0.81f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.75f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
}
