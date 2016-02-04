package com.chen.animation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestScaleAnimation extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_imageview);
		ImageView imageView = (ImageView) findViewById(R.id.iv);
		imageView.setBackgroundResource(R.drawable.fengche);
		ScaleAnimation animation = (ScaleAnimation) AnimationUtils
				.loadAnimation(this, R.anim.scale_animation);
		imageView.setAnimation(animation);
		imageView.startAnimation(animation);
	}
}
