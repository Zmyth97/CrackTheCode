package com.desitum.crackTheCode.libraries;

/**
 * Created by kody on 2/14/15.
 * can be used by kody and people in []
 */
public class ScaleAnimation implements Animation {

    private float startScale, endScale, currentScale, duration, pointInInterpolation;
    private int interpolator;

    public ScaleAnimation(float startScale, float endScale, float duration, int interpolator) {
        this.startScale = startScale;
        this.endScale = endScale;
        this.currentScale = startScale;
        this.duration = duration;

        this.interpolator = interpolator;
    }

    public void update(float delta) {
        pointInInterpolation += delta;
    }

    public float singleBounceShrinkCalculator(float bounceAmount, float startPoint) {
        //To Get the Shrink Amount
        //Shrink Formula: 0.4282 * squareroot(bounceAmount-1.1) + 1.30
        //Only works on bounce amounts >= 1.1 and <= 2.0 or it gets unaccurate

        float shrinkAmount = (float) (0.4282f * (float) Math.sqrt(bounceAmount - 1.1) + 1.30);
        return shrinkAmount;



    }
}
