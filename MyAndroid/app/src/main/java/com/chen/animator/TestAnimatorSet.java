package com.chen.animator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestAnimatorSet extends FragmentActivity implements
        OnClickListener {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_anmiatorset);
        imageView = (ImageView) findViewById(R.id.image);
        findViewById(R.id.together).setOnClickListener(this);
        findViewById(R.id.linear).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.together:
                playTogether();
                break;

            case R.id.linear:
                playSequentially();
                break;
        }
    }

    public void playTogether() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                this, R.animator.test_objectanimator_together);
        animatorSet.setTarget(imageView);
        animatorSet.start();

    }

    public void playSequentially() {
        // 得到x位置
        float cx = imageView.getX();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView,
                "scaleX", 1.0f, 2.0f).setDuration(4000);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView,
                "scaleY", 1.0f, 2.0f).setDuration(4000);
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(imageView, "x",
                cx, -cx).setDuration(4000);
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(imageView, "x",
                -cx, cx).setDuration(4000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, leftAnimator);
        // 这两个相同
        // animatorSet.play(scaleYAnimator).before(rightAnimator);
        animatorSet.play(rightAnimator).after(scaleYAnimator);

        animatorSet.start();
    }
}
