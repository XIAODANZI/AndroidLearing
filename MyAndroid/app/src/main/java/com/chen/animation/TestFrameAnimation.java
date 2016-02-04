package com.chen.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestFrameAnimation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_imageview);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        // 第一种
        imageView.setBackgroundResource(R.drawable.welcome_animation);
        AnimationDrawable animation = (AnimationDrawable) imageView
                .getBackground();
        // 第二种
        // imageView.setImageResource(R.drawable.welcome_animation);
        // AnimationDrawable animation = (AnimationDrawable) imageView
        // .getDrawable();
        animation.start();

    }
}
