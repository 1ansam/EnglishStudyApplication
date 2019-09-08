package com.night.basecore.widget.dialog.effects;

import android.animation.ObjectAnimator;
import android.view.View;


/**
 * Created by lee on 2014/7/30.
 */
public class FadeIn extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)

        );
    }
}
