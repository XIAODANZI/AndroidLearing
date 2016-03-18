package com.dan.controller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 此分割线只适用于listview
 * Created by Administrator on 2016/2/24.
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {


    // 系统默认的分隔条Drawable资源ID
    private static final int[] ATTRS = {android.R.attr.listDivider};
    // 分割条Drawable对象
    private Drawable mDivider;

    public MyDividerItemDecoration(Context context) {
        // 装载
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        // 获取系统提供的分隔条Drawable对象
        mDivider = a.getDrawable(0);
        // 回收TypedArray所占用的控件
        a.recycle();
    }


    // 在该方法中绘制所有列表项的分隔条
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        // 绘制矩形
        // 获取列表项距离左边缘的距离
        int left = parent.getPaddingLeft();

        // 获取列表项距离右边缘的距离
        int right = parent.getWidth() - parent.getPaddingRight();

        // 获取列表项的总数
        int childCount = parent.getChildCount();

        // 开始绘制所有列表项之间的分割线
        for (int i = 0; i < childCount; i++) {

            // 获取当前的列表项
            View childView = parent.getChildAt(i);

            // 获取当前列表项的布局参数信息
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();

            // 计算分隔条左上角的纵坐标
            int topLeft = childView.getBottom() + layoutParams.bottomMargin;

            // 计算分隔条右下角的纵坐标
            int bottomRight = topLeft + mDivider.getIntrinsicHeight();

            // 设置分隔条绘制的位置
            mDivider.setBounds(left, topLeft, right, bottomRight);

            // 开始绘制当前列表项下方的分隔条
            mDivider.draw(c);
        }
    }
}
