package com.chen.canvas;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("DrawAllocation")
public class MyClock extends View {

	PointF mCenter, mStart, mStop;
	Paint paint = new Paint();

	int width, height;
	int radius = 0;
	Calendar calendar = Calendar.getInstance();
	int hour, minute, second;

	final static int hourWidth = 6;
	final static int minuteWidth = 3;
	final static int secondWidth = 2;

	public MyClock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyClock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyClock(Context context) {
		super(context);
	}

	// 测量控件的实际宽和高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = getMeasuredWidth();
		height = getMeasuredHeight();
		// 得到表盘圆心
		mCenter = new PointF();
		mCenter.x = width / 2;
		mCenter.y = height / 2;
		// 得到半径
		if (mCenter.x <= mCenter.y) {
			radius = (int) (mCenter.x * 0.8);
		} else {
			radius = (int) (mCenter.y * 0.8);
		}
	}

	// 绘制图形的方法
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 表盘
		// 设置反锯齿
		paint.setAntiAlias(true);
		paint.setColor(0xfffbf4ec);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(mCenter.x, mCenter.y, radius, paint);

		// 边框
		paint.setColor(0xff000000);
		paint.setStrokeWidth(6); // 设置线宽
		paint.setStyle(Paint.Style.STROKE); // 设置样式为边线
		canvas.drawCircle(mCenter.x, mCenter.y, radius, paint);

		// 画刻度

		for (int i = 0; i < 60; i++) {

			mStart = new PointF();
			mStop = new PointF();
			if (i % 15 == 0) {
				// 画12，3，6，9
				paint.setColor(0xff000000);
				paint.setStrokeWidth(5); // 设置线宽
				drawGraduation(i, 17);
			} else if (i % 5 == 0 && i % 3 != 0) {
				// 所有的小时刻度
				paint.setColor(0xff42426f);
				paint.setStrokeWidth(3); // 设置线宽
				drawGraduation(i, 14);
			} else {
				paint.setColor(0xff4f2f4f);
				paint.setStrokeWidth(2); // 设置线宽
				drawGraduation(i, 10);
			}
			canvas.drawLine(mStart.x, mStart.y, mStop.x, mStop.y, paint);
		}

		// 画时针，绕圆心旋转
		// 角度
		float hourDegrees = 0;
		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);

		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);

		// TODO
		hourDegrees = (float) (hour * 30 + minute * 0.5);

		canvas.rotate(hourDegrees, mCenter.x, mCenter.y);

		// 画分针
		// 画秒针

	}

	public void drawGraduation(int position, int strokeWidth) {
		mStart.x = (float) (mCenter.x + (radius - 4)
				* Math.sin(Math.toRadians(position * 6)));
		mStart.y = (float) (mCenter.y - (radius - 4)
				* Math.cos(Math.toRadians(position * 6)));
		mStop.x = (float) (mCenter.x + (radius - strokeWidth)
				* Math.sin(Math.toRadians(position * 6)));
		mStop.y = (float) (mCenter.y - (radius - strokeWidth)
				* Math.cos(Math.toRadians(position * 6)));
	}
}
