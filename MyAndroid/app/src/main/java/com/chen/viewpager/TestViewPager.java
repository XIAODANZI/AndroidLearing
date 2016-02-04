package com.chen.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.nineteen.myandroid.R;

public class TestViewPager extends FragmentActivity implements
        OnBootmItemClickListener {

    ViewPager viewPager;
    HomePageFragment homePage = new HomePageFragment();
    ScriptFragment scriptFragment = new ScriptFragment();
    ImageFragment imageFragment = new ImageFragment();
    List<Fragment> list = new ArrayList<Fragment>();
    BottomFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragment);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomFragment = (BottomFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bottom);
        bottomFragment.setOnBootmItemClickListener(this);

        list.add(homePage);
        list.add(scriptFragment);
        list.add(imageFragment);
        viewPager.setAdapter(new MyFragmentPagerAdapter(
                getSupportFragmentManager(), list));
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            // 某页被选中
            @Override
            public void onPageSelected(int position) {
                bottomFragment.setSelected(position);
            }

            /**
             * 当某页滚动的时候回调 position 页码下标 ；positionOffest 位置的偏移；
             * positionOffestPixels 位置的像素偏移
             */
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            /**
             * 当页码的滚动状态改变时回调
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        break;
                }
            }
        });

    }


    @Override
    public void onBottomItemClick(View view, int position) {
        viewPager.setCurrentItem(position, true);

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }

}
