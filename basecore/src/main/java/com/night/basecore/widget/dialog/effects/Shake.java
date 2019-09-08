package com.night.basecore.widget.dialog.effects;

import android.animation.ObjectAnimator;
import android.view.View;


/**
 * Created by lee on 2014/7/31.
 */
public class Shake  extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, .10f, -25, .26f, 25, .42f, -25, .58f, 25, .74f, -25, .90f, 1, 0).setDuration(mDuration)

        );
    }
}
