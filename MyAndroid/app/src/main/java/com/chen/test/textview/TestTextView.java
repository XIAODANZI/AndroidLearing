package com.chen.test.textview;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class TestTextView extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// XML文件写布局
		// 加载布局文件
		// setContentView(R.layout.test_textview);
		// // 绑定控件
		// tv = (TextView) findViewById(R.id.textView_1);
		// // 设置可点击
		// tv.setClickable(true);
		// // 设置字体颜色为红色
		// tv.setTextColor(Color.parseColor("#ffff0000"));
		// // 设置点击事件
		// tv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// tv.setText("TextView的点击事件");
		// }
		// });
		// 代码写布局文件

		LinearLayout linearlayout = new LinearLayout(this);
		// 设置布局方向
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		// 设置参数
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		// 充满父控件大小
		linearlayout.setLayoutParams(layoutParams);

		tv = new TextView(this);
		// 子控件的参数
		LinearLayout.LayoutParams layoutParams_1 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		// 把独立像素设置成像素，即dip-->pixels
		// ceil()四舍五入
		int pixels = (int) Math.ceil(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
						.getDisplayMetrics()));
		// 根据像素点dip设置到周围控件上下左右的距离
		layoutParams_1.setMargins(pixels, pixels, pixels, pixels);

		// 设置布局参数，包裹内容
		tv.setLayoutParams(layoutParams_1);

		// 设置左右有图片，getResources().getDrawable(R.drawable.ic_launcher)根据id得到图片在得到源
		tv.setText(R.string.textview);
		tv.setBackgroundResource(R.drawable.popup_bg);
		// level 17
		// tv.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources()
		// .getDrawable(R.drawable.ic_launcher), null, getResources()
		// .getDrawable(R.drawable.ic_launcher), null);
		tv.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(R.drawable.ic_launcher), null,
				getResources().getDrawable(R.drawable.ic_launcher), null);
		// 添加子控件
		linearlayout.addView(tv);
		setContentView(linearlayout);
	}
}
