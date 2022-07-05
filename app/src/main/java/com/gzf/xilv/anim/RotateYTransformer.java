package com.gzf.xilv.anim;

import android.view.View;

import androidx.annotation.NonNull;

import com.youth.banner.transformer.BasePageTransformer;

public class RotateYTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 25f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    public RotateYTransformer() {
    }

    public RotateYTransformer(float maxRotate) {
        mMaxRotate = maxRotate;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotY(view.getHeight() >> 1);

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotationY(-1 * mMaxRotate);
            view.setPivotX(view.getWidth());
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            view.setRotationY(-position * mMaxRotate);

            //[0,-1]
            if (position < 0) {
                view.setPivotX(view.getWidth());
                view.setPivotX(view.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
            } else {//[1,0]
                view.setPivotX(0);
                view.setPivotX(view.getWidth() * DEFAULT_CENTER * (1 - position));
            }

            // Scale the page down (between MIN_SCALE and 1)
        } else {
            // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotationY(1 * mMaxRotate);
            view.setPivotX(0);
        }
    }
}
