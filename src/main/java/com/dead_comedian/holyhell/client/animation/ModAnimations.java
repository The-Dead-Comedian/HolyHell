package com.dead_comedian.holyhell.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ModAnimations {

//    //////////
//    //DEVOUT//
//    //////////
//
//
//    public static final AnimationDefinition DEVOUT_WALK = AnimationDefinition.Builder.withLength(1.5f).looping()
//            .addAnimation("hood",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, -2f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(0f, -2f, -1f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, -2f, -2f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(0f, 0f, 1f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(-2.5f, -2.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(-2.5f, -2.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -90f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, -90f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(0f, 0f, 1f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(2.5f, -2.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(2.5f, -2.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 90f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 90f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("robe",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, -1f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("robe",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(7f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM))).build();
//    public static final AnimationDefinition DEVOUT_IDLE = AnimationDefinition.Builder.withLength(1.5f).looping()
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM))).build();
//    public static final AnimationDefinition DEVOUT_CAST = AnimationDefinition.Builder.withLength(1.5f).looping()
//            .addAnimation("hood",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1.4167667f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("hood",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1.4167667f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, 145f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(0f, 0f, 125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.6766666f, KeyframeAnimations.degreeVec(-10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, 0f, 125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(-10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.0834333f, KeyframeAnimations.degreeVec(0f, 0f, 145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(-10f, 0f, 135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.4167667f, KeyframeAnimations.degreeVec(0f, 0f, 145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("knarifey",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 10f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, -145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, -145f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(0f, 0f, -125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.6766666f, KeyframeAnimations.degreeVec(-10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.8343334f, KeyframeAnimations.degreeVec(10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, 0f, -125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(-10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.0834333f, KeyframeAnimations.degreeVec(0f, 0f, -145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, -125f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(-10f, 0f, -135f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.4167667f, KeyframeAnimations.degreeVec(0f, 0f, -145f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM))).build();
//    public static final AnimationDefinition DEVOUT_PUSH = AnimationDefinition.Builder.withLength(1f).looping()
//            .addAnimation("body",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(0f, -20f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(0f, -5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(27f, -8f, 15f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-57.5f, 17.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(-90.79f, 4.85f, 14.05f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(-92f, 0f, 12f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 2.75f, -2.65f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(0f, 1f, -2f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 1.98f, -1.98f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-117.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(-117.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("hood",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, 7.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("knarifey",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 9f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR))).build();
//    public static final AnimationDefinition DEVOUT_STAB = AnimationDefinition.Builder.withLength(1f).looping()
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.posVec(-0.36f, -0.05f, 1.37f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.posVec(0.42f, -1.23f, -4.83f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.posVec(0.42f, -1.23f, -4.83f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(-63f, 45f, -26f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(-63f, 45f, -26f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(-48.79f, -14.31f, 6.73f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(-86.62f, -7.02f, 8.52f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(-86.62f, -7.02f, 8.52f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.posVec(0f, 2f, 1f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, 2f, 1f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(-65f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(-65f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(-84.51f, 2.98f, -6.4f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(-8.47f, 7.06f, 5.46f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(-8.47f, 7.06f, 5.46f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("body",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("body",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 17.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(0f, 17.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, -5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(0f, -12.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, -12.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("hood",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(0f, 5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, 5f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("knarifey",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(2.49f, 0.22f, -5f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(2.49f, 0.22f, -5f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(34.65f, -6.39f, -19.13f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(36.37f, -4.64f, -17.51f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(17.83f, -7.8f, -7.96f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.posVec(0f, 1f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_elbow",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 7.5f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, 7.5f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(-35f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM))).build();

    //////////
    //CHERUB//
    //////////

    public static final AnimationDefinition CHERUB_FLY = AnimationDefinition.Builder.withLength(1.5834333f).looping()
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.2916767f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5834334f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.0834333f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.375f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(6f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(6f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_wind",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.posVec(1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.posVec(1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_wind",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(21f, -30f, -13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4583433f, KeyframeAnimations.degreeVec(11f, 28f, 27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(21f, -30f, -13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(11f, 28f, 27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_wind",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.posVec(-1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.posVec(-1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_wind",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(21f, 30f, 13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4583433f, KeyframeAnimations.degreeVec(11f, -28f, -27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(21f, 30f, 13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(11f, -28f, -27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("mid",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(-3f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(3f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.0416767f, KeyframeAnimations.degreeVec(-3f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bottom",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(-6f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.0416767f, KeyframeAnimations.degreeVec(-6f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5834333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition CHERUB_IDLE = AnimationDefinition.Builder.withLength(1.25f).looping()
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.9167666f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4583433f, KeyframeAnimations.degreeVec(6f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_wind",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_wind",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.375f, KeyframeAnimations.degreeVec(21f, -30f, -13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(11f, 28f, 27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_wind",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(-1f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_wind",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.375f, KeyframeAnimations.degreeVec(21f, 30f, 13f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(11f, -28f, -27f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("mid",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.6766666f, KeyframeAnimations.degreeVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(2f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bottom",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(-7f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.6766666f, KeyframeAnimations.degreeVec(4f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();


    //////////
    //SPIRIT//
    //////////

    public static final AnimationDefinition SPIRIT_WALK = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_hand",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_hand",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_hand",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_hand",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, -0.5f, -2.5f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -0.5f, -2.5f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 1f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition SPIRIT = AnimationDefinition.Builder.withLength(0.7083434f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, -10f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_hand",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.16766666f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(0f, 0f, -3f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -3f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_hand",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, KeyframeAnimations.degreeVec(-92.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-92.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();

    ///////////
    // BAB 1 //
    ///////////


    public static final AnimationDefinition BABIDLE1 = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.scaleVec(1.1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition BABWALK1 = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.scaleVec(1.2f, 0.9f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();

    ///////////
    // BAB 2 //
    ///////////


    public static final AnimationDefinition BABIDLE2 = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -0.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.scaleVec(1.1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition BABWALK2 = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-10.18f, -14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -26.2f, 1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(10.18f, 14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 26.2f, 1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-9.96f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(-9.96f, 7.64f, -49.57f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(-9.96f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.degreeVec(-7.41f, -6.74f, 42.06f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.2083433f, KeyframeAnimations.degreeVec(-7.41f, -9.53f, 72.25f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(-7.41f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(7.41f, -6.74f, 42.06f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(3.04f, -9.53f, 72.25f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.degreeVec(9.96f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.2083433f, KeyframeAnimations.degreeVec(6.47f, 7.64f, -49.57f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0.5f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 17.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 30f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0.5f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -30f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition BABATTACK2 = AnimationDefinition.Builder.withLength(0.75f)
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 15f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(2f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -105f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(2f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -105f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();

    ///////////
    // BAB 3 //
    ///////////




    public static final AnimationDefinition BABIDLE3 = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -0.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.scaleVec(1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition BABWALK3 = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(-10.18f, -14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -26.2f, 1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(10.18f, 14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 26.2f, 1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1.25f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.2916767f, KeyframeAnimations.posVec(-2.24f, -0.82f, -1.46f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5834334f, KeyframeAnimations.posVec(-1.89f, -0.17f, -0.56f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.posVec(0.91f, -0.19f, -0.6f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.2083433f, KeyframeAnimations.posVec(3.05f, -0.13f, -0.3f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, -1.25f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-9.96f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.29167f, KeyframeAnimations.degreeVec(-9.96f, 7.64f, -49.57f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.58333f, KeyframeAnimations.degreeVec(-9.96f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.degreeVec(-7.41f, -6.74f, 42.06f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.20833f, KeyframeAnimations.degreeVec(-7.41f, -9.53f, 72.25f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(-9.96f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.2916767f, KeyframeAnimations.posVec(1.77f, 2.01f, -0.37f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5834334f, KeyframeAnimations.posVec(1.89f, -1.3f, 0.59f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.posVec(-0.78f, -1.32f, 0.96f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.2083433f, KeyframeAnimations.posVec(-2.87f, -0.79f, 0.48f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.29167f, KeyframeAnimations.degreeVec(7.41f, -6.74f, 42.06f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.58333f, KeyframeAnimations.degreeVec(3.04f, -9.53f, 72.25f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.875f, KeyframeAnimations.degreeVec(9.96f, 0.87f, -4.92f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.20833f, KeyframeAnimations.degreeVec(31.47f, 7.64f, -49.57f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0.5f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 17.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 30f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0.5f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(1f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -17.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -30f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(-0.1f, 0.09f, -0.12f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(-0.29f, 0.03f, 0.35f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(-0.1f, 0.02f, 0.38f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(-0.6f, 0.1f, 0.48f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(-0.32f, 0.14f, -0.21f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(10.18f, 14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 26.2f, -1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-10.18f, -14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, -26.2f, -1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("trail",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.posVec(-0.75f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("trail",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.scaleVec(1.2f, 1f, 0.9f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(0.9f, 1f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("robe",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(10.18f, 14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 26.2f, -1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-10.18f, -14.94f, 1.34f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, -26.2f, -1.23f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("robe",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.scaleVec(1f, 0.8f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.9583434f, KeyframeAnimations.scaleVec(1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.scaleVec(1f, 0.9f, 1.1f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition BABATTACK3 = AnimationDefinition.Builder.withLength(1.25f)
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 15f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.41667f, KeyframeAnimations.posVec(2.98f, 0.2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -105f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.41667f, KeyframeAnimations.posVec(3.96f, 0.4f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_arm",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -105f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(1.93f, 0.52f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -15f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("robe",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(1.69f, 0.45f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("robe",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -15f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();

//    //////////////
//    // PALLADIN //
//    //////////////
//
//    public static final AnimationDefinition PALLADIN_IDLE = AnimationDefinition.Builder.withLength(1.25f).looping()
//            .addAnimation("head",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("torso",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, -2f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9583434f, KeyframeAnimations.posVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("torso",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9583434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM),
//                            new Keyframe(0.9583434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
//                                    AnimationChannel.Interpolations.CATMULLROM))).build();
//    public static final AnimationDefinition PALLADIN_WALK = AnimationDefinition.Builder.withLength(1f).looping()
//            .addAnimation("body",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.2916767f, KeyframeAnimations.posVec(0f, -0.75f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("torso",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -5f, -2.5f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 6.5f, 6f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 5f, 2.5f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(0f, -9f, -6f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -5f, -2.5f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(-9.96f, -0.87f, -4.92f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(17.48f, -0.75f, 2.38f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(-9.96f, -0.87f, -4.92f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("left_leg",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.2916767f, KeyframeAnimations.posVec(0f, 1.58f, 2.17f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, 1f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("left_leg",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(16.67f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("right_leg",
//                    new AnimationChannel(AnimationChannel.Targets.POSITION,
//                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 1f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.7916766f, KeyframeAnimations.posVec(0f, 1.58f, 2.17f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 1f, 1f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("right_leg",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(16.67f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(17.48f, -0.75f, 2.38f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-9.96f, -0.87f, -4.92f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(17.48f, -0.75f, 2.38f),
//                                    AnimationChannel.Interpolations.LINEAR))).build();
//    public static final AnimationDefinition PALLADIN_CAST = AnimationDefinition.Builder.withLength(1f).looping()
//            .addAnimation("left_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(108.66f, 36.02f, 11.23f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(105f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(40f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(37.25f, 15.76f, -19.66f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(108.66f, 36.02f, 11.23f),
//                                    AnimationChannel.Interpolations.LINEAR)))
//            .addAnimation("right_hand",
//                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
//                            new Keyframe(0f, KeyframeAnimations.degreeVec(46.59f, -20.72f, 18.5f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.25f, KeyframeAnimations.degreeVec(50f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(107.5f, 0f, 0f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(114.03f, -42.41f, -16.74f),
//                                    AnimationChannel.Interpolations.LINEAR),
//                            new Keyframe(1f, KeyframeAnimations.degreeVec(46.59f, -20.72f, 18.5f),
//                                    AnimationChannel.Interpolations.LINEAR))).build();


    //////////////
    // KAMIKAZE //
    //////////////



    public static final AnimationDefinition KAMIKAZE_FLY2 = AnimationDefinition.Builder.withLength(1.25f).looping()
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.0416767f, KeyframeAnimations.posVec(0f, 7.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.0416767f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_top",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -24f, -20f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(47.27f, 29.19f, 53.77f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, -20f, -20f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_top",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 24f, 20f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(47.27f, -29.19f, -53.77f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 20f, 20f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("left_bottom",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -35f, 30f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.9583434f, KeyframeAnimations.degreeVec(47.27f, 29.19f, 53.77f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, -35f, 30f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("right_bottom",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 30f, -30f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(47.27f, -29.19f, -53.77f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 30f, -30f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    /////////////
    // HERETIC //
    /////////////


    public static final AnimationDefinition HERETIC_WALK = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("back_right_leg2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.95f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("back_right_leg2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.95f, KeyframeAnimations.degreeVec(-32.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("back_left_leg2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.35f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("back_left_leg2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.35f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("front_right_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.35f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("front_right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.35f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("front_left_leg",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.95f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("front_left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.95f, KeyframeAnimations.degreeVec(-32.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bottom",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.35f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.65f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.95f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.25f, KeyframeAnimations.posVec(0f, -1.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition HERETIC_ATTACK = AnimationDefinition.Builder.withLength(0.75f)
            .addAnimation("bottom",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.posVec(0f, -0.75f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bottom",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(-35f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("back_right_leg2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("back_left_leg2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(80f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("front_right_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("front_left_leg",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();


    ///////////
    // ANGEL //
    ///////////


    public static final AnimationDefinition FLY_IDLE = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.05f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.45f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.05f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("rightwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(60f, 30f, 50f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-20f, 8f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("leftwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(60f, -30f, -50f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(-20f, 8f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye_ball",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition FLY_WALK = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("rightwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-10f, 30f, 10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(8f, -45f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("leftwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-10f, -30f, -10f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(8f, 45f, -1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.posVec(0f, 2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.75f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.6f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.15f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.6f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    public static final AnimationDefinition FLY_ATTACK = AnimationDefinition.Builder.withLength(1.5f).looping()
            .addAnimation("rightwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(30f, 50f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(20f, -40f, 20f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("leftwing",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(30f, -50f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(20f, 40f, -20f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.55f, KeyframeAnimations.posVec(0f, -3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.05f, KeyframeAnimations.posVec(0f, -0.5f, -3f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.45f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.05f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("jaw_eye",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.65f, KeyframeAnimations.degreeVec(32.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(32.5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.2f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("eye_ball",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();


    ////////////
    //REVENANT//
    ////////////

    public static final AnimationDefinition REVENANT_IDLE = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.1213F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.2828F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.8787F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.4929F, -0.3262F, -4.9786F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.8787F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_IDLE_ARMED = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.2828F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-76.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-96.2736F, 55.352F, -93.9203F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_WALK = AnimationDefinition.Builder.withLength(2.0F).looping()
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.55F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5833F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0429F, -7.4713F, -0.6574F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0429F, 7.4713F, -0.6574F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.0429F, -7.4713F, -0.6574F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-14.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9583F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, 1.92F, -2.42F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-14.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 1.92F, -2.42F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_WALK_ARMED = AnimationDefinition.Builder.withLength(1.5F).looping()
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.125F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 37.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-6.67F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.83F, -4.67F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-76.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(-6.67F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 0.83F, -4.67F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-96.2736F, 55.352F, -93.9203F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_ATTACK =  AnimationDefinition.Builder.withLength(1.0F).looping()
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.degreeVec(10.0F, -10.1126F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(10.0F, 34.8874F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 3.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.degreeVec(-34.5654F, 1.6523F, 4.0309F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(6.3259F, -1.2293F, 20.218F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.4929F, -0.3262F, -4.9786F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-7.4929F, -0.3262F, -4.9786F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-57.3584F, 4.8941F, 5.5278F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-90.1191F, 28.3724F, 0.2234F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3333F, KeyframeAnimations.posVec(0.3289F, -0.3426F, 1.9428F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5417F, KeyframeAnimations.posVec(3.2523F, -2.4274F, 2.2606F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("weapon", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_ATTACK_ARMED = AnimationDefinition.Builder.withLength(1.1667F).looping()
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -5.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, -5.0F, -7.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -7.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, -7.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, -2.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-76.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-121.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-121.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-76.6983F, -54.4729F, 77.4689F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(4.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(4.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-2.5F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(-0.6301F, 0.0F, -2.2728F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(-0.6301F, 0.0F, -2.2728F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-17.5391F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.posVec(0.61F, 1.0F, -9.03F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(3.0F, -2.0F, -12.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(3.0F, -2.0F, -12.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, -0.75F, -2.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-96.2736F, 55.352F, -93.9203F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-82.4973F, 32.9719F, -92.3594F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-82.4973F, 32.9719F, -92.3594F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-96.2736F, 55.352F, -93.9203F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(-0.1234F, 1.0F, -0.8257F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(-0.1234F, 1.0F, -0.8257F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, -2.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(66.1493F, 17.6688F, 35.0561F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(66.1493F, 17.6688F, 35.0561F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(-12.088F, -11.7063F, 11.8632F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(-12.088F, -11.7063F, 11.8632F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_CATATONIC = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -16.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 9.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-31.6066F, 7.9937F, 12.7341F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-71.4516F, -19.0377F, -6.246F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.5F, -4.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-31.6066F, -7.9937F, -12.7341F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition REVENANT_CATATONIC_RISE = AnimationDefinition.Builder.withLength(1.0F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.7083F, KeyframeAnimations.posVec(0.0F, -16.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8333F, KeyframeAnimations.degreeVec(17.5F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7083F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, 9.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7083F, KeyframeAnimations.posVec(0.0F, 14.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0833F, KeyframeAnimations.degreeVec(-31.6066F, 7.9937F, 12.7341F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.25F, KeyframeAnimations.degreeVec(-35.8033F, 7.9918F, 6.3671F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, 7.99F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-71.4516F, -19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-26.4516F, -19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.8983F, -12.5691F, -5.8079F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-7.4929F, -0.3262F, -4.9786F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 2.5F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7083F, KeyframeAnimations.posVec(0.0F, 10.5F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 7.46F, -4.62F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-31.6066F, -7.9937F, -12.7341F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-35.8033F, -7.9918F, -6.3671F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 7.99F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_CATATONIC_SIT = AnimationDefinition.Builder.withLength(1.0F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, -16.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.degreeVec(17.5F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4583F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, 14.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4583F, KeyframeAnimations.posVec(0.0F, 9.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 7.99F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-35.8033F, 7.9918F, 6.3671F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-31.6066F, 7.9937F, 12.7341F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.4929F, -0.3262F, -4.9786F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.8983F, -12.5691F, -5.8079F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-26.4516F, -19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-71.4516F, -19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 7.46F, -4.62F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, 10.5F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 2.5F, -4.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.0F, 7.99F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-35.8033F, -7.9918F, -6.3671F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-31.6066F, -7.9937F, -12.7341F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.4583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition REVENANT_WOLOLO = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.3122F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.5129F, -180.0F, 149.4871F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.3681F, -180.0F, -157.3681F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();
}