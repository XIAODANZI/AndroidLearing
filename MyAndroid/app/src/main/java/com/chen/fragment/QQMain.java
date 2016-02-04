package com.chen.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.View;

import com.chen.viewpager.HomePageFragment;
import com.nineteen.myandroid.R;

public class QQMain extends FragmentActivity implements
        OnBootmItemClickListener {
    ChatFragment chatFragment = new ChatFragment();
    FriendsFragment friendsFragment = new FriendsFragment();
    GroupFragment groupFragment = new GroupFragment();
    DynamicFragment dynamicFragment = new DynamicFragment();
    HomePageFragment homePage = new HomePageFragment();
    QQBottomFragment qqBottomFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.qqmian);
        qqBottomFragment = (QQBottomFragment) getSupportFragmentManager()
                .findFragmentById(R.id.qqBottom);
        qqBottomFragment.setOnBootmItemClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentPanel, chatFragment).addToBackStack("chat")
                .commit();
        getSupportFragmentManager().addOnBackStackChangedListener(
                new OnBackStackChangedListener() {

                    @Override
                    public void onBackStackChanged() {
                        System.out.println("onBackStackChanged");
                        // 查询有多少fragment
                        int count = getSupportFragmentManager()
                                .getBackStackEntryCount();
                        // 拿到栈顶的entry的name
                        if (count - 1 >= 0) {
                            BackStackEntry entry = getSupportFragmentManager()
                                    .getBackStackEntryAt(count - 1);
                            // 获得到这个entry的name
                            String name = entry.getName();
                            System.out.println("name = " + name);
                            setItemChecked(name);
                        }
                    }
                });
    }

    public void setItemChecked(String name) {
        int position = QQBottomFragment.CHAT_POSITION;
        if (name.equals("chat")) {
            position = QQBottomFragment.CHAT_POSITION;
        } else if (name.equals("friends")) {
            position = QQBottomFragment.FREINEDS_POSITION;
        } else if (name.equals("group")) {
            position = QQBottomFragment.GROUP_POSITION;
        } else if (name.equals("dynamic")) {
            position = QQBottomFragment.DYNAMIC_POSITION;
        }
        qqBottomFragment.setSelected(position);
    }

    @Override
    public void onBottomItemClick(View view, int position) {
        switch (position) {
            case QQBottomFragment.CHAT_POSITION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel, chatFragment)
                        .addToBackStack("chat").commit();
                break;
            case QQBottomFragment.FREINEDS_POSITION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel, friendsFragment)
                        .addToBackStack("friends").commit();
                break;
            case QQBottomFragment.GROUP_POSITION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel, groupFragment)
                        .addToBackStack("group").commit();
                break;
            case QQBottomFragment.DYNAMIC_POSITION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentPanel, dynamicFragment)
                        .addToBackStack("dynamic").commit();
                break;
        }
        // qqBottomFragment.setSelected(position);
    }

}
