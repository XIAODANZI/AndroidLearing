package com.chen.animator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nineteen.myandroid.R;

public class TestValueAnimator extends FragmentActivity implements
        OnClickListener {

    int height = 0, width = 0;
    boolean isMeasured = false;

    ImageView imgBolBlue;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_value_animator);
        findViewById(R.id.btn_vertical).setOnClickListener(this);
        findViewById(R.id.btn_parabola).setOnClickListener(this);
        findViewById(R.id.btn_fadeout_del).setOnClickListener(this);

        relativeLayout = (RelativeLayout) findViewById(R.id.parent);
        imgBolBlue = (ImageView) findViewById(R.id.img_bol_blue);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewTreeObserver observer = relativeLayout.getViewTreeObserver();
        observer.addOnPreDrawListener(new OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                if (!isMeasured) {
                    isMeasured = true;
                    height = relativeLayout.getHeight();
                    width = relativeLayout.getWidth();
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        // TODO
        switch (v.getId()) {
            case R.id.btn_vertical:
                dropDown();
                break;
            case R.id.btn_parabola:
                parabola();
                break;
            case R.id.btn_fadeout_del:
                FadeoutDel();
                break;
        }
    }

    public void parabola() {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        // 使用像素点对象
        valueAnimator.setObjectValues(new PointF());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                // function = t / duration startValue 开始的点；endValue 结束的点
                PointF point = new PointF();
                point.x = 125 * fraction * 3;
                point.y = 0.5f * 150 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imgBolBlue.setX(pointF.x);
                imgBolBlue.setY(pointF.y);
            }
        });

        valueAnimator.start();

        valueAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imgBolBlue.setY(0.0f);
                imgBolBlue.setX(0.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    public void FadeoutDel() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimator.setDuration(500);
        valueAnimator.setTarget(imgBolBlue);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                imgBolBlue.setAlpha(value);
            }
        });
        valueAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                relativeLayout.removeView(imgBolBlue);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }

    public void dropDown() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, height);
        // ValueAnimator valueAnimator = new ValueAnimator();
        // target
        valueAnimator.setTarget(imgBolBlue);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                imgBolBlue.setY(value);
                imgBolBlue.setAlpha((height - value) / height);
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imgBolBlue.setY(0.0f);
                imgBolBlue.setAlpha(1.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }
}
