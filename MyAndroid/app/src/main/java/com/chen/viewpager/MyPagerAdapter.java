package com.chen.viewpager;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class MyPagerAdapter extends PagerAdapter {
    List<View> views;
    List<String> titles;

    public MyPagerAdapter(List<View> views, List<String> titles) {
        this.views = views;
        this.titles = titles;
    }

    public MyPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 获取到标题
    public CharSequence getPageTitle(int position) {
        return titles.get(position);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

}
// public class MyPagerAdapter extends PagerAdapter {
//
// List<View> views;
// List<String> titles;
//
// public MyPagerAdapter(List<View> views, List<String> titles) {
// this.titles = titles;
// this.views = views;
// }
//
// @Override
// public int getCount() {
// return views.size();
// }
//
// @Override
// public boolean isViewFromObject(View view, Object object) {
// return view == object;
// }
//
// // 不用
// // 获取到标题
// @Override
// public CharSequence getPageTitle(int position) {
// return titles.get(position);
// }
//
// @Override
// public void destroyItem(ViewGroup container, int position, Object object) {
// container.removeView(views.get(position));
// }
//
// @Override
// public Object instantiateItem(ViewGroup container, int position) {
// container.addView(views.get(position));
// return views.get(position);
// }
//
// }
