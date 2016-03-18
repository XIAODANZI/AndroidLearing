package com.dan.controller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nineteen.myandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/27.
 */
public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String title = "Tab" + (i + 1);
            tabs.addTab(tabs.newTab().setText(title));
            titles.add(title);
            Fragment fragment = new CustomFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragments);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        final Random random = new Random();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int position = random.nextInt(tabs.getTabCount());
//                TabLayout.Tab tab = tabs.getTabAt(position);
//                if (tab != null) {
//                    tab.select();
//                }
//            }
//        });

    }
}
