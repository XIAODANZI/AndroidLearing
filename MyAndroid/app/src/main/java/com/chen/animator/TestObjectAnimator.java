package com.chen.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestObjectAnimator extends FragmentActivity implements
        OnClickListener {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_imageview);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "aaa",
                0.1f, 1.0f, 1.0f, 0.1f, 1.0f);
        animator.setDuration(4000);
        animator.start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                imageView.setScaleX(value);
                imageView.setScaleY(value);
                imageView.setAlpha(value);

            }
        });

    }

}
