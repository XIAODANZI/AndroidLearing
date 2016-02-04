package com.chen.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class XMLForAnimator extends FragmentActivity implements OnClickListener {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_scale);
        imageView = (ImageView) findViewById(R.id.image);
        findViewById(R.id.btnScaleX).setOnClickListener(this);
        findViewById(R.id.btnScaleXY).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScaleX:
                ScaleX();
                break;
            case R.id.btnScaleXY:
                ScaleXY();
                break;
        }

    }

    public void ScaleX() {
        Animator animator = AnimatorInflater.loadAnimator(this,
                R.animator.scale_x);
        // 设置动画执行目标
        animator.setTarget(imageView);
        animator.start();
    }

    public void ScaleXY() {

        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
                .loadAnimator(this, R.animator.scale_xy);
        objectAnimator.setTarget(imageView);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                imageView.setScaleX(value);
                imageView.setScaleY(value);
                imageView.setAlpha(value);
                imageView.setRotationX(value * 360);
            }
        });
    }

}
