package com.codera.myapplication.chen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.codera.myapplication.R;

/**
 * 自定义View（一）
 * 1、自定义View的属性：res/values/  下建立一个attrs.xml，定义属性和声明整个样式
 * 2、在View的构造方法中获得我们自定义的属性
 * [ 3、重写onMesure ] 不一定是必须的，大部分情况下还是需要重写的
 * 4、重写onDraw
 * 5、在布局中申明自定义View，需要引入自己的命名空间
 * xmlns:custom="http://schemas.android.com/apk/res/com.codera.myapplication.chen.view.CustomTitleView
 * Created by Administrator on 2016/2/22.
 */
public class CustomTitleView extends View {

    private String mTitleText;
    private int mTitleTextColor, mTitleTextBackgorundColor;
    private int mTitleTextSize;

    /**
     * 绘制时，控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context, null);
    }


    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义的样式属性
     */
    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取我们所定义的自定义样式属性
         */
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextForeColor:
                    mTitleTextColor = typedArray.getColor(attr, Color.parseColor("#080c4f"));
                    break;
                case R.styleable.CustomTitleView_titleTextBackgroundColor:
                    mTitleTextBackgorundColor = typedArray.getColor(attr, Color.parseColor("#f2f2f2"));
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    int textSize = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr, textSize);
                    break;
            }
        }
        typedArray.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }

    /**
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * UNSPECIFIED：表示子布局想要多大就多大，很少使用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先画背景色
        mPaint.setColor(mTitleTextBackgorundColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        // 再画字
        mPaint.setColor(mTitleTextColor);
        /**
         * (getHeight() + mBound.height()) / 2 是文字的基础线
         */
        canvas.drawText(mTitleText, (getWidth() - mBound.width()) / 2, (getHeight() + mBound.height()) / 2, mPaint);
    }
}
