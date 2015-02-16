package com.desitum.crackTheCode.libraries;

/**
 * Created by kody on 2/14/15.
 * can be used by kody and people in []
 */
public class ScaleAnimation implements Animation {

    private float startScale, endScale, currentScale, duration;
    private int interpolator;

    public ScaleAnimation(float startScale, float endScale, float duration, int interpolator) {
        this.startScale = startScale;
        this.endScale = endScale;
        this.currentScale = startScale;
        this.duration = duration;

        this.interpolator = interpolator;
    }

    public void update(float delta) {

    }
}
