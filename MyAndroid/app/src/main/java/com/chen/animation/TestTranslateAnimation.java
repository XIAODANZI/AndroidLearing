package com.chen.animation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestTranslateAnimation extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_imageview);
		ImageView imageView = (ImageView) findViewById(R.id.iv);
		imageView.setBackgroundResource(R.drawable.fengche);
		TranslateAnimation animation = (TranslateAnimation) AnimationUtils
				.loadAnimation(this, R.anim.translate_animation);
		imageView.setAnimation(animation);
		imageView.startAnimation(animation);
	}
}
