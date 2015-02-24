package com.desitum.crackTheCode.libraries.animation;

import com.desitum.crackTheCode.libraries.interpolation.AccelerateDecelerateInterpolator;
import com.desitum.crackTheCode.libraries.interpolation.AccelerateInterpolator;
import com.desitum.crackTheCode.libraries.interpolation.AnticipateInterpolator;
import com.desitum.crackTheCode.libraries.interpolation.DecelerateInterpolator;
import com.desitum.crackTheCode.libraries.interpolation.Interpolation;
import com.desitum.crackTheCode.libraries.interpolation.Interpolator;
import com.desitum.crackTheCode.libraries.interpolation.OvershootInterpolator;

/**
 * Created by dvan6234 on 2/24/2015.
 */
public class ScaleAnimator implements Animator {

    private float duration;
    private float endScale;

    private float scaleSize;

    private float timeInAnimation;

    private boolean running;
    private boolean ran;

    private Interpolator interpolator;

    public ScaleAnimator(float duration, float startScale, float endScale, int interpolator){
        this.duration = duration;
        this.timeInAnimation = startScale;
        this.endScale = endScale;

        setupInterpolator(interpolator);
    }

    @Override
    public void update(float delta){
        if (!running){
            return;
        }
        timeInAnimation += delta;
        if (timeInAnimation >= endScale){
            timeInAnimation = endScale;
            stop();
        }

        scaleSize = interpolator.getInterpolation(timeInAnimation);
    }

    public float getScaleSize(){
        return scaleSize;
    }

    public void start(boolean isProtected){
        if (isProtected && !ran){
            running = true;
        } else {
            running = true;
        }
        ran = true;
    }

    public void stop(){
        running = false;
    }

    private void setupInterpolator(int interpolator){
        if (interpolator == Interpolation.ACCELERATE_INTERPOLATOR){
            this.interpolator = AccelerateInterpolator.$();
        } else if (interpolator == Interpolation.DECELERATE_INTERPOLATOR){
            this.interpolator = DecelerateInterpolator.$();
        } else if (interpolator == Interpolation.ANTICIPATE_INTERPOLATOR){
            this.interpolator = AnticipateInterpolator.$();
        } else if (interpolator == Interpolation.OVERSHOOT_INTERPOLATOR){
            this.interpolator = OvershootInterpolator.$();
        } else if (interpolator == Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR){
            this.interpolator = AccelerateDecelerateInterpolator.$();
        }
    }
}
