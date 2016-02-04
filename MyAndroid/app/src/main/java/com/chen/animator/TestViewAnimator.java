package com.chen.animator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nineteen.myandroid.R;

public class TestViewAnimator extends FragmentActivity implements
        OnClickListener {

    ImageView imageView;
    RelativeLayout relativeLayout;
    float height = 0f;
    boolean isMeasured = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewanimation);
        imageView = (ImageView) findViewById(R.id.image);
        relativeLayout = (RelativeLayout) findViewById(R.id.parent);
        findViewById(R.id.viewAnimator).setOnClickListener(this);
        findViewById(R.id.propertyHolder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewAnimator:
                dropDown();
                break;
            case R.id.propertyHolder:
                propertyHolder();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 通过viewTreeObserver计算测量高度
        ViewTreeObserver observer = relativeLayout.getViewTreeObserver();
        observer.addOnPreDrawListener(new OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                if (!isMeasured) {
                    isMeasured = true;
                    height = relativeLayout.getHeight();
                }
                return true;
            }
        });
    }

    @SuppressLint("NewApi")
    public void dropDown() {
        ViewPropertyAnimator viewPropertyAnimator = imageView.animate();
        viewPropertyAnimator.translationY(height);
        viewPropertyAnimator.setDuration(5000);
        viewPropertyAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input * input;
            }
        });

        viewPropertyAnimator.withStartAction(new Runnable() {
            @Override
            public void run() {
            }
        });
        viewPropertyAnimator.withEndAction(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setY(0);
                    }
                });
            }
        });
        viewPropertyAnimator.start();
    }

    public void propertyHolder() {
        PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("scaleX",
                1.0f, 0.8f, 0.5f, 0.3f, 0.5f, 0.8f, 1.0f);
        PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("scaleY",
                1.0f, 0.8f, 0.5f, 0.3f, 0.5f, 0.8f, 1.0f);
        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat(
                "alpha", 1.0f, 0.8f, 0.5f, 0.3f, 0.5f, 0.8f, 1.0f);
        ObjectAnimator
                .ofPropertyValuesHolder(findViewById(R.id.propertyHolder),
                        xHolder, yHolder, alphaHolder).setDuration(1000)
                .start();
    }
}
