package com.chen.animation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestAlphaAnimation extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_imageview);
		ImageView imageView = (ImageView) findViewById(R.id.iv);
		imageView.setBackgroundResource(R.drawable.fengche);
		AlphaAnimation animation = (AlphaAnimation) AnimationUtils
				.loadAnimation(this, R.anim.alpha_animation);
		imageView.setAnimation(animation);
		imageView.startAnimation(animation);
	}
}
