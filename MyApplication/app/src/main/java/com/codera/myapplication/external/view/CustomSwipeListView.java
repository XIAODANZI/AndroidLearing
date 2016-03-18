package com.codera.myapplication.external.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import com.codera.myapplication.R;
import com.codera.myapplication.utils.UIUtils;

/**
 * 左滑删除的listview
 * Created by Administrator on 2016/2/22.
 */
public class CustomSwipeListView extends ListView {

    /**
     * 当前活动listview position
     */
    private int slidePosition;

    /**
     * 手指按下X、Y的坐标
     */
    private int downX, downY;

    /**
     * 屏幕宽度
     */
    private int screenWidth;

    /**
     * ListView的item
     */
    private View itemView;

    /**
     * item里面的内容区域
     */
    private View contentView;

    /**
     * 滑动类
     */

    private Scroller scroller;

    /**
     * 活动速度极限值
     */
    private final int SNAP_VELOCITY = UIUtils.convertDptoPx(getContext(), 1000);

    /**
     * 速度追踪对象
     */
    private VelocityTracker mVelocityTracker;

    /**
     * 是否响应滑动，默认不响应
     */
    private boolean isSlide = false;

    /**
     * 用户滑动最小距离
     */
    private int mTouchSlop;

    /**
     * 移除item后的回到接口
     */
    private RemoveListener mRemoveListener;
    private RemoveDirection mRemoveDirection;

    private boolean isRemoveScroll = false;

    /**
     * 指定计算哪个点的速度
     */
    private int mPointerId;

    /**
     * 获得允许执行一个fling手势动作的最大速度值
     */
    private int mMaxVelocity;

    int velocityX = 0;

    public CustomSwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        scroller = new Scroller(context);

