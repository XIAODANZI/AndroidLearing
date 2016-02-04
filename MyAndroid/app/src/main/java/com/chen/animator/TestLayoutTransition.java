package com.chen.animator;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.nineteen.myandroid.R;

public class TestLayoutTransition extends FragmentActivity implements
		OnCheckedChangeListener, OnClickListener {

	CheckBox mChkAppearing, mChkChangeAppearing, mChkDisappearing,
			mChkChangeDisappearing;
	LinearLayout linearLayout;
	GridLayout gridLayout;
	LayoutTransition layoutTransition;
	ObjectAnimator xAnimator;

	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_animator);

		linearLayout = (LinearLayout) findViewById(R.id.lv_parent);
		mChkAppearing = (CheckBox) findViewById(R.id.id_chk_appearing);
		mChkChangeAppearing = (CheckBox) findViewById(R.id.id_chk_change_appearing);
		mChkDisappearing = (CheckBox) findViewById(R.id.id_chk_disappearing);
		mChkChangeDisappearing = (CheckBox) findViewById(R.id.id_chk_change_disappearing);

		mChkAppearing.setOnCheckedChangeListener(this);
		mChkChangeAppearing.setOnCheckedChangeListener(this);
		mChkDisappearing.setOnCheckedChangeListener(this);
		mChkChangeDisappearing.setOnCheckedChangeListener(this);

		findViewById(R.id.id_btn_add).setOnClickListener(this);
		// 生成一个GridLayout
		gridLayout = new GridLayout(this);
		// 每行5列
		gridLayout.setColumnCount(5);

		linearLayout.addView(gridLayout);

		// 布局动画
		layoutTransition = new LayoutTransition();
		layoutTransition.setDuration(800);
		// APPEARING
		xAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1.0f);

		layoutTransition.setAnimator(LayoutTransition.APPEARING, xAnimator);
		// CHANGE_APPREARING
		layoutTransition
				.setAnimator(LayoutTransition.CHANGE_APPEARING,
						layoutTransition
								.getAnimator(LayoutTransition.CHANGE_APPEARING));
		// DISAPPEARING
		layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,
				ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f));

		// CHANGE_DISAPPEARING
		layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
				layoutTransition
						.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
		// 将动画应用到gridlayout上面
		gridLayout.setLayoutTransition(layoutTransition);
	}

	@Override
	public void onClick(View v) {

		final Button button = new Button(this);
		button.setText("" + (++count));
		// 位置：在1之后，如果1存在，否在就是第一个
		gridLayout.addView(button, Math.min(1, gridLayout.getChildCount()));
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 移除BUTTON
				gridLayout.removeView(button);
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		layoutTransition = new LayoutTransition();
		System.out.println(isChecked);
		switch (buttonView.getId()) {
		case R.id.id_chk_appearing:
			layoutTransition.setAnimator(LayoutTransition.APPEARING,
					(isChecked) ? xAnimator : null);
			break;
		case R.id.id_chk_disappearing:
			layoutTransition.setAnimator(
					LayoutTransition.DISAPPEARING,
					(isChecked) ? ObjectAnimator.ofFloat(this, "alpha", 1.0f,
							0.0f) : null);
			break;
		case R.id.id_chk_change_appearing:
			layoutTransition.setAnimator(
					LayoutTransition.CHANGE_APPEARING,
					(isChecked) ? layoutTransition
							.getAnimator(LayoutTransition.CHANGE_APPEARING)
							: null);
			break;
		case R.id.id_chk_change_disappearing:
			layoutTransition.setAnimator(
					LayoutTransition.CHANGE_DISAPPEARING,
					(isChecked) ? layoutTransition
							.getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
							: null);
			break;
		}
		// 将动画应用到gridlayout上面
		gridLayout.setLayoutTransition(layoutTransition);
	}
}
