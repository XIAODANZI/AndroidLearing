package com.dan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 水流波动控件 更多详解见博客http://blog.csdn.net/zhongkejingwang/article/details/38556891
 *
 * @author chenjing
 */
public class WaveView2 extends View {

    private int mViewWidth;
    private int mViewHeight;
    private float heightDiff;

    /**
     * 波浪起伏幅度
     */
    private float mWaveHeight = 80;
    /**
     * 波长
     */
    private float mWaveWidth = 200;

    private float mMoveLen;
    /**
     * 水波平移速度
     */
    public static final float SPEED = 3;

    private List<Point> mPointsList;
    private Paint mPaintFirst, mPaintSecond, mPaintThird;
    private Path mWavePathFirst, mWavePathSecond, mWavePathThird;
    private boolean isMeasured = false;

    private Timer timer;
    private MyTimerTask mTask;
    Handler updateHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // 记录平移总位移
            mMoveLen += SPEED;

            // 波形平移
            for (int i = 0; i < mPointsList.size(); i++) {
                mPointsList.get(i).setX(mPointsList.get(i).getX() + SPEED);
            }
            if (mMoveLen >= mWaveWidth) {
                // 波形平移超过一个完整波形后复位
                mMoveLen = 0;
                resetPoints();
            }
            invalidate();

            return false;
        }
    });

    /**
     * 所有点的x坐标都还原到初始状态，也就是一个周期前的状态
     */
    private void resetPoints() {
        updateHeight();
        for (int i = 0; i < mPointsList.size(); i++) {
            mPointsList.get(i).setX(i * mWaveWidth / 4 - mWaveWidth);
        }
    }

    public WaveView2(Context context) {
        super(context);
        init();
    }

    public WaveView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPointsList = new ArrayList<>();
        timer = new Timer();

        mPaintFirst = new Paint();
        mPaintFirst.setAntiAlias(true);
        mPaintFirst.setStyle(Style.STROKE);
        mPaintFirst.setStrokeWidth(2);
        mPaintFirst.setColor(Color.rgb(95, 188, 231));

        mPaintSecond = new Paint();
        mPaintSecond.setAntiAlias(true);
        mPaintSecond.setStyle(Style.STROKE);
        mPaintSecond.setStrokeWidth(2);
        mPaintSecond.setColor(Color.argb(153, 95, 188, 231));

        mPaintThird = new Paint();
        mPaintThird.setAntiAlias(true);
        mPaintThird.setStyle(Style.STROKE);
        mPaintThird.setStrokeWidth(2);
        mPaintThird.setColor(Color.argb(102, 95, 188, 231));

        mWavePathFirst = new Path();
        mWavePathSecond = new Path();
        mWavePathThird = new Path();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        // 开始波动
        start();
    }

    private void start() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;
            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();

            // 根据View宽度计算波形峰值
            mWaveHeight = mViewHeight / 2;
            // 屏幕上显示2.5个波长
            mWaveWidth = mViewWidth / 2.5f;

            // 左边隐藏的距离预留一个波形
            // 这里计算在可见的View宽度中能容纳几个波形，注意n上取整
            int n = (int) Math.round(mViewWidth / mWaveWidth + 0.5);
            // n个波形需要4n+1个点，但是我们要预留一个波形在左边隐藏区域，所以需要4n+5个点
            for (int i = 0; i < (4 * n + 5); i++) {
                // 从P0开始初始化到P4n+4，总共4n+5个点
                float x = i * mWaveWidth / 4 - mWaveWidth;
                float y = 0;
                switch (i % 4) {
                    case 0:
                    case 2:
                        // 零点位于水位线上
                        y = mViewHeight - mWaveHeight;
                        break;
                    case 1:
                        // 往下波动的控制点
                        y = mViewHeight;
                        break;
                    case 3:
                        // 往上波动的控制点
                        y = mViewHeight - 2 * mWaveHeight;
                        break;
                }
                mPointsList.add(new Point(x, y));
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mWavePathFirst.reset();
        mWavePathSecond.reset();
        mWavePathThird.reset();
        int i = 0;
        int diff = 23;
        mWavePathFirst.moveTo(mPointsList.get(0).getX(), mPointsList.get(0).getY());
        mWavePathSecond.moveTo(mPointsList.get(0).getX() - diff, mPointsList.get(0).getY());
        mWavePathThird.moveTo(mPointsList.get(0).getX() - 2 * diff, mPointsList.get(0).getY());


        for (; i < mPointsList.size() - 2; i = i + 2) {
            mWavePathFirst.quadTo(mPointsList.get(i + 1).getX(),
                    mPointsList.get(i + 1).getY(),
                    mPointsList.get(i + 2).getX(),
                    mPointsList.get(i + 2).getY());
            mWavePathSecond.quadTo(mPointsList.get(i + 1).getX() - diff,
                    mPointsList.get(i + 1).getY(),
                    mPointsList.get(i + 2).getX() - diff,
                    mPointsList.get(i + 2).getY());
            mWavePathThird.quadTo(mPointsList.get(i + 1).getX() - 2 * diff,
                    mPointsList.get(i + 1).getY(),
                    mPointsList.get(i + 2).getX() - 2 * diff,
                    mPointsList.get(i + 2).getY());
        }
        mWavePathFirst.lineTo(mPointsList.get(i).getX(), mPointsList.get(i).getY());
        mWavePathSecond.lineTo(mPointsList.get(i).getX() - diff, mPointsList.get(i).getY());
        mWavePathThird.lineTo(mPointsList.get(i).getX() - 2 * diff, mPointsList.get(i).getY());

        canvas.drawPath(mWavePathThird, mPaintThird);

        canvas.saveLayerAlpha(0, 0, mViewWidth, mViewHeight, 255, Canvas.ALL_SAVE_FLAG);
        canvas.drawPath(mWavePathSecond, mPaintSecond);

        canvas.saveLayerAlpha(0, 0, mViewWidth, mViewHeight, 255, Canvas.ALL_SAVE_FLAG);
        canvas.drawPath(mWavePathFirst, mPaintFirst);

        canvas.restore();
        canvas.restore();
    }

    /**
     * 波峰增量
     *
     * @param heightDiff 目前是随机数
     */
    public void setWaveHeightDiff(float heightDiff) {

        this.heightDiff = heightDiff;

    }

    /**
     * 更新波峰波谷高度
     */
    private void updateHeight() {
        // 更改波峰和波谷的值
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < mPointsList.size(); i++) {
            // 从P0开始初始化到P4n+4，总共4n+5个点
            float y = 0;
            switch (i % 4) {
                case 0:
                case 2:
                    // 零点位于水位线上
                    y = mViewHeight - mWaveHeight;
                    break;
                case 1:
                    // 往下波动的控制点
                    y = mViewHeight + heightDiff;
                    break;
                case 3:
                    // 往上波动的控制点
                    y = mViewHeight - mWaveHeight - heightDiff;
                    break;
            }
            list.add(new Point(mPointsList.get(i).getX(), y));
        }

        if (mPointsList != null && mPointsList.size() > 0) {
            mPointsList.clear();
        } else {
            mPointsList = new ArrayList<>();
        }
        mPointsList.addAll(list);

    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }
    }

    class Point {
        private float x;
        private float y;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }

}
