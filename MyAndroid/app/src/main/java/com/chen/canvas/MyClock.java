package com.chen.canvas;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.chen.animation.model.UiUtils;

@SuppressLint("DrawAllocation")
public class MyClock extends View {

    PointF mCenter, mStart, mStop;
    Paint paint = new Paint();

    int width, height;
    int radius = 0;
    Calendar calendar;
    int hour, minute, second;

    final static int HOUR_WIDTH = 8;
    final static int MINUTE_WIDTH = 6;
    final static int SECOND_WIDTH = 4;

    final static int NEED_INVALIDATE = 10001;

    public MyClock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        handler.sendEmptyMessage(NEED_INVALIDATE);
    }

    public MyClock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClock(Context context) {
        this(context, null);
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
                // 其他分钟刻度
                paint.setColor(0xff4f2f4f);
                paint.setStrokeWidth(2); // 设置线宽
                drawGraduation(i, 10);
            }
            canvas.drawLine(mStart.x, mStart.y, mStop.x, mStop.y, paint);
        }

        // 画时针，绕圆心旋转
        // 角度
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        paint.setStyle(Paint.Style.STROKE);
        //  画时针
        // 30度是360,12个刻度；
        // 0.5度是30度里面60个刻度
        paint.setStrokeWidth(HOUR_WIDTH);
        paint.setColor(Color.parseColor("#080c4f"));

        float hourDegrees = (float) (hour * 30 + minute * 0.5);

        canvas.rotate(hourDegrees, mCenter.x, mCenter.y);

        canvas.drawLine(mCenter.x, mCenter.y + 15, mCenter.x, mCenter.y - UiUtils.dipToPx(getContext(), radius / 6), paint);

        // 画分针，360度，60分走完；6度60s走完
        paint.setStrokeWidth(MINUTE_WIDTH);
        paint.setColor(Color.parseColor("#99080c4f"));

        float minuteDegrees = (float) (minute * 6 + second * 0.1);

        canvas.rotate(minuteDegrees - hourDegrees, mCenter.x, mCenter.y);

        canvas.drawLine(mCenter.x, mCenter.y + 15, mCenter.x, mCenter.y - UiUtils.dipToPx(getContext(), radius / 5), paint);

        // 画秒针,360度，60s走完
        paint.setStrokeWidth(SECOND_WIDTH);
        paint.setColor(Color.parseColor("#66080c4f"));

        float secondDegrees = (float) (second * 6);

        canvas.rotate(secondDegrees - minuteDegrees, mCenter.x, mCenter.y);

        canvas.drawLine(mCenter.x, mCenter.y + 15, mCenter.x, mCenter.y - UiUtils.dipToPx(getContext(), radius / 4), paint);

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

    //每隔一秒，在handler中调用一次重新绘制方法
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {

                case NEED_INVALIDATE:
                    calendar = Calendar.getInstance();
                    invalidate();//告诉UI主线程重新绘制
                    handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

}