        // 检测用户在move前划过的距离，移动距离大于这个距离才开始算滑动
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        mMaxVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
    }

    public CustomSwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwipeListView(Context context) {
        super(context);
    }

    /**
     * 设置滑动删除的回调接口
     *
     * @param removeListener
     */
    public void setRemoveListener(RemoveDirection removeListener) {
        this.mRemoveDirection = removeListener;
    }

    /**
     * 添加用户的速度跟踪器
     *
     * @param event 手势事件
     */
    private void addVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 分发时间，判断点击的item的位置，以及通过postDeplayed来设置响应左右滑动事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        addVelocityTracker(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointerId = ev.getPointerId(0);
                // 假如scroller滚动还没有结束，直接返回
                if (!scroller.isFinished()) {
                    return super.dispatchTouchEvent(ev);
                }
                downX = (int) ev.getX();
                downY = (int) ev.getY();

                slidePosition = pointToPosition(downX, downY);

                // 无效的position，不做任何处理
                if (slidePosition == AdapterView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }

                // 获取我们点击的item view
                itemView = getChildAt(slidePosition - getFirstVisiblePosition());
                // 获取item view中的内容
                contentView = itemView.findViewById(R.id.contentView);
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(getScrollVelocity()) > SNAP_VELOCITY ||
                        (Math.abs(ev.getX() - downX) > mTouchSlop &&
                                Math.abs(ev.getY() - downY) < mTouchSlop)) {
                    // || 后面保证的是在水平方向滑动
                    isSlide = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * 处理拖动ListView item的逻辑
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isSlide && slidePosition != AdapterView.INVALID_POSITION) {
            addVelocityTracker(ev);

            final int action = ev.getAction();
            int x = (int) ev.getX();

            switch (action) {
                case MotionEvent.ACTION_MOVE:

                    int deltaX = downX - x;
                    downX = x;
                    // 手指拖动itemView滚动, deltaX大于0向左滚动，小于0向右滚
                    itemView.scrollBy(deltaX, 0);
                    setCotentViewAlpha(getAlphaRatio());
                    velocityX = getScrollVelocity();
                    break;
                case MotionEvent.ACTION_UP:

                    if (velocityX > SNAP_VELOCITY) {
                        scrollRight();
                    } else if (velocityX < -SNAP_VELOCITY) {
                        scrollLeft();
                    } else {
                        scrollByDistanceX();
                    }
                    recycleVelocityTracker();
                    // 手指离开的时候就不响应左右滚动
                    isSlide = false;
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroll.computeScrollOffset()返回true
        if (scroller.computeScrollOffset()) {
            // 让ListView item根据当前的滚动偏移量进行滚动
            itemView.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            setCotentViewAlpha(getAlphaRatio());
            postInvalidate();

            // 滚动动画结束的时候调用回调接口
            if (scroller.isFinished() && isRemoveScroll) {
                if (mRemoveListener == null) {
                    throw new NullPointerException("RemoveListener is null, " +
                            "we should called setRemoveListener()");
                }
                mRemoveListener.removeItem(mRemoveDirection, slidePosition);

                // 删除item后要把透明度和坐标恢复到初始值
                itemView.scrollTo(0, 0);
                setCotentViewAlpha(255);
            }
        }

    }

    /**
     * 往右滑动，getScrollX()返回的是左边缘的距离，就是以View左边缘为原点到开始滑动，
     */
    private void scrollRight() {
        isRemoveScroll = true;
        mRemoveDirection = RemoveDirection.RIGHT;
        final int delta = (screenWidth + itemView.getScrollX());
        // 调用startScroll方法设置一些滚动参数，在computeScroll()方法中调用scrollTo来滚动item
        scroller.startScroll(itemView.getScrollX(), 0, -delta, 0, Math.abs(delta));
        postInvalidate(); // 刷新itemView
    }

    /**
     * 向左滑动，根据上面我们知道向左滑动为正值
     */
    private void scrollLeft() {
        isRemoveScroll = true;
        mRemoveDirection = RemoveDirection.LEFT;
        final int delta = (screenWidth - itemView.getScrollX());
        // 调用startScroll方法来设置一些滚动的参数，computeScroll()方法中调用scrollTo来滚动item
        scroller.startScroll(itemView.getScrollX(), 0, delta, 0, Math.abs(delta));
        postInvalidate(); // 刷新itemView
    }

    /**
     * 如果滑动速度不快且距离不到1/3，就原地滑动回原点
     */
    private void scrollToOrigin() {
        isRemoveScroll = false;
        int scrollX = itemView.getScrollX();
        // 反方向滑动回去
        scroller.startScroll(scrollX, 0, -scrollX, 0, 400);
    }

    /**
     * 根据手指滚动itemView的距离来判断是滚动到开始位置还是向左或者向右滚动
     */
    private void scrollByDistanceX() {
        // 如果向左滚动的距离大于屏幕的二分之一，就让其删除
        if (itemView.getScrollX() >= screenWidth / 2) {
            scrollLeft();
        } else if (itemView.getScrollX() <= -screenWidth / 2) {
            scrollRight();
        } else {
            scrollToOrigin();
        }
    }

    /**
     * 获取移动距离跟透明度的比率，总距离为1/2 屏幕宽，透明度从0~255
     */
    private int getAlphaRatio() {
        int scrollX = Math.abs(itemView.getScrollX());
        int xRatio = Math.round(((2 * scrollX) / (float) screenWidth) * 255);
        // 透明度最大值为255
        xRatio = 255 - (xRatio > 255 ? 255 : xRatio);
        return xRatio;
    }

    /**
     * 设置内容区域的透明度
     */
    private void setCotentViewAlpha(int xRatio) {

        contentView.getBackground().setAlpha(xRatio);

//        TextView tvTitle = (TextView) contentView.findViewById(R.id.test_title);
//        TextView tvDate = (TextView) contentView.findViewById(R.id.test_date);
//        setTextAlpha(xRatio, tvTitle);
//        setTextAlpha(xRatio, tvDate);
    }

    /**
     * 设置文字的透明色
     */
    private void setTextAlpha(int ratio, TextView textView) {
        int color = textView.getCurrentTextColor();
        textView.setTextColor(Color.argb(ratio, Color.red(color), Color.green(color),
                Color.blue(color)));
    }

    /**
     * 获取X方向的滑动速度，大于0向右，反之向左
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
        int velocity = (int) mVelocityTracker.getXVelocity(mPointerId);
        return velocity;
    }

    /**
     * 滑动删除方向的枚举值
     */
    public enum RemoveDirection {
        RIGHT, LEFT
    }

    /**
     * 当listview item 滑出屏幕，回调这个接口
     */
    public interface RemoveListener {
        public void removeItem(RemoveDirection direction, int position);
    }

}
