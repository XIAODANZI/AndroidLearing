package com.dan.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nineteen.myandroid.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 樱花飘落
 * Created by Administrator on 2016/2/23.
 */
public class DriftDownView extends View {

    /**
     * 叶子飘动一个周期所花的时间
     */
    private static final long LEAF_FLOAT_TIME = 3000;

    /**
     * 叶子飘动一个周期所花的时间
     */
    private long mLeafFloatTime = LEAF_FLOAT_TIME;
    /**
     * 用于控制随机增加的时间不抱团
     */
    private int mAddTime;
    /**
     * 用于产生叶子信息
     */
    private LeafFactory mLeafFactory;
    /**
     * 产生出的叶子信息
     */
    private List<Leaf> mLeafInfos;

    private int mScreenWidth, mScreenHeight;

    private Bitmap mLeftBitmap;
    private Bitmap mBgBitmap;

    private Resources mResources;
    private Context mContext;


    public DriftDownView(Context context) {
        this(context, null);
    }

    public DriftDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DriftDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        mResources = getResources();
        mContext = getContext();
        mScreenWidth = mResources.getDisplayMetrics().widthPixels;
        mScreenHeight = mResources.getDisplayMetrics().heightPixels;
        initBitmap();
        mLeafFactory = new LeafFactory();
        mLeafInfos = mLeafFactory.generateLeafs();
    }

    void initBitmap() {
        mLeftBitmap = BitmapFactory.decodeResource(mResources, R.drawable.petal);
        mBgBitmap = BitmapFactory.decodeResource(mResources, R.drawable.background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, mScreenWidth, mScreenHeight);
        canvas.drawBitmap(mBgBitmap, null, rectF, null);
    }

    public enum StartType {
        LITTLE, MIDDLE, BIG
    }

    /**
     * 叶子对象，用来记录叶子主要数据
     *
     * @author Ajian_Studio
     */
    private class Leaf {

        /**
         * 在绘制部分的位置
         */
        float x, y;
        /**
         * 控制叶子飘动的幅度
         */
        StartType type;
        /**
         * 起始旋转的角度
         */
        int rotateAngle;
        /**
         * 旋转方向，0：顺时针；1：逆时针
         */
        int rotateDirection;
        /**
         * z
         * 起始时间(ms)
         */
        long startTime;
    }

    private class LeafFactory {
        private static final int MAX_LEAVES_COUNT = 8;

        Random random = new Random();

        /**
         * 生成一个叶子信息
         *
         * @return Leaf Object
         */
        public Leaf generateLeaf() {
            Leaf leaf = new Leaf();
            int randomType = random.nextInt(3);
            // 随时类型－ 随机振幅
            StartType type = StartType.MIDDLE;
            switch (randomType) {
                case 0:
                    break;
                case 1:
                    type = StartType.LITTLE;
                    break;
                case 2:
                    type = StartType.BIG;
                    break;
                default:
                    break;
            }
            leaf.type = type;
            // 随机起始的旋转角度
            leaf.rotateAngle = random.nextInt(360);
            // 随机旋转方向（顺时针或逆时针）
            leaf.rotateDirection = random.nextInt(2);
            // 为了产生交错的感觉，让开始的时间有一定的随机性
            mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
            mAddTime += random.nextInt((int) (mLeafFloatTime * 2));
            leaf.startTime = System.currentTimeMillis() + mAddTime;
            return leaf;
        }

        /**
         * 得到根据最大叶子数产生叶子信息列表
         *
         * @return a list of Leaves
         */
        public List<Leaf> generateLeafs() {
            return generateLeafs(MAX_LEAVES_COUNT);
        }


        /**
         * 根据传入的叶子数量产生叶子信息
         *
         * @param leafSize 叶子数量上限
         * @return a list of Leaves
         */
        public List<Leaf> generateLeafs(int leafSize) {
            List<Leaf> leafs = new LinkedList<Leaf>();
            for (int i = 0; i < leafSize; i++) {
                leafs.add(generateLeaf());
            }
            return leafs;
        }
    }
}
